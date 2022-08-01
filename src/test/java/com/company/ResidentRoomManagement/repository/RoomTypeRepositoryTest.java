package com.company.ResidentRoomManagement.repository;

import com.company.ResidentRoomManagement.model.RoomType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class RoomTypeRepositoryTest {

    @Autowired
    private RoomTypeRepository roomTypeRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @BeforeEach
    void setUp() {
        RoomType roomType = RoomType.builder().roomTypeName("Large").build();
        testEntityManager.persist(roomType);
    }

    @Test
    public void whenFindById_thenReturnRoomType(){
        RoomType roomType = roomTypeRepository.findById(1L).get();
        assertEquals("Large",roomType.getRoomTypeName());
    }
}