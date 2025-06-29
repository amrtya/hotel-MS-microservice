package com.hotelms.backend.roommodule.controller;

import com.hotelms.backend.roommodule.dto.AddRoomDTO;
import com.hotelms.backend.roommodule.dto.ResponseDTO;
import com.hotelms.backend.roommodule.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(path = "/room")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @PostMapping("/add")
    public ResponseDTO addRoom(@RequestBody AddRoomDTO request) {
        return roomService.addRoom(request);
    }

    @DeleteMapping("/remove")
    public ResponseDTO removeRoom(@RequestParam("roomId") String roomId) {
        return roomService.removeRoom(roomId);
    }
}
