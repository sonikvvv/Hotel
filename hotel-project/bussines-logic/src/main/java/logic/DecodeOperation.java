package logic;

import java.util.ArrayList;
import java.util.List;

import base_classes.DBConnection;
import base_classes.classes.Hotel;
import base_classes.classes.User;
import base_classes.classes.emuns.URE;
import logic.operations.*;

public class DecodeOperation {
    private static DBConnection db = new DBConnection();

    public static DBConnection getDb() {
        return db;
    }

    public static void setDb(DBConnection dbc) {
        db = dbc;
    }
    
    
    public static List<?> decodeLogicOperation(OperationType type, Object o, List<String> data) {
        User user_now = new User("name", "password", URE.ADMIN);
        Hotel h = new Hotel("Testivile");
        // h.setHotel_id(1);
        // user_now.addToHotel(h);
        // UserOperations.setUser_now(user_now);
        List<?> result = new ArrayList<>();
        switch (type){
            case SAVE_OR_UPDATE:
                db.saveOrUpdateObject(o);
                break;
            case GET_ADS:
                result = AdditionalServicesOperations.getAllAdditionalServices(db);
                break;
            case GET_USERS:
                result = UserOperations.getUsers(db);
                break;
            case GET_RESERVATIONS:
                result = ReservOperations.getReservations(db, data.get(0));
                break;
            case GET_ROOMS:
                result = RoomOperations.getRooms(db);
                break;
            case CLIENT_INFO:
                result = ClientOperations.getClientsInfo(db, data);
                break;
            case CLIENT_RAITING:
                result = ClientOperations.getClientRaiting(db, data);
                break;
            case USED_SERVICES:
                result = ClientOperations.getUsedServices(db, data);
                break;
            case CREATED_RESERVATIONS:
                result = ReservOperations.getCreatedReservations(db, data);
                break;
            case ROOM_RAITING:
                result = RoomOperations.getRoomRait(db, data);
                break;
            case RECEPT_CREATED_RESERVATIONS:
                result = ReservOperations.getReceptionistCreatedReservations(db, data);
                break;
            case GET_RECEPTIONIST:
                result = UserOperations.getReceptionists(db);
                break;
            case GET_USER_NOW:
                result = UserOperations.getUser_now();
                break;
            case ADD_TO_USERS:
                UserOperations.addUser(db, data);
                break;
            case GET_HOTEL:
                result = HotelOperations.getHotels(db);
                break;
            case LOGIN:
                result = UserOperations.authenticationOperation(db, data);
                break;
            case DELETE:
                db.deleteObject(o);
                break;
            case CHECKOUT:
                RoomOperations.checkOUT(db, data);
                break;
            case RECEPTIONIST_REFERENCE:
                result = RoomOperations.getRoomBusyness(db, data);
                break;
            case ROOM_TYPES:
                result = RoomOperations.getRoomTypes(db);
                break;
            default:
                break;
        }

        return result;
    }

    public static void main(String[] args) {
        List<String> t = new ArrayList<>();
        t.add("2020-11-16");
        t.add("2020-11-17");
        t.add("1");
        //Clients purvi = new Clients("Kaloyan", LocalDate.of(1999, 9, 24), true, "1369", LocalDate.of(2023, 1, 5) , "AV1552A", new Country("Bulgaria"), "No note", "42194Xd");
        //List res = DecodeOperation.decodeLogicOperation(OperationType.SAVE_OR_UPDATE, purvi, null);
    }
}
