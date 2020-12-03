package logic.operations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import base_classes.DBConnection;
import base_classes.classes.Hotel;
import base_classes.classes.Reservation;
import base_classes.classes.User;
import base_classes.classes.emuns.URE;

public class ReservOperations {
    private static final Logger LOGGER = LogManager.getLogger(ClientOperations.class);

    public static List<Reservation> getReservations(DBConnection db, String data) {
        LOGGER.debug("Starting getReservations with data {}.", data);
        User user_now  = UserOperations.getUser_now().get(0);
        
        List<Reservation> result = new ArrayList<>();
        List<Reservation> reservations = new ArrayList<>();
        if (user_now.getUser_role() == URE.ADMIN) {
            reservations.addAll(db.getAllReservations());
            LOGGER.debug("Getting reservations from all hotels.");
        } else { 
            for (Hotel hotel : user_now.getHotel()) {
                reservations.addAll(db.getAllReservationsByHotel(hotel.getHotel_id()));
                LOGGER.debug("Getting reservations from hotel id: {}.", hotel.getHotel_id());
            }
        }

        LOGGER.debug("Comparing reservations to date - {}.", data);
        for (Reservation reservation : reservations) {
            if (DateOperations.compareTwoDates(reservation.getDate_made(), DateOperations.toDate(data))){
                result.add(reservation);
            }
        }
        
        LOGGER.debug("Result. {}", result);
        return result;
    }

    public static List<Reservation> getCreatedReservations(DBConnection db, List<String> data) {
        LOGGER.debug("Starting getCreatedReservations with data {}.", data);
        User user_now = UserOperations.getUser_now().get(0);
        List<Reservation> result = new ArrayList<>();
        List<Reservation> reservations = new ArrayList<>();

        if (user_now.getUser_role() == URE.ADMIN) {
            reservations.addAll(db.getAllReservations());
            LOGGER.debug("Getting reservations from all hotels.");
        } else {
            for (Hotel hotel : user_now.getHotel()) {
                reservations.addAll(db.getAllReservationsByHotel(hotel.getHotel_id()));
                LOGGER.debug("Getting reservations from hotel id: {}.", hotel.getHotel_id());
            }
        }

        LocalDate fromdate = DateOperations.toDate(data.get(0));
        LocalDate todate = DateOperations.toDate(data.get(1));

        LOGGER.debug("Comparing reservations to dates {} - {}.", fromdate.toString(), todate.toString());
        for (Reservation reservation : reservations) {
            if (DateOperations.compareDates(reservation.getDate_made(), fromdate, todate)) {
                result.add(reservation);
            }
        }

        LOGGER.debug("Result. {}", result);
        return result;
    }

    public static List<Reservation> getReceptionistCreatedReservations(DBConnection db, List<String> data) {
        LOGGER.debug("Starting getReceptionistCreatedReservations with data {}.", data);
        User user_now = UserOperations.getUser_now().get(0);
        List<Reservation> result = new ArrayList<>();
        List<Reservation> reservations = new ArrayList<>();

        if (user_now.getUser_role() == URE.ADMIN) {
            reservations.addAll(db.getAllReservations());
            LOGGER.debug("Getting reservations from all hotels.");
        } else {
            for (Hotel hotel : user_now.getHotel()) {
                reservations.addAll(db.getAllReservationsByHotel(hotel.getHotel_id()));
                LOGGER.debug("Getting reservations from hotel id: {}.", hotel.getHotel_id());
            }
        }

        LocalDate fromdate = DateOperations.toDate(data.get(0));
        LocalDate todate = DateOperations.toDate(data.get(1));

        LOGGER.debug("Comparing reservations to dates {} - {}.", fromdate.toString(), todate.toString());
        for (Reservation reservation : reservations) {
            if (DateOperations.compareDates(reservation.getDate_made(), fromdate, todate)) {
                if (reservation.getReceptionist().getUser_name().equals(data.get(2)))
                    result.add(reservation);
            }
        }
        LOGGER.debug("Result. {}", result);
        return result;
    }
}
