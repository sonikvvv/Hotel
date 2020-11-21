package base_classes.classes;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import base_classes.classes.emuns.HE;

@Entity(name = "hotel")
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hotel_generator")
    @SequenceGenerator(name = "hotel_generator", sequenceName = "hotel_seq", allocationSize = 50)
    private int hotel_id;
    private String hotel_name;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(unique = false)
    private List<User> users = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(unique = false)
    private List<Room> rooms = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(unique = false)
    private List<Clients> clients = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(unique = false)
    private List<Reservation> reservations = new ArrayList<>();

    public Hotel() {}

    public Hotel(String name) {
        this.hotel_name = name;
    }

    public Hotel(String hotel_name, List<User> users) {
        this.setHotel_name(hotel_name);
        this.users = users;
    }

    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    public List<Clients> getClients() {
        return clients;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public List<User> getUsers() {
        return users;
    }

    public String getHotel_name() {
        return hotel_name;
    }

    public void setHotel_name(String hotel_name) {
        this.hotel_name = hotel_name;
    }

    public void setClients(List<Clients> clients) {
        this.clients = clients;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }


    public void addToUsers(User u) {
        u.addToID(this.hotel_id + "");
        this.users.add(u);
    }
    
    public void addToRooms(Room room) {
        room.setHotel_id(this.hotel_id);
        this.rooms.add(room);
    }

    public void addToClients(Clients clients) {
        clients.setHotel_id(this.hotel_id);
        this.clients.add(clients);
    }

    public void addToReservations(Reservation reservations) {
        reservations.setHotel_id(this.hotel_id);
        reservations.setHotel_name(this.hotel_name);
        this.reservations.add(reservations);
    }

    public static List<String> getFields() {
        List<String> ls = new ArrayList<>();
        ls.add("hotel_id");
        ls.add("hotel_name");
        ls.add("users");
        ls.add("rooms");
        ls.add("clients");
        ls.add("reservations");
        return ls;
    }

    public static String getTableName() {
        return "hotel";
    }

    public static String search(HE type) {
        String sqlString = "from " + getTableName() + " t where t.";
        List<String> fields = getFields();

        switch (type) {
            case ID:
                sqlString = sqlString + fields.get(0) + " = ";
                break;
            case NAME:
                sqlString = sqlString + fields.get(1) + " = '";
                break;
            case USER:
                sqlString = "select u from " + Hotel.getTableName() + " t join t." + Hotel.getFields().get(5)
                        + " u where u." + Reservation.getFields().get(3) + " = ";
                break;
            default:
                break;
        }

        return sqlString;
    }

    @Override
    public String toString() {
        return "Hotel [ id = " + this.hotel_id + " users = " + this.users + " reservations: " + this.reservations
                + " clients: " + this.clients + " rooms: " +this.rooms + " ]";
    }
}
