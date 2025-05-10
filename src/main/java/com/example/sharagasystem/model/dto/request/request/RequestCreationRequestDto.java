package com.example.sharagasystem.model.dto.request.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestCreationRequestDto {
    private String userEmail;
    private String description;
    private String type;
}
