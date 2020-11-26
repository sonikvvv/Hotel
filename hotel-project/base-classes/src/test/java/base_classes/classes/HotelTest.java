package base_classes.classes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class HotelTest {

    @Test
    public void getTableNameTest() {
        assertNotNull(Hotel.getTableName());
        assertEquals("hotel", Hotel.getTableName());
    }

    @Test
    public void getFieldsTest() {
        assertNotNull(Hotel.getFields());
        assertNotEquals(0, Hotel.getFields().size());
    }
    
}
