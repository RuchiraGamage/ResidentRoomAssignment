package com.company.ResidentRoomManagement.repository;

import com.company.ResidentRoomManagement.model.RoomType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomTypeRepository extends JpaRepository<RoomType, Long> {

    Page<RoomType> findByRoomTypeNameContaining(String text, Pageable pageable);
}
