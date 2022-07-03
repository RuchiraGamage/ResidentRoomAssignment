package com.company.ResidentRoomManagement.service;

import com.company.ResidentRoomManagement.model.DTO.FacilityDTO;
import com.company.ResidentRoomManagement.model.Facility;

import java.sql.SQLException;
import java.util.List;

public interface FacilityService {
    Facility saveNewFacility(FacilityDTO facility) throws SQLException;

    List<Facility> getAllFacilitiesByAdminId(Long adminId);

    Facility addRoomToFacility(Long adminId, Long roomId, Long facilityId);
}
