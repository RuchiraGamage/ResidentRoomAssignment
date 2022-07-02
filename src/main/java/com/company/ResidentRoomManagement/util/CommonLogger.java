package com.company.ResidentRoomManagement.util;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CommonLogger {
    private static final Logger logger = Logger.getLogger(Globals.RESIDENT_API_LOGGER);

    public static void log(Exception e) {
        logger.log(Level.WARNING, e.getMessage());
    }
}
