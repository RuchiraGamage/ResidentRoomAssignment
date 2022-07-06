package com.company.ResidentRoomManagement.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;

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

}
