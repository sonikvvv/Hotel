package logic.operations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import base_classes.DBConnection;
import base_classes.classes.Raiting;
import base_classes.classes.Room;

public class RoomOperations {//TODO: fix the raiting

    private static List<Room> temporal = new ArrayList<>();

    public static List<Room> getTemporal() {
        return temporal;
    }

    public static void setTemporal(List<Room> temporal) {
        RoomOperations.temporal = temporal;
    }

    public static void addToTemporal(Room room) {
        temporal.clear();
        temporal.add(room);
    }

    public static List<Room> getRooms(DBConnection db, int hotel_id) {
        List<Room> result = db.getRoomsByHotel(hotel_id);
        return result;
    }

    public static List<Room> getRoomRait(DBConnection db, List<String> data) {
        int hotel_id = UserOperations.getUser_now().get(0).getHotel().get(0).getHotel_id();
        List<Room> result = new ArrayList<>();

        List<Room> rooms = db.getRoomsByHotel(hotel_id);

        LocalDateTime fromdate = DateOperations.toDateAndTime(data.get(0));
        LocalDateTime todate = DateOperations.toDateAndTime(data.get(1));

        for (Room room : rooms) {
            Room tmp = room;
            List<Raiting> raits = new ArrayList<>();

            for (Raiting raiting : tmp.getRait()) {
                if (DateOperations.compareDateTime(raiting.getDate_made(), fromdate, todate)){
                    raits.add(raiting);
                }
            }

            Raiting max = raits.get(0);
            for (Raiting raiting : raits) {
                if (max.getRait_value() < raiting.getRait_value()) {
                    max = raiting;
                }
            }

            tmp.getRait().set(0, max);
            result.add(tmp);
        }

        return result;
    }


}
