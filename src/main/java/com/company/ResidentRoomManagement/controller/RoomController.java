package com.company.ResidentRoomManagement.controller;

import com.company.ResidentRoomManagement.model.DTO.RoomDTO;
import com.company.ResidentRoomManagement.util.ApiResponse;
import com.company.ResidentRoomManagement.util.Constants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = "/room")
public interface RoomController {

    @Tag( description = "User based operations", name = Constants.ROOM_APIS )
    @Operation( summary = Constants.ROOM_APIS + "register new room",
            description = "register new room",
            responses = { @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "200", description = "register new room" ) } )
    @PostMapping
    ResponseEntity<ApiResponse> register(@RequestBody RoomDTO room);
}
