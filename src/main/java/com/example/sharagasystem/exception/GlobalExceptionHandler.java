package com.example.sharagasystem.exception;

import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({
            NotFoundException.class,
            AuthenticationException.class,
            NameAlreadyTakenException.class,
            FastException.class,
    })
    public ResponseEntity<Object> handleNotFoundException(
            final RuntimeException e, HttpServletRequest request
    ) {
        return generateResponseEntity(new String[]{e.getMessage()}, request, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            org.springframework.http.HttpHeaders headers,
            HttpStatusCode status,
            org.springframework.web.context.request.WebRequest request
    ) {
        String[] errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .toArray(String[]::new);

        HttpServletRequest servletRequest = ((org.springframework.web.context.request.ServletWebRequest) request).getRequest();

        return generateResponseEntity(errors, servletRequest, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<Object> generateResponseEntity(final String[] errors, final HttpServletRequest request, HttpStatus httpStatus) {
        final ExceptionDetails exceptionDetails = new ExceptionDetails();
        exceptionDetails.setMessage(processErrors(errors));
        exceptionDetails.setHttpStatus(httpStatus);
        exceptionDetails.setLocalDateTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        exceptionDetails.setPath(request.getRequestURI());
        return new ResponseEntity<>(exceptionDetails, httpStatus);
    }

    private String[] processErrors(String[] errors) {
        String defaultErrorMessage = "An error occurred";
        return Arrays.stream(errors)
                .map(error -> error != null ? error : defaultErrorMessage)
                .toArray(String[]::new);
    }

}
