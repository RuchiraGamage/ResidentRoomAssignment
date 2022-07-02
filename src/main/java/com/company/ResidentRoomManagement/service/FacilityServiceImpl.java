package com.company.ResidentRoomManagement.service;

import com.company.ResidentRoomManagement.model.DTO.FacilityDTO;
import com.company.ResidentRoomManagement.model.Facility;
import com.company.ResidentRoomManagement.repository.FacilityRepository;
import com.company.ResidentRoomManagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacilityServiceImpl implements FacilityService {
    @Autowired
    private FacilityRepository facilityRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Facility saveNewFacility(FacilityDTO facility) {
        Facility fac = new Facility(facility.getName(),facility.getCode(),facility.getDescription(),facility.getPrice());
        fac.setFacilityAdmin(userRepository.findById(facility.getFacilityAdmin()).get());
        return facilityRepository.save(fac);
    }

    @Override
    public List<Facility> getAllFacilitiesByAdminId(Long adminId) {
        return facilityRepository.findAllByFacilityAdminId(adminId);
    }
}
