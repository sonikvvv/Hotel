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
    
    
    @SuppressWarnings("rawtypes")
    public static List decodeLogicOperation(OperationType type, Object o, List<String> data) {
        List result = new ArrayList<>();
        switch (type){
            case LOGIN:
                result = UserOperations.authenticationOperation(db, data);
                break;
            case CLIENT_INFO:
                result = ClientOperations.getClientsInfo(db, data);
                break;
            case CLIENT_RAITING:
                result = ClientOperations.getClientRaiting(db, data);
                break;
            case CREATED_RESERVATIONS:
                result = ReservOperations.getCreatedReservations(db, data);
                break;
            case USED_SERVICES:

                break;
            case GET_RECEPT:
                //result = 
            case RECEPT:
                result = UserOperations.getReceptCreatedReservations(db, data);
                break;
            case ROOM_RAITING:
                break;
            case UPDATE:
                db.saveOrUpdateObject(o);
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
        List res = DecodeOperation.decodeLogicOperation(OperationType.CLIENT_INFO, null, t);
    }
}
