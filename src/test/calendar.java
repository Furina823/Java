package test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.WeekFields;
import java.util.Calendar;
import java.util.Locale;

public class calendar {

    public static void main(String[] args) {


        LocalDate today = LocalDate.now();

        // Get the first day of the week based on the default locale
        LocalDate firstDayOfWeek = today.with(WeekFields.of(Locale.getDefault()).getFirstDayOfWeek());

        // Create a DateTimeFormatter with the pattern "E, dd/MM/yyyy"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E, dd/MM/yyyy");

        // Loop through the days of the week and print the formatted date
        for (int i = 0; i < 110; i++) {
            LocalDate currentDay = firstDayOfWeek.plus(i, ChronoUnit.DAYS);
            String formattedDate = currentDay.format(formatter);
            System.out.println(formattedDate);
        }



    }

}
