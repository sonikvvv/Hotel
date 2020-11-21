package logic.operations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import base_classes.DBConnection;
import base_classes.classes.Reservation;
import base_classes.classes.ReservationForm;
import base_classes.classes.User;
import base_classes.classes.emuns.URE;

public class ReservOperations {

    public static List<Reservation> getReservations(DBConnection db, List<String> data) {
        List<Reservation> result = new ArrayList<>();
        ReservationForm fr = new ReservationForm("reservation_type", "room_type", "cancel_type",
                LocalDate.of(2020, 11, 15), LocalDate.of(2020, 12, 15), 1, 1, 0, "food_type", 1000, "status", "notes",
                "client_name");

        Reservation r = new Reservation(new User("mej", "fedlslf", URE.ADMIN), fr, null);
        Reservation r1 = new Reservation(new User("mej", "fedlslf", URE.ADMIN), fr, null);

        result.add(r);
        result.add(r1);

        return result;
    }

    public static List<Reservation> getCreatedReservations(DBConnection db, List<String> data) {
        List<Reservation> result = new ArrayList<>();
        ReservationForm fr = new ReservationForm("reservation_type", "room_type", "cancel_type",
                LocalDate.of(2020, 11, 15), LocalDate.of(2020, 12, 15), 1, 1, 0, "food_type", 1000, "status", "notes",
                "client_name");

        Reservation r = new Reservation(new User("mej", "fedlslf", URE.ADMIN), fr, null);
        Reservation r1 = new Reservation(new User("mej", "fedlslf", URE.ADMIN), fr, null);

        result.add(r);
        result.add(r1);

        return result;
    }

    public static List<Reservation> getReceptionistCreatedReservations(DBConnection db, List<String> data) {
        List<Reservation> result = new ArrayList<>();
        ReservationForm fr = new ReservationForm("reservation_type", "room_type", "cancel_type",
                LocalDate.of(2020, 11, 15), LocalDate.of(2020, 12, 15), 1, 1, 0, "food_type", 1000, "status", "notes",
                "client_name");

        Reservation r = new Reservation(new User("mej", "fedlslf", URE.ADMIN), fr, null);
        Reservation r1 = new Reservation(new User("mej", "fedlslf", URE.ADMIN), fr, null);

        result.add(r);
        result.add(r1);

        return result;
    }
}
