//package com.example.sharagasystem.mapper;
//
//import com.example.sharagasystem.dto.resident.ResidentRequestDto;
//import com.example.sharagasystem.dto.resident.ResidentResponseDto;
//import com.example.sharagasystem.model.ResidentDetails;
//import com.example.sharagasystem.model.Role;
//import com.example.sharagasystem.security.repository.RoleRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Component;
//
//@RequiredArgsConstructor
//@Component
//public class ResidentMapper {
//
//    private final RoleRepository roleRepository;
//
//    public ResidentDetails toEntity(ResidentRequestDto dto) {
//        ResidentDetails residentDetails = new ResidentDetails();
//        if (dto.getFirstName() != null) {
//            residentDetails.setFirstName(dto.getFirstName());
//        }
//        if (dto.getLastName() != null) {
//            residentDetails.setLastName(dto.getLastName());
//        }
//        if (dto.getEmail() != null) {
//            residentDetails.setEmail(dto.getEmail());
//        }
//        if (dto.getRole() != null) {
//            Role.RoleName roleName = Role.RoleName.valueOf(dto.getRole());
//            Role role = roleRepository.findByRoleName(roleName)
//                    .orElseThrow(() -> new RuntimeException("Role not found: " + roleName));
//            residentDetails.setRole(role);
//        }
//        if (dto.getPhoneNumber() != null) {
//            residentDetails.setPhoneNumber(dto.getPhoneNumber());
//        }
//        return residentDetails;
//    }
//
//    public ResidentResponseDto toDTO(ResidentDetails user) {
//        ResidentResponseDto residentResponseDto = new ResidentResponseDto();
//        residentResponseDto.setFirstName(user.getFirstName());
//        residentResponseDto.setLastName(user.getLastName());
//        residentResponseDto.setEmail(user.getEmail());
//        residentResponseDto.setPhoneNumber(user.getPhoneNumber());
//        residentResponseDto.setRole(user.getRole().getRoleName().toString());
//        return residentResponseDto;
//    }
//}
