package com.company.ResidentRoomManagement.controller;

import com.company.ResidentRoomManagement.model.RoomType;
import com.company.ResidentRoomManagement.util.ApiResponse;
import com.company.ResidentRoomManagement.util.Constants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping(value ="/roomType")
public interface RoomTypeController {
    @Tag( description = "Room Type based operations", name = Constants.ROOM_TYPE_APIS )
    @Operation( summary = Constants.ROOM_TYPE_APIS + "register new room Type ",
            description = "register new room type",
            responses = { @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "200", description = "register new room type" ) } )
    @PostMapping
    ResponseEntity<ApiResponse> register(@Valid @RequestBody RoomType roomType);
}
