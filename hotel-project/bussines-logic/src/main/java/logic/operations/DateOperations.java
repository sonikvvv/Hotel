package logic.operations;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DateOperations {
    
    public static LocalDate toDate(String value) {
        String[] tmp = value.split("-");
        return LocalDate.of(Integer.valueOf(tmp[0]), Integer.valueOf(tmp[1]), Integer.valueOf(tmp[2]));
    }

    public static LocalDateTime toDateAndTime(String value) {
        String[] tmp = value.split("-");
        return LocalDateTime.of(Integer.valueOf(tmp[0]), Integer.valueOf(tmp[1]), Integer.valueOf(tmp[2]) + 1, 0, 0, 0);
    }

    public static boolean compareDateTime(LocalDateTime compare, LocalDateTime from, LocalDateTime to) {
        if (compare.getYear() >= from.getYear() && compare.getYear() <= to.getYear())
            if (compare.getMonthValue() >= from.getMonthValue() && compare.getMonthValue() <= to.getMonthValue())
                if (compare.getDayOfMonth() >= from.getDayOfMonth() && compare.getDayOfMonth() <= to.getDayOfMonth())
                    return true;
                else
                    return false;
            else
                return false;
        else
            return false;
    }

    public static boolean compareDates(LocalDate compare, LocalDate from, LocalDate to) {
        if (compare.getYear() >= from.getYear() && compare.getYear() <= to.getYear())
            if (compare.getMonthValue() >= from.getMonthValue() && compare.getMonthValue() <= to.getMonthValue())
                if (compare.getDayOfMonth() >= from.getDayOfMonth() && compare.getDayOfMonth() <= to.getDayOfMonth())
                    return true;
                else
                    return false;
            else
                return false;
        else
            return false;
    }
}
