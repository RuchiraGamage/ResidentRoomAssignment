package com.company.ResidentRoomManagement.service;

import com.company.ResidentRoomManagement.model.DTO.RoomDTO;
import com.company.ResidentRoomManagement.model.Room;

public interface RoomService {
    Room createRoom(RoomDTO roomDTO);
}
