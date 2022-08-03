package com.company.ResidentRoomManagement.repository;

import com.company.ResidentRoomManagement.model.RoomType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

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

    @Test
    @Disabled
    public void findAllPagination(){
        Pageable firstPageWithThreeRecords = PageRequest.of(0,3, Sort.by("roomTypeName"));
        Pageable secondPageWithThreeRecords = PageRequest.of(1,2);
        List<RoomType> roomTypeList = roomTypeRepository.findAll(firstPageWithThreeRecords).getContent();

    }
}