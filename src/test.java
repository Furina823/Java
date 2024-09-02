import datamodel.WorkSchedule;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

public class test {

    public static void main(String[] args) {

        LocalDate currentDate = LocalDate.now();

        LocalDate previousMonthDate = currentDate.minusMonths(1);

        ArrayList<WorkSchedule> sameMonth = new ArrayList<>();

        ArrayList<WorkSchedule> sameMonth_Holiday = new ArrayList<>();
        ArrayList<WorkSchedule> sameMonth_WorkDay = new ArrayList<>();

        for(WorkSchedule ws : WorkSchedule.getRecords()){
            if (isSameMonthAndYear(ws.getDate(),previousMonthDate.toString())){
                if(ws.getIsHoliday().equals("1"))
                {
                    sameMonth_Holiday.add(ws);
                }else {
                    sameMonth_WorkDay.add(ws);
                }
            }
        }

        int workday = sameMonth_WorkDay.size();
        int holiday = sameMonth_Holiday.size();

        System.out.println("Holiday :"+holiday +"\n"+"Workday :"+workday);

    }

    public static boolean isSameMonthAndYear(String date1, String date2) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Parse the strings into LocalDate objects
        LocalDate localDate1 = LocalDate.parse(date1, formatter);
        LocalDate localDate2 = LocalDate.parse(date2, formatter);

        // Compare the year and month
        return localDate1.getYear() == localDate2.getYear() &&
                localDate1.getMonth() == localDate2.getMonth();
    }

}