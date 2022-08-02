package com.company.ResidentRoomManagement.repository;

import com.company.ResidentRoomManagement.model.Resident;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ResidentRepositoryTest {

    @Autowired
    private ResidentRepository residentRepository;

    @Test
    public void findResidentByPartOfHisFirstName(){
        List<Resident> residents = residentRepository.findByUserFirstNameContaining("testName");
    }

    @Test
    public void findByUserByLastName(){
        Resident resident = residentRepository.findByUserLastName("testLastName");
    }

    @Test
    public void findByUserId(){
        Resident resident = residentRepository.findByUserId(1L);
    }
}