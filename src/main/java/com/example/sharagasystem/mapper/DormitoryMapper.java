package com.example.sharagasystem.mapper;

import com.example.sharagasystem.dto.response.DormitoryResponseDto;
import com.example.sharagasystem.model.Dormitory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DormitoryMapper {
    private final UserMapper userMapper;

    public DormitoryResponseDto mapToDormitoryResponseDto(Dormitory dormitory){
        DormitoryResponseDto responseDto = new DormitoryResponseDto();
        responseDto.setId(dormitory.getId());
        responseDto.setName(dormitory.getName());
        responseDto.setAddress(dormitory.getAddress());
        responseDto.setCapacity(dormitory.getCapacity());
        responseDto.setAmountOfRoom(dormitory.getRooms() != null ? dormitory.getRooms().size() : 0);
        responseDto.setCommandant(userMapper.toUserResponseDto(dormitory.getCommandant()));
        return responseDto;
    }
}
