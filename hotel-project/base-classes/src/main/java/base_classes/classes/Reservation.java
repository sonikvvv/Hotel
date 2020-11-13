package base_classes.classes;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

import base_classes.classes.emuns.ReservE;

@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reserv_generator")
    @SequenceGenerator(name = "reserv_generator", sequenceName = "reserv_seq", allocationSize = 50)
    private int reservation_id;

    @OneToOne(cascade = CascadeType.ALL)
    private Room room;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Clients> clients;

    @OneToOne(cascade = CascadeType.ALL)
    private ReservationForm reservation_form;

    @OneToOne
    private User receptionist;
    //TODO: add hotel field

    public Reservation() {
    }

    public Reservation(Room room, List<Clients> clients, User receptionist) {
        this.room = room;
        this.clients = clients;
        this.receptionist = receptionist;
    }

    public List<Clients> getClients() {
        return clients;
    }
    public int getReservation_id() {
        return reservation_id;
    }
    public Room getRoom() {
        return room;
    }
    public ReservationForm getReservation_form() {
        return reservation_form;
    }

    public User getReceptionist() {
        return receptionist;
    }


    public void setReservation_form(ReservationForm reservation_form) {
        this.reservation_form = reservation_form;
    }
    public void setClients(List<Clients> clients) {
        this.clients = clients;
    }
    public void setReservation_id(int reservation_id) {
        this.reservation_id = reservation_id;
    }
    public void setRoom(Room room) {
        this.room = room;
    }
    public void setReceptionist(User receptionist) {
        this.receptionist = receptionist;
    }

    public void addClient(Clients client) {
        this.clients.add(client);
    }

    public static List<String> getFields() {
        List<String> ls = new ArrayList<>();
        ls.add("reservation_id");
        ls.add("room");
        ls.add("clients");
        ls.add("reservation_form");
        ls.add("receptionist");
        return ls;
    }

    public static String getTableName() {
        return "reservation";
    }

    public static String search(ReservE type) {
        String sqlString = "from " + getTableName() + "t where t.";
        List<String> fields = getFields();

        switch (type) {
            case ID:
                sqlString = sqlString + fields.get(0) + " = ";
                break;
            case ROOM_ID:
                sqlString = sqlString + fields.get(1) + ".r_id = " ;
                break;
            case ROOM_NUMBER:
                sqlString = sqlString + fields.get(1) + ".r_number = '" ;
                break;
            case ROOM_TYPE:
                sqlString = sqlString + fields.get(1) + ".r_type = '" ;
                break;
            case CLIENTS_ID:
                sqlString = sqlString + fields.get(2) + ".client_id = ";
                break;
            case RESERVATION_FORM_ID:
                sqlString = sqlString + fields.get(3) + ".reservation_form_id = ";
                break;
            case RECEPTIONIST_ID:
                sqlString = sqlString + fields.get(4) + ".user_name = '";
                break;
            case RECEPTIONIST_NAME:
                sqlString = sqlString + fields.get(4) + ".user_id = ";
                break;
            case ALL:
                sqlString = "from " + getTableName();
                break;
            default:
                break;
        }

        return sqlString;
    }

    @Override
    public String toString() {
        return "Reservation [ id = " + this.reservation_id + " room: " + this.room.getRoom_number() + " clients: " + this.clients + " ]";
    }

}
