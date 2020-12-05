package logic.operations;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import base_classes.DBConnection;
import base_classes.classes.Clients;
import base_classes.classes.Hotel;
import base_classes.classes.Raiting;
import base_classes.classes.Reservation;
import base_classes.classes.Room;
import base_classes.classes.User;
import base_classes.classes.emuns.SE;
import base_classes.classes.emuns.URE;

public class RoomOperations {//TODO: fix the raiting

    private static List<Room> temporal = new ArrayList<>();
    private static final Logger LOGGER = LogManager.getLogger(ClientOperations.class);

    public static List<Room> getTemporal() {
        LOGGER.debug("Starting getTemporal.");
        return temporal;
    }

    public static void setTemporal(List<Room> temporal) {
        LOGGER.debug("Starting setTemporal with data {}.", temporal);
        RoomOperations.temporal = temporal;
    }

    public static void addToTemporal(Room room) {
        LOGGER.debug("Starting addToTemporal with data {}.", room);
        temporal.clear();
        temporal.add(room);
    }

    public static List<Room> getRooms(DBConnection db) {
        LOGGER.debug("Starting getRooms.");
        User user_now = UserOperations.getUser_now().get(0);
        List<Room> rooms = new ArrayList<>();

        if (user_now.getUser_role() == URE.ADMIN) {
            rooms.addAll(db.getAllRooms());
            LOGGER.debug("Getting rooms from all hotels.");
        } else {
            for (Hotel hotel : user_now.getHotel()) {
                rooms.addAll(db.getRoomsByHotel(hotel.getHotel_id()));
                LOGGER.debug("Getting rooms from hotel id: {}.", hotel.getHotel_id());
            }
        }
        LOGGER.debug("Result. {}", rooms);
        return rooms;
    }

    public static List<Room> getRoomRait(DBConnection db, List<String> data) {
        LOGGER.debug("Starting getRoomRait with data - {}", data);
        User user_now = UserOperations.getUser_now().get(0);
        List<Room> result = new ArrayList<>();
        List<Room> rooms = new ArrayList<>();

        if (user_now.getUser_role() == URE.ADMIN) {
            rooms.addAll(db.getAllRooms());
            LOGGER.debug("Getting room from all hotels.");
        } else {
            for (Hotel hotel : user_now.getHotel()) {
                rooms.addAll(db.getRoomsByHotel(hotel.getHotel_id()));
                LOGGER.debug("Getting room from hotel id: {}.", hotel.getHotel_id());
            }
        }

        LocalDateTime fromdate = DateOperations.toDateAndTime(data.get(0));
        LocalDateTime todate = DateOperations.toDateAndTimeEndOfDay(data.get(1));

        LOGGER.debug("Comparing room raits by dates {} - {} ", fromdate, todate);
        for (Room room : rooms) {
            Room tmp = room;
            List<Raiting> raits = new ArrayList<>();

            for (Raiting raiting : tmp.getRait()) {
                if (DateOperations.compareDateTime(raiting.getDate_made(), fromdate, todate)){
                    raits.add(raiting);
                }
            }

            try {
                Raiting max = raits.get(0);
                for (Raiting raiting : raits) {
                    if (max.getRait_value() < raiting.getRait_value()) {
                        max = raiting;
                    }
                }

                tmp.getRait().set(0, max);
                result.add(tmp);
            } catch (Exception e) {
                LOGGER.error("Error occured -> {}", e);
            }
        }

        LOGGER.debug("Result. {}", result);
        return result;
    }

    public static void checkOUT(DBConnection db, List<String> client_and_roomID) {
        LOGGER.debug("Starting checkOUT with data {}.", client_and_roomID);
        int room_id = Integer.valueOf(client_and_roomID.get(0));
        Room room = db.getRoomByID(room_id);
        Clients client_for_checkout = null;

        LOGGER.debug("Finding client with name - {}", client_and_roomID.get(1));
        for (Clients clients : room.getClients()) {
            if(clients.getC_name().equals(client_and_roomID.get(1))){
                client_for_checkout = clients;
                break;
            }
        }

        LOGGER.debug("Checking list size - {}", client_and_roomID.size());
        if(client_and_roomID.size() == 3){
            room.setR_status(SE.DIRTY);
        }

        LOGGER.debug("Client for check out - {}", client_for_checkout);
        room.getClients().remove(client_for_checkout);
        client_for_checkout.checkOut();
        db.saveOrUpdateObject(client_for_checkout);
        LOGGER.debug("Room for update - {}", room);
        db.saveOrUpdateObject(room);
    }

    public static List<String> getRoomTypes(DBConnection db) {
        LOGGER.debug("Starting getRoomTypes.");
        List<String> room_types = db.getDistinctRoomTypes();
        LOGGER.debug("Result. {}", room_types);
        return room_types;
    }

    public static List<RoomBusyness> getRoomBusyness(DBConnection db, List<String> data) {
        LOGGER.debug("Starting getRoomBusyness with data - {} ", data);
        User user_now = UserOperations.getUser_now().get(0);
        LocalDate fromDate = DateOperations.toDate(data.get(0));
        LocalDate toDate = DateOperations.toDate(data.get(1));

        List<RoomBusyness> result = new ArrayList<>();
        List<String> room_types = db.getDistinctRoomTypes();

        List<Reservation> reservations = new ArrayList<>();
        if (user_now.getUser_role() == URE.ADMIN){
            reservations.addAll(db.getAllReservations());
            LOGGER.debug("Getting rooms from all hotels.");
        } else {
            for (Hotel hotel : user_now.getHotel()) {
                reservations.addAll(db.getAllReservationsByHotel(hotel.getHotel_id()));
                LOGGER.debug("Getting rooms from hotel id: {}.", hotel.getHotel_id());
            }
        }

        LOGGER.debug("Calculating dates between {} - {}", fromDate, toDate);
        List<LocalDate> datesBetween = fromDate.datesUntil(toDate).collect(Collectors.toList());
        datesBetween.add(toDate);

        int[][] tmp = new int[datesBetween.size()][room_types.size()];
        
        LOGGER.debug("Calculating room bussynes.");
        for (Reservation reservation : reservations) {
            int dateIndex = 0;
            int todateIndex = 0;
            int r_typeIndex = 0;
            LocalDate startDate = reservation.getReservation_form().getStart_date();
            LocalDate endDate = reservation.getReservation_form().getEnd_date();

            if (startDate.compareTo(fromDate) > 0) {
                if (startDate.compareTo(toDate) < 0) {
                    dateIndex = datesBetween.indexOf(startDate);
                } else
                    dateIndex = datesBetween.indexOf(toDate);
            }
            else dateIndex = 0;

            if (endDate.compareTo(toDate) < 0) {
                if (endDate.compareTo(fromDate) > 0) {
                    todateIndex = datesBetween.indexOf(endDate);
                } else 
                    todateIndex = 0;
            } else todateIndex = datesBetween.indexOf(toDate);

            if (reservation.getRoom() != null)
                r_typeIndex = room_types.indexOf(reservation.getRoom().getR_type());

            for (int i = dateIndex; i <= todateIndex; i++) {
                tmp[i][r_typeIndex] += 1;
            }
            
        }

        for (int i = 0; i <= datesBetween.size() - 1; i++) {
            result.add(new RoomBusyness(datesBetween.get(i), tmp[i]));
        }

        LOGGER.debug("Result. {}", result);
        return result;
    }
}
