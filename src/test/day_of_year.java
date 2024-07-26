package test;

import java.util.ArrayList;

public class day_of_year {

    private String[][] dayYear = {
            {"Jan","31"},
            {"Feb","28"},
            {"Mar","31"},
            {"Apr","30"},
            {"May","31"},
            {"Jun","30"},
            {"Jul","31"},
            {"Aug","31"},
            {"Sep","30"},
            {"Oct","31"},
            {"Nov","30"},
            {"Dec","31"}
    };

    public day_of_year(int year){

        leapYear ly = new leapYear(year);

        if (ly.getIsleap()) {
            dayYear[1][1] = "29";
        }else{
            dayYear[1][1] = "28";
        }

    }

    public String[][] getDayYear() {
        return dayYear;
    }

    public void setDayYear(String[][] dayYear) {
        this.dayYear = dayYear;
    }

}
