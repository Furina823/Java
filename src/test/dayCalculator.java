package test;

import java.time.LocalDateTime;
import java.time.Month;
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

            int numbermonth = switch(month){
                case "Jan" -> 1;
                case "Feb" -> 2;
                case "Mar" -> 3;
                case "Apr" -> 4;
                case "May" -> 5;
                case "Jun" -> 6;
                case "Jul" -> 7;
                case "Aug" -> 8;
                case "Sep" -> 9;
                case "Oct" -> 10;
                case "Nov" -> 11;
                case "Dec" -> 12;
                default -> throw new IllegalStateException("Unexpected value: " + month);
            };

            int year = Integer.parseInt(d[2]);

             day_of_year dy = new day_of_year(year);

             int current_day_month = dy.getMonthday().get(month);

            // M T W T F S S

            LocalDateTime date2 = LocalDateTime.of(year, numbermonth,1,15,30,45,0);

            DateTimeFormatter newformatdate = DateTimeFormatter.ofPattern("E");
            String newformatdate1 = date2.format(newformatdate);

            newformatdate = DateTimeFormatter.ofPattern("dd");
            String day2 = date2.format(newformatdate);

            int day3 = Integer.parseInt(day2);

            String result = switch(newformatdate1){
                case "Mon" -> "Mon";
                case "Tue" -> "Tue";
                case "Wed" -> "Wed";
                case "Thu" -> "Thu";
                case "Fri" -> "Fri";
                case "Sat" -> "Sat";
                case "Sun" -> "Sun";
                default -> throw new IllegalStateException("Unexpected value: " + newformatdate1);
            };

            System.out.println("Mon | Tue | Wed | Thu | Fri | Sat");
            for (int i = day3; i < current_day_month; i++){




            }


        }
}
