package com.company.ResidentRoomManagement.repository;

import com.company.ResidentRoomManagement.model.Resident;
import com.company.ResidentRoomManagement.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
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

    @Test
    public void createResident(){
        User user = User.builder().userGroupId(2).email("testemai@gmail.com").firstName("testResident").build();
        Resident resident = Resident.builder().user(user).build();

        residentRepository.save(resident);
    }
}