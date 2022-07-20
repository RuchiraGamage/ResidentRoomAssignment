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
