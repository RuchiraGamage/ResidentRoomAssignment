package com.company.ResidentRoomManagement.controller.impl;

import com.company.ResidentRoomManagement.controller.RoomTypeController;
import com.company.ResidentRoomManagement.model.RoomType;
import com.company.ResidentRoomManagement.service.RoomTypeService;
import com.company.ResidentRoomManagement.util.ApiResponse;
import com.company.ResidentRoomManagement.util.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoomTypeControllerImpl implements RoomTypeController {

    @Autowired
    private RoomTypeService roomTypeService;

    @Override
    public ResponseEntity<ApiResponse> register(RoomType roomType) {
        ResponseEntity<ApiResponse> response = null;

        try {
            RoomType savedInstance = roomTypeService.createNewRoomType(roomType);
            response = new ResponseEntity<>(new ApiResponse(true,"Room type created", roomType), HttpStatus.OK);
        } catch (Exception e){
            response = new ResponseEntity<>(new ApiResponse(Error.ERROR, false,"Fail saving new room type", roomType), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }
}
