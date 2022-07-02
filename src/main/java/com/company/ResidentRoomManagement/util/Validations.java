package com.company.ResidentRoomManagement.util;

import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Check whether the given filed value is valid or not
 */

@NoArgsConstructor
public class Validations {
    /**
     * Check whether the given filed value is valid or not
     * @param value Value
     * @return present or not
     */
    public static boolean isPresent(Object value){
        return value != null && !String.valueOf(value).isEmpty();
    }

    /**
     * Check whether the given long value is valid or not
     * @param value Value
     * @return present or not
     */
    public static boolean isPresent(long value){
        return value > 0;
    }

    /**
     * check a list is valid or not
     * @param value Value
     * @return present or not
     */
    public static boolean isPresent(List value){
        return value != null && !value.isEmpty();
    }
}
