package logic.operations;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DateOperations {
    private static final Logger LOGGER = LogManager.getLogger(DateOperations.class);
    
    public static LocalDate toDate(String value) {
        LOGGER.debug("Starting toDate with data {}.", value);
        String[] tmp = value.split("-");
        return LocalDate.of(Integer.valueOf(tmp[0]), Integer.valueOf(tmp[1]), Integer.valueOf(tmp[2]));
    }

    public static LocalDateTime toDateAndTime(String value) {
        String[] tmp = value.split("-");
        return LocalDateTime.of(Integer.valueOf(tmp[0]), Integer.valueOf(tmp[1]), Integer.valueOf(tmp[2]) + 1, 0, 0, 0);
    }

    public static boolean compareDateTime(LocalDateTime compare, LocalDateTime from, LocalDateTime to) {
        LOGGER.debug("Starting compareDateTime with data {}, {}, {}.", compare.toString(), from.toString(), to.toString());
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
        LOGGER.debug("Starting compareDates with data {}, {}, {}.", compare.toString(), from.toString(), to.toString());
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

    public static boolean compareTwoDates(LocalDate compare, LocalDate to) {
        LOGGER.debug("Starting compareTwoDates with data {}, {}.", compare.toString(), to.toString());
        if (compare.getYear() == to.getYear())
            if (compare.getMonthValue() == to.getMonthValue())
                if (compare.getDayOfMonth() == to.getDayOfMonth())
                    return true;
                else
                    return false;
            else
                return false;
        else
            return false;
    }
}
