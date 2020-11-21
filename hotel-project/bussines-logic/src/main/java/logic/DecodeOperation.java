package logic;

import java.util.ArrayList;
import java.util.List;

//import base_classes.DBConnection;
import logic.operations.*;

public class DecodeOperation { //TODO: some how save temporali objects
    // private static DBConnection db = new DBConnection();

    // public static DBConnection getDb() {
    //     return db;
    // }

    // public static void setDb(DBConnection dbc) {
    //     db = dbc;
    // }
    
    
    public static List<?> decodeLogicOperation(OperationType type, Object o, List<String> data) {
        List<?> result = new ArrayList<>();
        switch (type){
            case SAVEІOR_UPDATE:
                // db.saveOrUpdateObject(o);
                break;
            case GET_ADS:
                result = AdditionalServicesOperations.getAllAdditionalServices(null);
                break;
            case GET_USERS:
                result = UserOperations.getUsers(null, data);
                break;
            case GET_RESERVATIONS:
                result = ReservOperations.getReservations(null, data);
                break;
            case GET_ROOMS:
                result = RoomOperations.getRooms(null, data);
                break;
            case CLIENT_INFO:
                result = ClientOperations.getClientsInfo(null, data);
                break;
            case CLIENT_RAITING:
                result = ClientOperations.getClientRaiting(null, data);
                break;
            case USED_SERVICES:
                result = ClientOperations.getUsedServices(null, data);
                break;
            case CREATED_RESERVATIONS:
                result = ReservOperations.getCreatedReservations(null, data);
                break;
            case ROOM_RAITING:
                result = RoomOperations.getRoomRait(null, data);
                break;
            case RECEPT_CREATED_RESERVATIONS:
                result = ReservOperations.getReceptionistCreatedReservations(null, data);
                break;
            case GET_RECEPTIONIST:
                result = UserOperations.getReceptionists(null, data);
                break;
            case GET_USER_NOW:
                result = UserOperations.getUser_now();
                break;
            case AAD_TO_USERS:
                break;
            case GET_HOTEL:
                result = HotelOperations.getHotels(null, data);
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
        //List res = DecodeOperation.decodeLogicOperation(OperationType.CLIENT_INFO, null, t);
    }
}
