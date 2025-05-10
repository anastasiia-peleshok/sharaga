package com.example.sharagasystem.mapper;

import com.example.sharagasystem.model.ResidentDetails;
import com.example.sharagasystem.model.dto.response.ResidentDetailsLowInfoResponseDto;
import java.time.LocalDate;
import org.springframework.stereotype.Component;

@Component
public class ResidentDetailsMapper {
    public ResidentDetailsLowInfoResponseDto mapToResidentDetailsLowInfoResponseDto(ResidentDetails residentDetails) {
        if(residentDetails == null){
            return null;
        }
        ResidentDetailsLowInfoResponseDto residentResponseDto = new ResidentDetailsLowInfoResponseDto();
        residentResponseDto.setId(residentDetails.getId());
        residentResponseDto.setFirstName(residentDetails.getFirstName());
        residentResponseDto.setLastName(residentDetails.getLastName());
        residentResponseDto.setRoom(residentDetails.getRoom() != null ? residentDetails.getRoom().getNumber() : null);
        residentResponseDto.setYearsInUniversity(countYearsInUniversity(residentDetails));
        return residentResponseDto;
    }

    private Integer countYearsInUniversity(ResidentDetails residentDetails) {
        if(residentDetails.getDateOfEntry() == null){
            return 0;
        }
        int currentYear = LocalDate.now().getYear();
        int entryYear = residentDetails.getDateOfEntry().getYear();
        return currentYear - entryYear;
    }
}
