package com.company.ResidentRoomManagement.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * Base level for all entity classes
 */

@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity implements Serializable {

    /**
     * Auto generated unique ID of entity
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;
}