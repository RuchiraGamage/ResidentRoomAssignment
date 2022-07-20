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

import java.util.List;
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

    @Override
    public ResponseEntity<ApiResponse> addResidentToRoom(Long roomId, Long residentId) {
        ResponseEntity<ApiResponse> response;
        try {
            Room updatedInstance = roomService.addResidentToRoom(roomId,residentId);
            response = new ResponseEntity<>(new ApiResponse(true,"resident added to room",updatedInstance), HttpStatus.OK);

        }catch (Exception e){
            response = new ResponseEntity<>(new ApiResponse(Error.ERROR, false,"fail add resident to room : "+ e.getMessage(),null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    @Override
    public ResponseEntity<ApiResponse> removeResidentFromRoom(Long roomId, Long residentId) {
        ResponseEntity<ApiResponse> response;
        try {
            Room updatedInstance = roomService.removeResidentFromRoom(roomId,residentId);
            response = new ResponseEntity<>(new ApiResponse(true,"resident removed from room",updatedInstance), HttpStatus.OK);

        }catch (Exception e){
            response = new ResponseEntity<>(new ApiResponse(Error.ERROR, false,"fail remove resident from room : "+ e.getMessage(),null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    @Override
    public ResponseEntity<ApiResponse> getAllRooms() {
        ResponseEntity<ApiResponse> response;
        try {
            List<Room> rooms = roomService.getAllRoom();
            response = new ResponseEntity<>(new ApiResponse(true,"resident removed from room",rooms), HttpStatus.OK);

        }catch (Exception e){
            response = new ResponseEntity<>(new ApiResponse(Error.ERROR, false,"fail getting all rooms : "+ e.getMessage(),null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;

    }
}
