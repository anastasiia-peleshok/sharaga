package com.example.sharagasystem.mapper;

import com.example.sharagasystem.model.StaffDetails;
import com.example.sharagasystem.model.dto.response.staff.StaffDetailsLowInfoResponseDto;
import com.example.sharagasystem.model.dto.response.staff.StaffResponseDto;
import org.springframework.stereotype.Component;

@Component
public class StaffDetailsMapper {
    public StaffResponseDto mapToResponseDto(StaffDetails staffDetails){
        if(staffDetails == null) {
            return null;
        }
        StaffResponseDto staffResponseDto = new StaffResponseDto();
        staffResponseDto.setId(staffDetails.getId());
        staffResponseDto.setFirstName(staffDetails.getFirstName());
        staffResponseDto.setLastName(staffDetails.getLastName());
        staffResponseDto.setEmail(staffDetails.getEmail());
        staffResponseDto.setPhoneNumber(staffDetails.getPhoneNumber());
        staffResponseDto.setRole(staffDetails.getRole());
        staffResponseDto.setStaffType(staffDetails.getType());
        return staffResponseDto;
    }

    public StaffDetailsLowInfoResponseDto mapToLowInfoResponseDto(StaffDetails staffDetails){
        if(staffDetails == null) {
            return null;
        }
        StaffDetailsLowInfoResponseDto staffDetailsResponseDto = new StaffDetailsLowInfoResponseDto();
        staffDetailsResponseDto.setId(staffDetails.getId());
        staffDetailsResponseDto.setFirstName(staffDetails.getFirstName());
        staffDetailsResponseDto.setLastName(staffDetails.getLastName());
        staffDetailsResponseDto.setEmail(staffDetails.getEmail());
        staffDetailsResponseDto.setPhoneNumber(staffDetails.getPhoneNumber());
        staffDetailsResponseDto.setRole(staffDetails.getRole().getRoleName().name());
        return staffDetailsResponseDto;
    }
}
