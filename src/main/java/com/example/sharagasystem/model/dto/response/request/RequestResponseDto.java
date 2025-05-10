package com.example.sharagasystem.model.dto.response.request;

import com.example.sharagasystem.model.enums.RequestStatus;
import com.example.sharagasystem.model.enums.RequestType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestResponseDto {
    private String number;
    private String residentEmail;
    private String assigneeEmail;
    private String description;
    private RequestType type;
    private RequestStatus status;
}
