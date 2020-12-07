package logic.operations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import base_classes.classes.Room;
import base_classes.classes.emuns.SE;

public class RoomOperationsTest {
    private Room temporal = new Room("103", "Double", 1000, SE.FREE);

    @Test
    public void addTest() {
        RoomOperations.addToTemporal(temporal);

        assertNotNull(RoomOperations.getTemporal());
        assertTrue(RoomOperations.getTemporal().size() > 0);
    }

    @Test
    public void setAndGetTest() {
        List<Room> tmp = new ArrayList<>();
        tmp.add(temporal);
        RoomOperations.setTemporal(tmp);

        assertNotNull(RoomOperations.getTemporal());
        assertTrue(RoomOperations.getTemporal().size() > 0);
        assertEquals(tmp, RoomOperations.getTemporal());
        assertEquals(temporal, RoomOperations.getTemporal().get(0));
    }
    
}
