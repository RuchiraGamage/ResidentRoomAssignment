package com.company.ResidentRoomManagement.controller;

import com.company.ResidentRoomManagement.model.BaseEntity;
import com.company.ResidentRoomManagement.util.Globals;
import com.company.ResidentRoomManagement.util.Validations;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * upper level of interface of controller
 * @param <Entity> Entity type selected
 */

@CrossOrigin(origins = Globals.ALLOWED_ORIGINS)
public interface BaseController<Entity extends BaseEntity>{
    /**
     * check whether the given field value is valid or not
     * @param value value
     * @return return false if value is null or empty
     */
    default boolean isPresent(Object value){
        return Validations.isPresent(value);
    }

    /**
     * check whether the given field value is valid or not
     * @param value value
     * @return return false if value is null or empty
     */
    default boolean isPresent(long value){
        return Validations.isPresent(value);
    }

}
