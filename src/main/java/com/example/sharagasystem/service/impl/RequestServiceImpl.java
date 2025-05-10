package com.example.sharagasystem.service.impl;

import com.example.sharagasystem.exception.NotFoundException;
import com.example.sharagasystem.mapper.RequestMapper;
import com.example.sharagasystem.model.Request;
import com.example.sharagasystem.model.dto.filter.RequestFilterDto;
import com.example.sharagasystem.model.dto.request.request.RequestCreationRequestDto;
import com.example.sharagasystem.model.dto.response.request.RequestResponseDto;
import com.example.sharagasystem.model.enums.RequestStatus;
import com.example.sharagasystem.model.enums.RequestType;
import com.example.sharagasystem.repository.RequestRepository;
import com.example.sharagasystem.security.model.User;
import com.example.sharagasystem.security.repository.UserRepository;
import com.example.sharagasystem.service.RequestService;
import com.example.sharagasystem.specification.RequestSpecification;
import com.example.sharagasystem.utility.ProjectNumberGenerator;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
@Service
@Setter
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService {

    private final RequestRepository requestRepository;
    private final UserRepository userRepository;
    private final ProjectNumberGenerator projectNumberGenerator;

    @Override
    public RequestResponseDto findByNumber(String number) {
        return requestRepository.findRequestByNumber(number).map(RequestMapper::mapToRequestResponseDto)
                .orElseThrow(() -> new NotFoundException("There is no request with number: " + number));

    }

    @Override
    public List<RequestResponseDto> findAll() {
        return requestRepository.findAll().stream().map(RequestMapper::mapToRequestResponseDto).toList();
    }

    @Override
    public List<RequestResponseDto> getRequestsByFilter(List<RequestFilterDto> filterDTOList) {

        for (RequestFilterDto filter : filterDTOList) {
            if (filter.getColumnName().equals("assignee") || filter.getColumnName().equals("resident")) {
                String email = (String) filter.getColumnValue();
                User user = userRepository.findByEmail(email)
                        .orElseThrow(() -> new NotFoundException("User not found: " + email));
                filter.setColumnValue(user);
            }
            if (filter.getColumnName().equals("type")) {
                String type = (String) filter.getColumnValue();
                RequestType requestType = RequestType.valueOf(type);
                filter.setColumnValue(requestType);
            }
            if (filter.getColumnName().equals("status")) {
                String status = (String) filter.getColumnValue();
                RequestStatus requestStatus = RequestStatus.valueOf(status);
                filter.setColumnValue(requestStatus);
            }

        }

        return requestRepository.findAll(RequestSpecification.columnEqual(filterDTOList))
                .stream().map(RequestMapper::mapToRequestResponseDto).toList();
    }


    @Override
    public RequestResponseDto saveRequest(RequestCreationRequestDto requestCreationRequestDto) {
        Request request = RequestMapper.mapToRequest(requestCreationRequestDto);
        request.setNumber(projectNumberGenerator.generateProjectNumber("REQ"));
        User resident = userRepository.findByEmail(requestCreationRequestDto.getUserEmail())
                .orElseThrow(() -> new NotFoundException("User not found: " + requestCreationRequestDto.getUserEmail()));
        request.setResident(resident);
        request.setStatus(RequestStatus.WAITING_FOR_ASSIGNEE);
        Request savedRequest = requestRepository.save(request);
        return RequestMapper.mapToRequestResponseDto(savedRequest);
    }

    @Override
    public RequestResponseDto assignRequest(String number, UUID assigneeId) {
        Request request = requestRepository.findRequestByNumber(number)
                .orElseThrow(() -> new NotFoundException("There is no request with number: " + number));
        User assignee = userRepository.findById(assigneeId)
                .orElseThrow(() -> new NotFoundException("User not found: " + assigneeId));
        request.setAssignee(assignee);
        request.setStatus(RequestStatus.IN_PROGRESS);
        Request save = requestRepository.save(request);
        return RequestMapper.mapToRequestResponseDto(save);

    }
}
