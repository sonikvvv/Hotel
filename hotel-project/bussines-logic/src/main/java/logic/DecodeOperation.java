package logic;

import java.util.ArrayList;
import java.util.List;

import base_classes.DBConnection;
import base_classes.classes.Hotel;
import base_classes.classes.User;
import base_classes.classes.emuns.URE;
import logic.operations.*;

public class DecodeOperation { //TODO: some how save temporali objects
    private static DBConnection db = new DBConnection();

    public static DBConnection getDb() {
        return db;
    }

    public static void setDb(DBConnection dbc) {
        db = dbc;
    }
    
    
    public static List<?> decodeLogicOperation(OperationType type, Object o, List<String> data) {
        User user_now = new User("name", "password", URE.OWNER);
        Hotel h = new Hotel("Testivile");
        h.setHotel_id(1);
        user_now.addToHotel(h);
        UserOperations.setUser_now(user_now);
        List<?> result = new ArrayList<>();
        switch (type){
            case SAVE_OR_UPDATE:
                // db.saveOrUpdateObject(o);
                break;
            case GET_ADS:
                result = AdditionalServicesOperations.getAllAdditionalServices(db, 1);
                break;
            case GET_USERS:
                result = UserOperations.getUsers(db, data);
                break;
            case GET_RESERVATIONS:
                result = ReservOperations.getReservations(db, data);
                break;
            case GET_ROOMS:
                result = RoomOperations.getRooms(db, 1);
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
                result = UserOperations.getReceptionists(db, data);
                break;
            case GET_USER_NOW:
                result = UserOperations.getUser_now();
                break;
            case AAD_TO_USERS:
                break;
            case GET_HOTEL:
                result = HotelOperations.getHotels(db, data);
                break;
            case LOGIN:
                result = UserOperations.authenticationOperation(db, data);
                break;
            default:
                break;
        }

        return result;
    }
}
