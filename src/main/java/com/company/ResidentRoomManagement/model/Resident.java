package com.company.ResidentRoomManagement.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Entity for Resident
 */

@Entity
@Table(name = "tbl_resident")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Resident extends BaseEntity {

    @Id
    @SequenceGenerator(name = "resident_sequence",sequenceName = "resident_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "resident_sequence" )
    private long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "room_id")
    @JsonIgnoreProperties(value = {"residents"})
    private Room room;
}
