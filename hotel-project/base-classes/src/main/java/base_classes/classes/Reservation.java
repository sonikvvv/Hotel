package base_classes.classes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity(name = "reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reserv_generator")
    @SequenceGenerator(name = "reserv_generator", sequenceName = "reserv_seq", allocationSize = 1)
    private int reservation_id;

    @ManyToOne
    private Room room;

    @Embedded
    private ReservationForm reservation_form;

    @ManyToOne
    private User receptionist;

    @ManyToOne
    private Hotel hotel;

    private LocalDate date_made;

    public Reservation() {
    }

    public Reservation(User receptionist, ReservationForm rf, Room room) {
        this.receptionist = receptionist;
        this.reservation_form = rf;
        this.room = room;
        this.date_made = LocalDate.now();
    }

    public Reservation(User receptionist, ReservationForm rf, Room room, Hotel hotel) {
        this.receptionist = receptionist;
        this.reservation_form = rf;
        this.room = room;
        this.hotel = hotel;
        this.date_made = LocalDate.now();
    }

    public Hotel getHotel() {
        return hotel;
    }
    public User getReceptionist() {
        return receptionist;
    }
    public ReservationForm getReservation_form() {
        return reservation_form;
    }
    public int getReservation_id() {
        return reservation_id;
    }
    public Room getRoom() {
        return room;
    }
    public LocalDate getDate_made() {
        return date_made;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
    public void setReceptionist(User receptionist) {
        this.receptionist = receptionist;
    }
    public void setReservation_form(ReservationForm reservation_form) {
        this.reservation_form = reservation_form;
    }
    public void setReservation_id(int reservation_id) {
        this.reservation_id = reservation_id;
    }
    public void setRoom(Room room) {
        this.room = room;
    }
    public void setDate_made(LocalDate date_made) {
        this.date_made = date_made;
    }

    public static List<String> getFields() {
        List<String> ls = new ArrayList<>();
        ls.add("reservation_id");
        ls.add("room");
        ls.add("reservation_form");
        ls.add("receptionist");
        ls.add("hotel");
        return ls;
    }

    public static String getTableName() {
        return "reservation";
    }

    @Override
    public String toString() {
        return "Reservation [ id = " + this.reservation_id + " receptionist: " + this.receptionist + " form: " + this.reservation_form
                + " rooms: " + this.room + " date made: " + this.date_made + " ]";
    }

}
