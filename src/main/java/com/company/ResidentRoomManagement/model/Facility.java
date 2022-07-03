package com.company.ResidentRoomManagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity for Facility
 */

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "tbl_facility")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Facility extends BaseEntity {

    private String name;

    private String code;

    private String description;

    private double price;

    @OneToOne
    private User facilityAdmin;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "facilities")
    private List<Room> rooms = new ArrayList<>();

    public Facility(String name, String code, String description, double price) {
        this.name = name;
        this.code = code;
        this.description = description;
        this.price = price;
    }
}
