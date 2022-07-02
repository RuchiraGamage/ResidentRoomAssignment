package com.company.ResidentRoomManagement.repository;

import com.company.ResidentRoomManagement.model.Resident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResidentRepository extends JpaRepository<Resident, Long> {
}
