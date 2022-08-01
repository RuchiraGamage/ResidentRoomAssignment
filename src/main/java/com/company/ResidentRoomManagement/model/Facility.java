package com.company.ResidentRoomManagement.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * Entity for Facility
 */

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_facility")
@Data
@Builder
public class Facility extends BaseEntity {

    private String name;

    private String code;

    private String description;

    private double price;

    @OneToOne(fetch = FetchType.EAGER)
    private User facilityAdmin;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "tbl_facility_room", joinColumns = {@JoinColumn(name = "facility_id", referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name = "room_id", referencedColumnName = "id")})
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<Room> rooms;

    public Facility(String name, String code, String description, double price) {
        this.name = name;
        this.code = code;
        this.description = description;
        this.price = price;
    }
}
