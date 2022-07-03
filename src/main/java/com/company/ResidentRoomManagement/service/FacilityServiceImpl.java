package com.company.ResidentRoomManagement.service;

import com.company.ResidentRoomManagement.model.DTO.FacilityDTO;
import com.company.ResidentRoomManagement.model.Facility;
import com.company.ResidentRoomManagement.model.Room;
import com.company.ResidentRoomManagement.repository.FacilityRepository;
import com.company.ResidentRoomManagement.repository.RoomRepository;
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

    @Autowired
    private RoomRepository roomRepository;

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

    @Override
    public Facility addRoomToFacility(Long adminId, Long roomId, Long facilityId) {
        Facility fac = facilityRepository.findById(facilityId).get();
        Room room = roomRepository.getById(roomId);
        room.getFacilities().add(fac);
        fac.getRooms().add(room);

        return facilityRepository.saveAndFlush(fac);
    }

}
