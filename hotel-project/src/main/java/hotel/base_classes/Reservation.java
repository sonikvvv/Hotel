package hotel.base_classes;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Reservation {
    @Id
    private int reservation_id;
    private Room room;
    private List<Clients> clients;


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
