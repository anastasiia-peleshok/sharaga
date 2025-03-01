package com.example.sharagasystem.controller;

import com.example.sharagasystem.dto.RoomRequestDto;
import com.example.sharagasystem.model.Room;
import com.example.sharagasystem.service.RoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/rooms")
public class RoomController {
    private final RoomService roomService;


    @PostMapping
    public Room createNewRoom(@RequestBody RoomRequestDto roomRequest){
        log.info("Entering POST /rooms");
        return roomService.create(roomRequest);
    }
}
