package com.mech_serv_mng.util;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class DateUtils {
    public static LocalDate validateDate(String stringDate){
        LocalDate date;
        try{
            date=LocalDate.parse(stringDate);
        }
        catch(DateTimeParseException ex){
            return null;
        }
        return date;
    }
}
