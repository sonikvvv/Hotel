package logic.operations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import base_classes.DBConnection;
import base_classes.classes.Clients;
import base_classes.classes.Hotel;
import base_classes.classes.Raiting;
import base_classes.classes.Room;
import base_classes.classes.User;
import base_classes.classes.emuns.SE;
import base_classes.classes.emuns.URE;

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

    public static List<Room> getRooms(DBConnection db) {
        User user_now = UserOperations.getUser_now().get(0);
        
        List<Room> rooms = new ArrayList<>();

        if (user_now.getUser_role() == URE.ADMIN) {
            rooms.addAll(db.getAllRooms());
        } else {
            for (Hotel hotel : user_now.getHotel()) {
                rooms.addAll(db.getRoomsByHotel(hotel.getHotel_id()));
            }
        }

        return rooms;
    }

    public static List<Room> getRoomRait(DBConnection db, List<String> data) {
       User user_now = UserOperations.getUser_now().get(0);
        List<Room> result = new ArrayList<>();

        List<Room> rooms = new ArrayList<>();

        if (user_now.getUser_role() == URE.ADMIN) {
            rooms.addAll(db.getAllRooms());
        } else {
            for (Hotel hotel : user_now.getHotel()) {
                rooms.addAll(db.getRoomsByHotel(hotel.getHotel_id()));
            }
        }

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

    public static void checkOUT(DBConnection db, List<String> client_and_roomID) {
        int room_id = Integer.valueOf(client_and_roomID.get(0));
        Room room = db.getRoomByID(room_id);
        Clients for_checkout = null;

        for (Clients clients : room.getClients()) {
            if(clients.getC_name().equals(client_and_roomID.get(1))){
                for_checkout = clients;
                break;
            }
        }

        if(client_and_roomID.size() == 3){
            room.setR_status(SE.DIRTY);
        }

        room.getClients().remove(for_checkout);
        for_checkout.checkOut();
        db.saveOrUpdateObject(for_checkout);
        db.saveOrUpdateObject(room);
    }
}
