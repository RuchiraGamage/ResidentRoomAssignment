package com.company.ResidentRoomManagement.repository;

import com.company.ResidentRoomManagement.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
}
