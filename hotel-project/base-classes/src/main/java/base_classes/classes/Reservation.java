package base_classes.classes;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

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

    @Override
    public String toString() {
        return "Reservation [ id = " + this.reservation_id + " room: " + this.room.getRoom_number() + " clients: " + this.clients + " ]";
    }

}
