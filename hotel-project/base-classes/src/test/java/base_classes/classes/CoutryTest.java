package base_classes.classes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class CoutryTest {

    @Test
    public void getTableNameTest() {
        assertNotNull(Country.getTableName());
        assertEquals("country", Country.getTableName());
    }

    @Test
    public void getFieldsTest() {
        assertNotNull(Country.getFields());
        assertNotEquals(0, Country.getFields().size());
    }
}
