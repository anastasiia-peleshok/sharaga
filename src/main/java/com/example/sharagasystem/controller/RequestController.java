package com.example.sharagasystem.controller;

import com.example.sharagasystem.model.dto.filter.RequestFilterDto;
import com.example.sharagasystem.model.dto.request.request.RequestCreationRequestDto;
import com.example.sharagasystem.model.dto.response.request.RequestResponseDto;
import com.example.sharagasystem.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/request")
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
