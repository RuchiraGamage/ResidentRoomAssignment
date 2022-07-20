package com.company.ResidentRoomManagement.controller;

import com.company.ResidentRoomManagement.model.DTO.RoomDTO;
import com.company.ResidentRoomManagement.util.ApiResponse;
import com.company.ResidentRoomManagement.util.Constants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/room")
public interface RoomController {

    @Tag( description = "Room based operations", name = Constants.ROOM_APIS )
    @Operation( summary = Constants.ROOM_APIS + "register new room",
            description = "register new room",
            responses = { @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "200", description = "register new room" ) } )
    @PostMapping
    ResponseEntity<ApiResponse> register(@RequestBody RoomDTO room);

    @Tag( description = "Room based operations", name = Constants.ROOM_APIS)
    @Operation(summary = Constants.ROOM_APIS + "add resident to room"
            ,description = "add resident to room"
            ,responses = {@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",description ="assign resident to room" )})
    @PostMapping(value = "{room_id}/assign/{resident_id}")
    ResponseEntity<ApiResponse> addResidentToRoom(@PathVariable(value = "room_id") Long roomId
            ,@PathVariable(value = "resident_id") Long residentId);

    @Tag( description = "Room based operations", name = Constants.ROOM_APIS)
    @Operation(summary = Constants.ROOM_APIS + "remove resident from room"
            ,description = "remove resident from room"
            ,responses = {@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",description ="removed resident from room" )})
    @PostMapping(value = "{room_id}/remove/{resident_id}")
    ResponseEntity<ApiResponse> removeResidentFromRoom(@PathVariable(value = "room_id") Long roomId
            ,@PathVariable(value = "resident_id") Long residentId);

    @Tag( description = "Room based operations", name = Constants.ROOM_APIS )
    @Operation( summary = Constants.ROOM_APIS + "Get all rooms with resident assignments",
            description = "Get all rooms with resident assignments",
            responses = { @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "200", description = "Get all rooms with resident assignments" ) } )
    @GetMapping(value = "getAll")
    ResponseEntity<ApiResponse> getAllRooms();

}
