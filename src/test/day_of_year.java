package test;

import java.time.MonthDay;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class day_of_year {

    private HashMap<String, Integer> monthday = new HashMap<String, Integer>();

    public HashMap<String, Integer> getMonthday() {
        return monthday;
    }

    public day_of_year(int year){

        monthday.put("Jan", 31);
        monthday.put("Mar", 31);
        monthday.put("Apr", 30);
        monthday.put("May", 31);
        monthday.put("Jun", 30);
        monthday.put("Jul", 31);
        monthday.put("Aug", 31);
        monthday.put("Sep", 30);
        monthday.put("Oct", 31);
        monthday.put("Nov", 30);
        monthday.put("Dec", 31);

        leapYear ly = new leapYear(year);

        if (ly.getIsleap()) {
            monthday.put("Feb", 28);
        }else{
            monthday.put("Feb", 29);
        }

    }

}
