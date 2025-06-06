package com.hotelms.backend.usermodule.util;

import java.time.ZonedDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {

    public static String currentDateTime() {
        ZoneId zoneId = ZoneId.of("Asia/Kolkata");              // TODO: Dynamic Timezone creation
        ZonedDateTime currentDateTime = ZonedDateTime.now(zoneId);
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return currentDateTime.format(dateTimeFormat);
    }
}
