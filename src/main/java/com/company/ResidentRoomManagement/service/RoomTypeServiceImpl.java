package com.company.ResidentRoomManagement.service;

import com.company.ResidentRoomManagement.model.RoomType;
import com.company.ResidentRoomManagement.repository.RoomTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomTypeServiceImpl implements RoomTypeService {

    @Autowired
    private RoomTypeRepository roomTypeRepository;

    @Override
    public RoomType createNewRoomType(RoomType roomType) {
        return roomTypeRepository.saveAndFlush(roomType);
    }
}
