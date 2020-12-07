package logic.operations;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DateOperations {
    private static final Logger LOGGER = LogManager.getLogger(DateOperations.class);

    public static LocalDate toDate(String value) {
        LOGGER.debug("Starting toDate with data {}.", value);
        return LocalDate.parse(value);
    }

    public static LocalDateTime toDateAndTime(String value) {
        LOGGER.debug("Starting toDateAndTime with data {}.", value);
        String[] tmp = value.split("-");
        return LocalDateTime.of(Integer.valueOf(tmp[0]), Integer.valueOf(tmp[1]), Integer.valueOf(tmp[2]), 0, 0, 0);
    }

    public static LocalDateTime toDateAndTimeEndOfDay(String value) {
        LOGGER.debug("Starting toDateAndTimeEndOfDay with data {}.", value);
        String[] tmp = value.split("-");
        return LocalDateTime.of(Integer.valueOf(tmp[0]), Integer.valueOf(tmp[1]), Integer.valueOf(tmp[2]), 23, 59, 59);
    }

    public static boolean compareDateTime(LocalDateTime compare, LocalDateTime from, LocalDateTime to) {
        LOGGER.debug("Starting compareDateTime with data {}, {}, {}.", compare.toString(), from.toString(),
                to.toString());
        if (compare.isAfter(from) && compare.isBefore(to))
            return true;
        else if (compare.isEqual(from) || compare.isEqual(to))
            return true;
        else
            return false;
    }

    public static boolean compareDates(LocalDate compare, LocalDate from, LocalDate to) {
        LOGGER.debug("Starting compareDates with data {}, {}, {}.", compare.toString(), from.toString(), to.toString());
        if (compare.isAfter(from) && compare.isBefore(to))
            return true;
        else if (compare.isEqual(from) || compare.isEqual(to))
            return true;
        else
            return false;
    }

    public static boolean compareTwoDates(LocalDate compare, LocalDate to) {
        LOGGER.debug("Starting compareTwoDates with data {}, {}.", compare.toString(), to.toString());
        if (compare.isEqual(to))
            return true;
        else
            return false;
    }
}
