package logic.operations;

import java.util.List;

import base_classes.DBConnection;
import base_classes.classes.Reservation;

public class ReservOperations {
    public static List<Reservation> getCreatedReservations(DBConnection db, List<String> data) {
        return db.getReservationsByDate(data);
    }
}
