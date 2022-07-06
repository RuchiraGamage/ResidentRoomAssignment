package com.company.ResidentRoomManagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@NoArgsConstructor
@Entity
@Table(name = "tbl_facility")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Facility extends BaseEntity {

    private String name;

    private String code;

    private String description;

    private double price;

    @OneToOne
    private User facilityAdmin;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "tbl_facility_room", joinColumns = {@JoinColumn(name = "facility_id")}, inverseJoinColumns = {@JoinColumn(name = "room_id")})
    private List<Room> rooms = new ArrayList<>();

    public Facility(String name, String code, String description, double price) {
        this.name = name;
        this.code = code;
        this.description = description;
        this.price = price;
    }

    @JsonIgnore
    public void addRoom(Room room) {
        this.rooms.add(room);
        room.getFacilities().add(this);
    }

    @JsonIgnore
    public void deleteRooms(Room r) {
        Room rm = this.rooms.stream().filter(room -> room.id == r.getId()).findFirst().orElse(null);
        if (rm != null){
            this.rooms.remove(rm);
            rm.getFacilities().remove(this);
        }
    }
}
