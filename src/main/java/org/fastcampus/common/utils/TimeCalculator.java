package org.fastcampus.common.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeCalculator {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private TimeCalculator(){

    }
    public static LocalDate getDateDaysAge(int dayAgo){
        LocalDate currDate = LocalDate.now();
        return currDate.minusDays(dayAgo);
    }

    public static String getFormatterDate(LocalDateTime dateTime){
        if(dateTime == null){
            return "";
        }
        return dateTime.format(FORMATTER);
    }
}
