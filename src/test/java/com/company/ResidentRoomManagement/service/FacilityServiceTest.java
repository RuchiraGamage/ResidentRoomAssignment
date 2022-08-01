package com.company.ResidentRoomManagement.service;

import com.company.ResidentRoomManagement.model.Facility;
import com.company.ResidentRoomManagement.model.User;
import com.company.ResidentRoomManagement.repository.FacilityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FacilityServiceTest {

    @Autowired
    private FacilityService facilityService;

    @MockBean
    private FacilityRepository facilityRepository;

    @BeforeEach
    void setUp() {
        Facility facility = Facility.builder().name("fac1").build();
        List<Facility> facilities = new ArrayList<>(Collections.singletonList(facility));
        Mockito.when(facilityRepository.findAllByFacilityAdminId(1)).thenReturn(facilities);
    }

    @Test
    @DisplayName("Get facilities when given the admin Id")
    @Disabled
    void whenInputAdminId_then_allRelatedFacilitiesShouldReturn() {
        Long adminId = 1L;
        List<Facility> facilityList = facilityService.getAllFacilitiesByAdminId(adminId);
        assertEquals(1,facilityList.size());
    }
}