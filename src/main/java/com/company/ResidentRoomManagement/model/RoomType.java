package com.company.ResidentRoomManagement.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Entity for Room Type
 */

@NoArgsConstructor
@Entity
@Table(name = "tbl_room_type")
@Data
@Builder
@AllArgsConstructor
@ToString(exclude = "rooms")
public class RoomType extends BaseEntity {

    @Id
    @SequenceGenerator(name = "room_type_sequence",sequenceName = "room_type_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "room_type_sequence" )
    private long id;

    @NotNull(message = "room type name can not be null")
    @NotEmpty(message = "room type name can not be empty")
    private String roomTypeName;

    private String description;

    private double price;

    @OneToMany(mappedBy = "roomType", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = {"roomType"}, allowSetters = true)
    private List<Room> rooms;

}
