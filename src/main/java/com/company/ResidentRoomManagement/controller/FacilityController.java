package com.company.ResidentRoomManagement.controller;

import com.company.ResidentRoomManagement.model.DTO.FacilityDTO;
import com.company.ResidentRoomManagement.util.ApiResponse;
import com.company.ResidentRoomManagement.util.Constants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/facility")
public interface FacilityController {

    @Tag( description = "Facility based operations", name = Constants.FACILITY_APIS )
    @Operation( summary = Constants.FACILITY_APIS + "register new facility",
            description = "register new facility",
            responses = { @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "200", description = "register new facility" ) } )
    @PostMapping
    ResponseEntity<ApiResponse> create(@RequestBody FacilityDTO facility);

    @Tag( description = "Facility based operations", name = Constants.FACILITY_APIS )
    @Operation( summary = Constants.FACILITY_APIS + "get all facilities by admin id",
            description = "get all facilities by admin id",
            responses = { @io.swagger.v3.oas.annotations.responses.ApiResponse( responseCode = "200", description = "get all facilities by admin id" ) } )
    @GetMapping(value = "/all/{adminId}")
    ResponseEntity<ApiResponse> getAllByFacilityAdminId(@PathVariable(value = "adminId") Long adminId);
}
