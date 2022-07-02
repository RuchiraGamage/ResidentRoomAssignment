package com.company.ResidentRoomManagement.model.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class FacilityDTO implements Serializable {

    private String name;

    private String code;

    private String description;

    private double price;

    private long facilityAdmin;
}
