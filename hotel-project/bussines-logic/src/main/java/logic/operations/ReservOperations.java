package logic.operations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import base_classes.DBConnection;
import base_classes.classes.Reservation;

public class ReservOperations {

    public static List<Reservation> getReservations(DBConnection db, List<String> data) {
        List<Reservation> result = new ArrayList<>();
        result = db.getAllReservationsByHotel(1);

        return result;
    }

    public static List<Reservation> getCreatedReservations(DBConnection db, List<String> data) {
        List<Reservation> result = new ArrayList<>();
        result = db.getAllReservationsByHotel(1);

        return result;
    }

    public static List<Reservation> getReceptionistCreatedReservations(DBConnection db, List<String> data) {
        List<Reservation> result = new ArrayList<>();
        List<Reservation> reservations = db.getAllReservationsByHotel(1);

        LocalDate fromdate = DateOperations.toDate(data.get(0));
        LocalDate todate = DateOperations.toDate(data.get(1));

        for (Reservation reservation : reservations) {
            if (DateOperations.compareDates(reservation.getDate_made(), fromdate, todate)) {
                if (reservation.getReceptionist().getUser_name().equals(data.get(2)))
                    result.add(reservation);
            }
        }
        return result;
    }
}
