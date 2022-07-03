package com.company.ResidentRoomManagement.controller.impl;

import com.company.ResidentRoomManagement.controller.FacilityController;
import com.company.ResidentRoomManagement.model.DTO.FacilityDTO;
import com.company.ResidentRoomManagement.model.Facility;
import com.company.ResidentRoomManagement.service.FacilityService;
import com.company.ResidentRoomManagement.util.ApiResponse;
import com.company.ResidentRoomManagement.util.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class FacilityControllerImpl implements FacilityController {

    @Autowired
    FacilityService facilityService;

    @Override
    public ResponseEntity<ApiResponse> create(FacilityDTO facility) {
        ResponseEntity<ApiResponse> response = null;

        try {
            String facilityCode = UUID.randomUUID().toString();
            facility.setCode(facilityCode);

            Facility savedInstance = facilityService.saveNewFacility(facility);
            response = new ResponseEntity<>(new ApiResponse(true, "saved facility successfully", savedInstance), HttpStatus.CREATED);

        } catch (Exception e) {
            response = new ResponseEntity<>(new ApiResponse(Error.ERROR, false, "facility save failed", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }

    @Override
    public ResponseEntity<ApiResponse> getAllByFacilityAdminId(Long adminId) {
        ResponseEntity<ApiResponse> response = null;

        try {
            List<Facility> facilities = facilityService.getAllFacilitiesByAdminId(adminId);
            response = new ResponseEntity<>(new ApiResponse(true, "All facilities", facilities), HttpStatus.OK);

        } catch (Exception e) {
            response = new ResponseEntity<>(new ApiResponse(false, "all facility load failed", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }

    @Override
    public ResponseEntity<ApiResponse> addRoomToFacility(Long facilityId, Long adminId, Long roomId) {
        ResponseEntity<ApiResponse> response = null;

        try {
            Facility savedInstance = facilityService.addRoomToFacility(adminId, roomId,facilityId);
            response = new ResponseEntity<>(new ApiResponse(true, "Room added to facility", savedInstance), HttpStatus.OK);

        } catch (Exception e) {
            response = new ResponseEntity<>(new ApiResponse(false, "Fail to add room to facility", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return response;
    }

}
