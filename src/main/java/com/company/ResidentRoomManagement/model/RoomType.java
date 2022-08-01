package com.company.ResidentRoomManagement.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
public class RoomType extends BaseEntity {

    @NotNull(message = "room type name can not be null")
    @NotEmpty(message = "room type name can not be empty")
    private String roomTypeName;

    private String description;

    private double price;

    @OneToMany(mappedBy = "roomType", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = {"roomType"}, allowSetters = true)
    private List<Room> rooms;

}
