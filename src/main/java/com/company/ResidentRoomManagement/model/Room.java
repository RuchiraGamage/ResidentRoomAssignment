package com.company.ResidentRoomManagement.model;

import com.company.ResidentRoomManagement.model.DTO.RoomDTO;
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
@ToString
@NoArgsConstructor
@Entity
@Table(name = "tbl_room")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Room extends BaseEntity {
    private String roomCode;

    private int status;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "room_type_id")
    private RoomType roomType;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "tbl_room_facility", joinColumns = {@JoinColumn(name = "room_id")}, inverseJoinColumns = {@JoinColumn(name = "facility_id")})
    private List<Facility> facilities = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,orphanRemoval = true, mappedBy = "room")
    private List<Resident> residents = new ArrayList<>();

    public Room(RoomDTO roomDTO) {
        this.roomCode = roomDTO.getRoomCode();
        this.status = roomDTO.getStatus();
        this.description = roomDTO.getDescription();
    }
}
