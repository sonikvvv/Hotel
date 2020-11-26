package base_classes.classes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;

import org.junit.Test;

import base_classes.classes.emuns.SE;
import base_classes.classes.emuns.URE;

public class ReservationTest {
    private Room room = null;
    private User user = null;
    private Hotel hotel = null;
    private ReservationForm rf = null;
    private Reservation reservation = null;

    public ReservationTest() {
        room = new Room("r_number", "r_type", 1000, SE.FREE);
        user = new User("name", "password", URE.MANAGER);
        hotel = new Hotel("Testivile");
        rf = new ReservationForm("reservation_type", "room_type", "cancel_type", LocalDate.now(), LocalDate.now(), 2, 0, 0, "food_type", 1500, "status", "notes", "client_name");
        reservation = new Reservation(user, rf, room, hotel);
    }

    @Test
    public void dateMadeTest() {
        assertNotNull(reservation.getDate_made());
        assertNotEquals("", reservation.getDate_made().toString());
    }

    @Test
    public void getRoomTest() {
        assertNotNull(reservation.getRoom());
        assertEquals(room, reservation.getRoom());
    }

    @Test
    public void getHotelTest() {
        assertNotNull(reservation.getHotel());
        assertEquals(hotel, reservation.getHotel());
    }

    @Test
    public void getRFTest() {
        assertNotNull(reservation.getReservation_form());
        assertEquals(rf, reservation.getReservation_form());
    }

    public void getUserTest() {
        assertNotNull(reservation.getReceptionist());
        assertEquals(user, reservation.getReceptionist());
    }
    
    @Test
    public void getTableNameTest() {
        assertNotNull(Reservation.getTableName());
        assertEquals("reservation", Reservation.getTableName());
    }

    @Test
    public void getFieldsTest() {
        assertNotNull(Reservation.getFields());
        assertNotEquals(0, Reservation.getFields().size());
    }
}
