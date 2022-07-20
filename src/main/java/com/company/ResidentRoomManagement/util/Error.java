package com.company.ResidentRoomManagement.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Error {
    public static final int SUCCESS = 1;
    public static final int ERROR = -1;
    public static final int WARNING = 0;

    private String errorMessage;
    private int errorCode = 1;

    public Error() {
    }

    public Error(int errorCode) {
        this.errorCode = errorCode;
    }

    public Error(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorStatus() {
        if (this.errorCode == SUCCESS) {
            return "SUCCESS";
        } else if (this.errorCode == ERROR) {
            return "ERROR";
        } else {
            return "WARNING";
        }
    }
}
