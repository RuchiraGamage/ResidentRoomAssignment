package com.company.ResidentRoomManagement.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * Entity for Resident
 */

@Getter
@Setter
@Entity
@Table(name = "tbl_resident")
public class Resident extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "room_id")
    private Room room;

    public Resident(){}
}
