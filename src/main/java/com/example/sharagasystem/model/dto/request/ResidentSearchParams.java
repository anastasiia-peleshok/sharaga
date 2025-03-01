package com.example.sharagasystem.model.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Pageable;

@Getter
@Setter
public class ResidentSearchParams {
    private String textToSearch;
    private Pageable pageable;
}
