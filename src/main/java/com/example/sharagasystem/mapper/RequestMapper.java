package com.example.sharagasystem.mapper;

import com.example.sharagasystem.model.Request;
import com.example.sharagasystem.model.dto.request.request.RequestCreationRequestDto;
import com.example.sharagasystem.model.dto.response.request.RequestResponseDto;
import com.example.sharagasystem.model.enums.RequestType;

public class RequestMapper {
    public static Request mapToRequest(RequestCreationRequestDto requestCreationRequestDto) {
        Request request = new Request();
        request.setType(RequestType.valueOf(requestCreationRequestDto.getType()));
        request.setDescription(requestCreationRequestDto.getDescription());
        return request;
    }

    public static RequestResponseDto mapToRequestResponseDto(Request request) {
        RequestResponseDto requestResponseDto = new RequestResponseDto();
        requestResponseDto.setDescription(request.getDescription());
        requestResponseDto.setType(request.getType());
        requestResponseDto.setNumber(String.valueOf(request.getNumber()));
        requestResponseDto.setStatus(request.getStatus());
        requestResponseDto.setResidentEmail(request.getResident().getEmail());
        if (request.getAssignee() != null) {
            requestResponseDto.setAssigneeEmail(request.getAssignee().getEmail());
        }
        return requestResponseDto;
    }
}
