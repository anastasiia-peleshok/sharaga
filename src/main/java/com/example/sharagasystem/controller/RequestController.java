package com.example.sharagasystem.controller;

import com.example.sharagasystem.model.dto.filter.RequestFilterDto;
import com.example.sharagasystem.model.dto.request.request.RequestCreationRequestDto;
import com.example.sharagasystem.model.dto.response.request.RequestResponseDto;
import com.example.sharagasystem.service.RequestService;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/requests")
@RequiredArgsConstructor
public class RequestController {
    private final RequestService requestService;

    @PostMapping
    @ResponseBody
    public RequestResponseDto createRequest(@RequestBody RequestCreationRequestDto requestCreationRequestDto) {
        return requestService.saveRequest(requestCreationRequestDto);
    }

    @PutMapping("/{projectNumber}/{assigneeId}")
    @ResponseBody
    public RequestResponseDto assignResponsibleForRequest(@PathVariable String projectNumber, @PathVariable UUID assigneeId) {
        return requestService.assignRequest(projectNumber, assigneeId);
    }

    @GetMapping
    public List<RequestResponseDto> getStudentsByFilter(@RequestBody List<RequestFilterDto> filterDTOList)
    {
        return requestService.getRequestsByFilter(filterDTOList);
    }
}
