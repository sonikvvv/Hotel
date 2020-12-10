package logic;

import java.util.ArrayList;
import java.util.List;

import base_classes.DBConnection;
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
        List<?> result = new ArrayList<>();
        switch (type){
            case SAVE:
                db.saveObject(o);
                break;
            case UPDATE:
                db.updateObject(o);
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
            case CHECKOUT_FOR_TODAY:
                result = ReservOperations.getCheckoutForToday(db);
                break;
            default:
                break;
        }

        return result;
    }
}
