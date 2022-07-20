package com.company.ResidentRoomManagement.util;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse extends Error implements Serializable {
    private boolean success;
    private String message;
    private Object body;

    public ApiResponse(boolean success, String message, Object body) {
        this.success = success;
        this.message = message;
        this.body = body;
    }

    public ApiResponse(int errorCode, boolean success, String message, Object body) {
        super(errorCode);
        this.success = success;
        this.message = message;
        this.body = body;
    }

    public ApiResponse(int errorCode, String errorMessage, boolean success, String message, Object body) {
        super(errorCode, errorMessage);
        this.success = success;
        this.message = message;
        this.body = body;
    }
}
