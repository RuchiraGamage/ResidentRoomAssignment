package com.company.ResidentRoomManagement.service;

import com.company.ResidentRoomManagement.model.DTO.RoomDTO;
import com.company.ResidentRoomManagement.model.Resident;
import com.company.ResidentRoomManagement.model.Room;
import com.company.ResidentRoomManagement.repository.ResidentRepository;
import com.company.ResidentRoomManagement.repository.RoomRepository;
import com.company.ResidentRoomManagement.repository.RoomTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class RoomServiceImpl implements RoomService{

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private RoomTypeRepository roomTypeRepository;

    @Autowired
    private ResidentRepository residentRepository;

    @Override
    public Room createRoom(RoomDTO roomDTO) {

        Room room = new Room(roomDTO);
        room.setRoomType(roomTypeRepository.findById(roomDTO.getRoomType()).get());
        return roomRepository.save(room);
    }

    @Override
    public Room addResidentToRoom(Long roomId, Long residentId) {
        Room room = roomRepository.findById(roomId).orElseThrow(() -> new EntityNotFoundException("room doesn't exists"));
        Resident resident = residentRepository.findById(residentId).orElseThrow(() -> new EntityNotFoundException("resident doesn't exists"));

        resident.setRoom(room);
        room.getResidents().add(resident);

        return roomRepository.saveAndFlush(room);
    }

    @Override
    public Room removeResidentFromRoom(Long roomId, Long residentId) {
        Room room = roomRepository.findById(roomId).orElseThrow(() -> new EntityNotFoundException("room doesn't exists"));
        Resident resident = residentRepository.findById(residentId).orElseThrow(() -> new EntityNotFoundException("resident doesn't exists"));

        resident.setRoom(null);
        room.getResidents().remove(resident);
        return roomRepository.saveAndFlush(room);
    }

    @Override
    public List<Room> getAllRoom() {
        return roomRepository.findAll();
    }
}
