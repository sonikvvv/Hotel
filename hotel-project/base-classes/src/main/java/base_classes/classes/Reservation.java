package base_classes.classes;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import base_classes.classes.emuns.ReservE;
import base_classes.classes.emuns.RoomE;

@Entity
public class Reservation {
    @Id
    private int reservation_id;
    @OneToMany
    private Room room;
    @OneToMany
    private List<Clients> clients;
    @OneToOne
    private ReservationForm reservation_form;

    public Reservation() {
    }

    public Reservation(Room room, List<Clients> clients) {
        this.room = room;
        this.clients = clients;
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

    public void addClient(Clients client) {
        this.clients.add(client);
    }

    public static List<String> getFields() {
        List<String> ls = new ArrayList<>();
        ls.add("reservation_id");
        ls.add("room");
        ls.add("clients");
        ls.add("reservation_form");
        return ls;
    }

    public static String getTableName() {
        return "reservation";
    }

    public String search(ReservE type) {
        String sqlString = "from " + getTableName() + " where ";
        List<String> fields = getFields();

        switch (type) {
            case ID:
                sqlString = sqlString + fields.get(0) + " = ";
                break;
            case ROOM_ID:
                sqlString = sqlString + fields.get(1) + " = " + this.room.search(RoomE.ID);
                break;
            case ROOM_NUMBER:
                sqlString = sqlString + fields.get(1) + " = " + this.room.search(RoomE.NUMBER);
                break;
            case ROOM_TYPE:
                sqlString = sqlString + fields.get(1) + " = " + this.room.search(RoomE.ROOM_TYPE);
                break;
            case CLIENTS_ID:
                sqlString = sqlString + fields.get(2) + " = from " + Clients.getTableName()
                    + " where " + Clients.getFields().get(0) + " = ";
                break;
            case RESERVATION_FORM_ID:
                sqlString = sqlString + fields.get(2) + " = from " + ReservationForm.getTableName()
                    + " where " + ReservationForm.getFields().get(0) + " = ";
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
