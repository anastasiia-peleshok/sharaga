package com.example.sharagasystem.mapper;

import com.example.sharagasystem.dto.resident.ResidentRequestDto;
import com.example.sharagasystem.dto.resident.ResidentResponseDto;
import com.example.sharagasystem.model.ResidentDetails;
import com.example.sharagasystem.model.Role;
import com.example.sharagasystem.config.MapperConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class, uses = {
        RoomMapper.class, DormitoryMapper.class, FurnitureMapper.class
})
public interface ResidentMapper {
    @Mapping(target = "roomNumber", source = "room.number", ignore = true)
    @Mapping(target = "dormitoryName", source = "dormitory.name", ignore = true)
    @Mapping(target = "role", source = "role.roleName")
    ResidentResponseDto toDto(ResidentDetails residentDetails);

    @Mapping(target = "room", ignore = true)
    @Mapping(target = "dormitory", ignore = true)
    @Mapping(target = "role", source = "role")
    ResidentDetails toEntity(ResidentRequestDto residentResponseDto);

    default String mapRoleToString(Role role) {
        return role != null ? role.getRoleName().name() : null;
    }

    default Role mapStringToRole(String roleName) {
        return roleName != null ? (roleName.equals(Role.RoleName.ADMIN) ? new Role(Role.RoleName.ADMIN) : new Role(Role.RoleName.USER)) : null; // Assumes Role has a constructor Role(String name)
    }
}
