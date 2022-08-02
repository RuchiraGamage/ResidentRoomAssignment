package com.company.ResidentRoomManagement.model;

import com.company.ResidentRoomManagement.model.DTO.RoomDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * Entity for Room
 */

@Entity
@Table(name = "tbl_room")
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room extends BaseEntity {

    @Id
    @SequenceGenerator(name = "room_sequence",sequenceName = "room_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "room_sequence" )
    private long id;

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

    public Room(RoomDTO roomDTO) {
        this.roomCode = roomDTO.getRoomCode();
        this.status = roomDTO.getStatus();
        this.description = roomDTO.getDescription();
    }
}
