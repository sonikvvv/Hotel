package base_classes.classes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class RaitingTest {
    
    @Test
    public void getTableNameTest() {
        assertNotNull(Raiting.getTableName());
        assertEquals("raiting", Raiting.getTableName());
    }

    @Test
    public void getFieldsTest() {
        assertNotNull(Raiting.getFields());
        assertNotEquals(0, Raiting.getFields().size());
    }

}
