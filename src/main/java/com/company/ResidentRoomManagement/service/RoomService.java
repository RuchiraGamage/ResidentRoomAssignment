package com.company.ResidentRoomManagement.service;

import com.company.ResidentRoomManagement.model.DTO.RoomDTO;
import com.company.ResidentRoomManagement.model.Room;

import java.util.List;

public interface RoomService {
    Room createRoom(RoomDTO roomDTO);

    Room addResidentToRoom(Long roomId, Long residentId);

    Room removeResidentFromRoom(Long roomId, Long residentId);

    List<Room> getAllRoom();
}
