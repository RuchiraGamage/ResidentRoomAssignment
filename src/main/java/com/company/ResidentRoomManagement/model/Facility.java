package com.company.ResidentRoomManagement.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * Entity for Facility
 */

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_facility")
@Data
@Builder
public class Facility extends BaseEntity {

    @Id
    @SequenceGenerator(
            name = "facility_sequence",
            sequenceName = "facility_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "facility_sequence")
    private long id;

    private String name;

    private String code;

    private String description;

    private double price;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_di", referencedColumnName = "id")
    private User facilityAdmin;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "tbl_facility_room", joinColumns = {@JoinColumn(name = "facility_id", referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name = "room_id", referencedColumnName = "id")})
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<Room> rooms;

    public Facility(String name, String code, String description, double price) {
        this.name = name;
        this.code = code;
        this.description = description;
        this.price = price;
    }
}
