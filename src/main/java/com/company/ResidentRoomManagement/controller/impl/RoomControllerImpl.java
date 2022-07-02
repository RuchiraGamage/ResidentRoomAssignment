package com.company.ResidentRoomManagement.controller.impl;

import com.company.ResidentRoomManagement.controller.RoomController;
import com.company.ResidentRoomManagement.model.DTO.RoomDTO;
import com.company.ResidentRoomManagement.model.Room;
import com.company.ResidentRoomManagement.service.RoomService;
import com.company.ResidentRoomManagement.util.ApiResponse;
import com.company.ResidentRoomManagement.util.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class RoomControllerImpl implements RoomController {

    @Autowired
    private RoomService roomService;

    @Override
    public ResponseEntity<ApiResponse> register(RoomDTO room) {
        ResponseEntity<ApiResponse> response;

        try {
            String roomCode = UUID.randomUUID().toString();
            room.setRoomCode(roomCode);

            Room savedInstance = roomService.createRoom(room);
            response = new ResponseEntity<>(new ApiResponse(true, "room saved successfully",savedInstance), HttpStatus.CREATED);

        }catch (Exception e){
            response = new ResponseEntity<>(new ApiResponse(Error.ERROR,false, "fail room save",null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }
}
