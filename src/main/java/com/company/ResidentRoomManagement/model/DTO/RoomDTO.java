package com.company.ResidentRoomManagement.model.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RoomDTO implements Serializable {

    private long id;

    private String roomCode;

    private int status;

    private String description;

    private long roomType;
}
