package com.company.ResidentRoomManagement.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * Entity for Room Type
 */

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tbl_room_type")
public class RoomType extends BaseEntity {

    private String roomTypeName;

    private String description;

    private double price;

    @OneToMany(mappedBy = "roomType", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = {"roomType"}, allowSetters = true)
    private List<Room> rooms;

}
