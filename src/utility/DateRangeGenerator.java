package utility;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DateRangeGenerator {

    public static String[] generateDateRange(LocalDate startDate, LocalDate endDate) {
        List<String> dateList = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        while (!startDate.isAfter(endDate)) {
            dateList.add(startDate.format(formatter)); // Add date in yyyy-MM-dd format
            startDate = startDate.plusDays(1); // Move to the next day
        }

        return dateList.toArray(new String[0]); // Convert the list to an array
    }

}