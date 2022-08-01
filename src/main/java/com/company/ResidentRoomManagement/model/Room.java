package com.company.ResidentRoomManagement.model;

import com.company.ResidentRoomManagement.model.DTO.RoomDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * Entity for Room
 */

@Entity
@Table(name = "tbl_room")
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Room extends BaseEntity {

    private String roomCode;

    private int status;

    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "room_type_id")
    @JsonIgnoreProperties(value = {"rooms"})
    private RoomType roomType;

    @OneToMany(mappedBy = "room",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnoreProperties(value = {"room"})
    private List<Resident> residents;

    public Room(){}

    public Room(RoomDTO roomDTO) {
        this.roomCode = roomDTO.getRoomCode();
        this.status = roomDTO.getStatus();
        this.description = roomDTO.getDescription();
    }
}
