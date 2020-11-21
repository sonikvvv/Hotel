package logic.operations;

import java.util.ArrayList;
import java.util.List;

import base_classes.DBConnection;
import base_classes.classes.Raiting;
import base_classes.classes.Room;
import base_classes.classes.emuns.SE;

public class RoomOperations {//TODO: fix the raiting

    public static List<Room> getRooms(DBConnection db, List<String> data) {
        List<Room> result = new ArrayList<>();
        Room r = new Room("r_number", "Double", 153, SE.DIRTY);
        result.add(r);

        return result;
    }

    public static List<Room> getRoomRait(DBConnection db, List<String> data) {
        List<Room> result = new ArrayList<>();
        Room r = new Room("r_number", "Double", 153, SE.DIRTY);
        r.addToRait(new Raiting(5.7));
        result.add(r);

        return result;
    }
}
