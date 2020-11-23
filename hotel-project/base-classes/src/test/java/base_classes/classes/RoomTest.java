package base_classes.classes;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import base_classes.classes.emuns.SE;

public class RoomTest {
    private Room r = null;
    private String number;
    private int id;
    private List<String> fieldsTest = new ArrayList<>();
    private String type;
    private SE status;
    private String table_name;
    private double price;

    public RoomTest(){
        number = "test";
        id = 1;
        price = 1000;
        type = "1234";
        table_name = "room";
        status = SE.FREE;
        fieldsTest.add("r_id");
        fieldsTest.add("r_number");
        fieldsTest.add("r_type");
        fieldsTest.add("r_status");
        fieldsTest.add("hotel");
        fieldsTest.add("rait");
        r = new Room(number, type, price, status);
        r.setR_id(id);
    }

    @Test
    public void tableNameTest() {
        assertEquals(table_name, Room.getTableName());
    }

    @Test
    public void fieldsTest() {
        assertEquals(fieldsTest, Room.getFields());
    }
}
