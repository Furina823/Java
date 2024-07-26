package test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class dayCalculator {

    public static void main(String[] args) {

        new dayCalculator();

    }

        public dayCalculator(){

            LocalDateTime date = LocalDateTime.now();

            DateTimeFormatter formatdate = DateTimeFormatter.ofPattern("E, dd/MMM/yyyy");
            String formattedDate = date.format(formatdate);

            String[] s = formattedDate.split(",");
            String weekday = s[0];
            String day_month_year = s[1].trim();

            String[] d = day_month_year.split("/");
            int day = Integer.parseInt(d[0]);
            String month = d[1];
            int year = Integer.parseInt(d[2]);

            System.out.println("date :" + formattedDate);
            System.out.println("weekday :" + weekday);
            System.out.println("day :" + day);
            System.out.println("month :" + month);
            System.out.println("year :" + year);

            day_of_year dy = new day_of_year(year);






        }

}
