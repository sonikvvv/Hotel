package logic.operations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.Test;

public class DateOperationsTest {
    private LocalDate min = LocalDate.of(2020, 11, 10);
    private LocalDate max = LocalDate.parse("2020-12-10");
    private LocalDate compare = LocalDate.parse("2020-11-20");
    private LocalDateTime begin = LocalDateTime.of(2020, 11, 10, 0, 0, 0);
    private LocalDateTime end = LocalDateTime.of(2020, 11, 10, 23, 59, 59);
    private LocalDateTime comp = LocalDateTime.of(2020, 11, 10, 10, 59, 59);

    @Test
    public void toDateTest() {
        assertEquals(min, DateOperations.toDate("2020-11-10"));
    }

    @Test
    public void toDateAndTimeTest() {
        assertEquals(begin, DateOperations.toDateAndTime("2020-11-10"));
    }

    @Test
    public void toDateAndTimeEndOfDayTest() {
        assertEquals(end, DateOperations.toDateAndTimeEndOfDay("2020-11-10"));
    }

    @Test
    public void compareDateTimeTest() {
        assertTrue(DateOperations.compareDateTime(comp, begin, end));
        assertFalse(DateOperations.compareDateTime(LocalDateTime.of(2020, 10, 10, 0, 0, 0), begin, end));
        assertFalse(DateOperations.compareDateTime(LocalDateTime.of(2020, 12, 15, 0, 0, 0), begin, end));
    }

    @Test
    public void compareDates() {
        assertTrue(DateOperations.compareDates(compare, min, max));
        assertFalse(DateOperations.compareDates(LocalDate.of(2020, 10, 10), min, max));
        assertFalse(DateOperations.compareDates(LocalDate.of(2020, 12, 15), min, max));
    }

    @Test
    public void compareTwoDatesTest() {
        assertTrue(DateOperations.compareTwoDates(compare, compare));
        assertFalse(DateOperations.compareTwoDates(compare, min));
    }

}
