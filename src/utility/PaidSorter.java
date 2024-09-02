package utility;

import datamodel.Paid;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PaidSorter {
    public static void sortPaidByDateDescending(List<Paid> paidList) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-dd-MM");

        Collections.sort(paidList, new Comparator<Paid>() {
            @Override
            public int compare(Paid p1, Paid p2) {
                LocalDate date1 = LocalDate.parse(p1.getDate(), formatter);
                LocalDate date2 = LocalDate.parse(p2.getDate(), formatter);
                return date2.compareTo(date1); // For descending order
            }
        });
    }
}

