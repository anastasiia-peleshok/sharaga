package com.example.sharagasystem.controller;

import com.example.sharagasystem.model.Gender;
import com.example.sharagasystem.model.Room;
import com.example.sharagasystem.model.dto.request.RoomRequestDto;
import com.example.sharagasystem.model.dto.response.room.RoomListLowInfoResponseDto;
import com.example.sharagasystem.model.dto.response.room.RoomListResponseDto;
import com.example.sharagasystem.service.RoomService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/rooms")
public class RoomController {
    private final RoomService roomService;


    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public Room createNewRoom(@RequestBody RoomRequestDto roomRequest){
        log.info("Entering POST /rooms");
        return roomService.create(roomRequest);
    }

    @PatchMapping("/{roomId}/dormitory/{dormitoryId}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public void assignRoomToDormitory(@PathVariable UUID roomId,
                                      @PathVariable UUID dormitoryId){
        log.info("Entering PATCH /rooms/{}/dormitory/{}", roomId, dormitoryId);
        roomService.assignRoomToDormitory(roomId, dormitoryId);
    }


    @GetMapping("/dormitory/{dormitoryId}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public Page<RoomListResponseDto> findAllByDormitory(@PathVariable UUID dormitoryId,
                                                        @RequestParam(required = false, defaultValue = "") String textToSearch,
                                                        Pageable pageable){
        log.info("Entering GET /rooms/dormitory/{} with dormitory id {}", dormitoryId);
        return roomService.findAllByDormitory(dormitoryId, textToSearch, pageable);
    }

    @GetMapping("/dormitory/{dormitoryId}/gender")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public Page<RoomListLowInfoResponseDto> findAllByGender(@PathVariable UUID dormitoryId,
                                                            @RequestParam Gender gender,
                                                            @RequestParam(required = false, defaultValue = "") String textToSearch,
                                                             Pageable pageable){
        log.info("Entering POST /rooms/dormitory/{dormitoryId}/gender with dormitory id {}", dormitoryId);
        return roomService.findAllWithFreePlaces(dormitoryId, gender, textToSearch, pageable);
    }
}
