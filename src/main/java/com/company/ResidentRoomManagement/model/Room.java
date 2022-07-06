package com.company.ResidentRoomManagement.model;

import com.company.ResidentRoomManagement.model.DTO.RoomDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
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
 * Entity for Room
 */

@Getter
@Setter
@Entity
@Table(name = "tbl_room")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Room extends BaseEntity {
    private String roomCode;

    private int status;

    private String description;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "room_type_id")
    private RoomType roomType;

    @JsonIgnoreProperties({"rooms", "facilityAdmin"})
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "rooms")
    private List<Facility> facilities = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,orphanRemoval = true, mappedBy = "room")
    private List<Resident> residents = new ArrayList<>();

    public Room(){}

    public Room(RoomDTO roomDTO) {
        this.roomCode = roomDTO.getRoomCode();
        this.status = roomDTO.getStatus();
        this.description = roomDTO.getDescription();
    }
}
