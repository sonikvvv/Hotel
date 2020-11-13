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
            case RECEPT:
                result = UserOperations.getReceptCreatedReservations(db, data);
                break;
            case ROOM_RAITING:
                break;
            case UPDATE:
                db.updateObject(o);
                break;
            default:
                break;
        }

        return result;
    }
}
