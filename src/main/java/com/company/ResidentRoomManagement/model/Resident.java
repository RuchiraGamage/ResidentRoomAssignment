package com.company.ResidentRoomManagement.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

/**
 * Entity for Resident
 */

@Entity
@Table(name = "tbl_resident")
@Data
public class Resident extends BaseEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "room_id")
    @JsonIgnoreProperties(value = {"residents"})
    private Room room;

    public Resident(){}
}
