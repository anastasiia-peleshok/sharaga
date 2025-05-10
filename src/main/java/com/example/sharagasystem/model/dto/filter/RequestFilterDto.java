package com.example.sharagasystem.model.dto.filter;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestFilterDto {
    private String columnName;

    private Object columnValue;

}
