package com.company.ResidentRoomManagement.service;

import com.company.ResidentRoomManagement.model.DTO.RoomDTO;
import com.company.ResidentRoomManagement.model.Room;
import com.company.ResidentRoomManagement.repository.RoomRepository;
import com.company.ResidentRoomManagement.repository.RoomTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomServiceImpl implements RoomService{

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private RoomTypeRepository roomTypeRepository;

    @Override
    public Room createRoom(RoomDTO roomDTO) {

        Room room = new Room(roomDTO);
        room.setRoomType(roomTypeRepository.findById(roomDTO.getRoomType()).get());
        return roomRepository.save(room);
    }
}
