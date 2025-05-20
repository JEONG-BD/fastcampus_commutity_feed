package org.fastcampus.common;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TimeCalculator {

    private TimeCalculator(){

    }
    public static LocalDate getDateDaysAge(int dayAgo){
        LocalDate currDate = LocalDate.now();
        return currDate.minusDays(dayAgo);
    }
}
