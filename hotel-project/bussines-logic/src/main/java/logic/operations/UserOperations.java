package logic.operations;

import java.util.List;

import base_classes.DBConnection;

@SuppressWarnings("rawtypes")
public class UserOperations {

    public static List authenticationOperation(DBConnection db, List<String> data) {
        return null; //TODO: fix the log in
    }

    public static List getReceptCreatedReservations(DBConnection db, List<String> data) {
        return db.getRceptionistReservations(data.get(0));
    }
}
