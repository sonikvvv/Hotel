package base_classes.classes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import base_classes.classes.emuns.ReservE;

@Entity(name = "reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reserv_generator")
    @SequenceGenerator(name = "reserv_generator", sequenceName = "reserv_seq", allocationSize = 50)
    private int reservation_id;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(unique = false)
    private List<Room> rooms = new ArrayList<>();

    @Embedded
    private ReservationForm reservation_form;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", unique = false)
    private User receptionist;

    private int hotel_id;
    private String hotel_name;

    private LocalDate date_made;

    public Reservation() {
    }

    public Reservation(User receptionist, ReservationForm rf, List<Room> rooms) {
        this.receptionist = receptionist;
        this.reservation_form = rf;
        this.rooms = rooms;
    }

    public Reservation(User receptionist, ReservationForm rf, List<Room> rooms, int hotel_id) {
        this.receptionist = receptionist;
        this.reservation_form = rf;
        this.rooms = rooms;
        this.hotel_id = hotel_id;
    }

    public int getHotel_id() {
        return hotel_id;
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
    public List<Room> getRooms() {
        return rooms;
    }
    public LocalDate getDate_made() {
        return date_made;
    }
    public String getHotel_name() {
        return hotel_name;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
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
    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }
    public void setDate_made(LocalDate date_made) {
        this.date_made = date_made;
    }
    public void setHotel_name(String hotel_name) {
        this.hotel_name = hotel_name;
    }

    public void addToRooms(Room r) {
        this.rooms.add(r);
    }


    public static List<String> getFields() {
        List<String> ls = new ArrayList<>();
        ls.add("reservation_id");
        ls.add("rooms");
        ls.add("reservation_form");
        ls.add("receptionist");
        ls.add("hotel_id");
        return ls;
    }

    public static String getTableName() {
        return "reservation";
    }

    public static String search(ReservE type) {
        String sqlString = "from " + getTableName() + "t where t.";
        List<String> fields = getFields();
        List<String> rfFields = ReservationForm.getFields();

        switch (type) {
            case ID:
                sqlString = sqlString + fields.get(0) + " = ";
                break;
            case ROOM_ID:
                sqlString = sqlString + fields.get(1) + "." + Room.getFields().get(0) + "= " ;
                break;
            case ROOM_NUMBER:
                sqlString = sqlString + fields.get(1) + "." + Room.getFields().get(1) + " = '" ;
                break;
            case ROOM_TYPE:
                sqlString = sqlString + fields.get(1) + "." + Room.getFields().get(2) + " = '" ;
                break;
            case RECEPTIONIST_ID:
                sqlString = sqlString + fields.get(4) + "." + User.getFields().get(1) + " = ";
                break;
            case RECEPTIONIST_NAME:
                sqlString = sqlString + fields.get(4) + "." + User.getFields().get(1) + " = ";
                break;
            case HOTEL_ID:
                sqlString = sqlString + fields.get(5) + " = ";
                break;
            case RESERV_TYPE:
                sqlString = sqlString + rfFields.get(0) + " = '";
                break;
            case CANCEL_TYPE:
                sqlString = sqlString + rfFields.get(1) + " = '";
                break;
            case START_DATE:
                sqlString = sqlString + rfFields.get(2) + " like to_date('";
                break;
            case END_DATE:
                sqlString = sqlString + rfFields.get(3) + " like to_date('";
                break;
            case FOOD_TYPE:
                sqlString = sqlString + "lower(" + rfFields.get(4) + ") = lower('";
                break;
            case STATUS:
                sqlString = sqlString + "lower(" + rfFields.get(5) + ") = lower('";
                break;
            case CLIENTS_NAME:
                sqlString = sqlString + "lower(" + rfFields.get(6) + ") = lower('";
            default:
                break;
        }

        return sqlString;
    }

    @Override
    public String toString() { //fix me TODO 
        return "Reservation [ id = " + this.reservation_id + " receptionist: " + this.receptionist + " form: " + this.reservation_form
                + " rooms: " + this.rooms + " date made: " + this.date_made + " ]";
    }

}
