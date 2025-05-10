package com.example.sharagasystem.service;

import com.example.sharagasystem.model.dto.filter.RequestFilterDto;
import com.example.sharagasystem.model.dto.request.request.RequestCreationRequestDto;
import com.example.sharagasystem.model.dto.response.request.RequestResponseDto;
import java.util.List;
import java.util.UUID;

public interface RequestService {
    RequestResponseDto findByNumber(String number);

    List<RequestResponseDto> findAll();

    List<RequestResponseDto> getRequestsByFilter(List<RequestFilterDto> filterDTOList);

    RequestResponseDto saveRequest(RequestCreationRequestDto requestCreationRequestDto);

    RequestResponseDto assignRequest(String number, UUID assigneeId);


}
