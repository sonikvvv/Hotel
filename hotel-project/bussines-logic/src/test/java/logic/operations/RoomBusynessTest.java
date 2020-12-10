package logic.operations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;

import org.junit.Test;

public class RoomBusynessTest {
    private RoomBusyness rb = new RoomBusyness();
    private LocalDate date = LocalDate.of(2020, 10, 10);
    private int[] array = {5, 6, 7, 9};

    @Test
    public void setTest() {
        rb.setDate(date);
        rb.setRoom_busynes(array);
        
        assertNotNull(rb.getDate());
        assertNotNull(rb.getRoom_busynes());

        assertEquals(date, rb.getDate());
        assertEquals(array, rb.getRoom_busynes());
    }
}
