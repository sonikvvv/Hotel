package base_classes.classes;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

import base_classes.classes.emuns.RoomE;
import base_classes.classes.emuns.SE;

@Entity(name = "room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "room_generator")
    @SequenceGenerator(name = "room_generator", sequenceName = "room_seq", allocationSize = 50)
    private int r_id;
    
    private String r_number;
    private String r_type;
    private double price; 

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(unique = false)
    private List<Clients> clients = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private SE r_status;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(unique = false)
    private List<Raiting> rait = new ArrayList<>();

    private int hotel_id;

    public Room() {
    }

    public Room(String r_number, String r_type, double price, SE r_status) {
        this.r_number = r_number;
        this.r_type = r_type;
        this.price = price;
        this.r_status = r_status;
    }

    public Room(String r_number, String r_type, double price, SE r_status, int hotel_id) {
        this.r_number = r_number;
        this.r_type = r_type;
        this.price = price;
        this.r_status = r_status;
        this.hotel_id = hotel_id;
    }

    public List<Clients> getClients() {
        return clients;
    }
    public double getPrice() {
        return price;
    }
    public int getR_id() {
        return r_id;
    }
    public String getR_number() {
        return r_number;
    }
    public SE getR_status() {
        return r_status;
    }
    public String getR_type() {
        return r_type;
    }
    public List<Raiting> getRait() {
        return rait;
    }
    public int getHotel_id() {
        return hotel_id;
    }


    public void setClients(List<Clients> clients) {
        this.clients = clients;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public void setR_id(int r_id) {
        this.r_id = r_id;
    }
    public void setR_number(String r_number) {
        this.r_number = r_number;
    }
    public void setR_status(SE r_status) {
        this.r_status = r_status;
    }
    public void setR_type(String r_type) {
        this.r_type = r_type;
    }
    public void setRait(List<Raiting> rait) {
        this.rait = rait;
    }
    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }


    public void addToClients(Clients client) {
        this.clients.add(client);
    }

    public void addToRait(Raiting rait) {
        this.rait.add(rait);
    }

    public static List<String> getFields() {
        List<String> ls = new ArrayList<>();
        ls.add("r_id");
        ls.add("r_number");
        ls.add("r_type");
        ls.add("r_status");
        ls.add("hotel_id");
        ls.add("rait");
        return ls;
    }

    public static String getTableName() {
        return "room";
    }

    public static String search(RoomE type) {
        String sqlString = "from " + getTableName() + " t where t.";
        List<String> fields = getFields();

        switch (type) {
            case ID:
                sqlString = sqlString + fields.get(0) + " = ";
                break;
            case NUMBER:
                sqlString = sqlString + fields.get(1) + " = '";
                break;
            case ROOM_TYPE:
                sqlString = sqlString + fields.get(2) + " = '";
                break;
            case ROOM_STATUS:
                sqlString = sqlString + fields.get(3) + " = '";
                break;
            case HOTEL_ID:
                sqlString = sqlString + fields.get(4) + " = ";
                break;
            default:
                break;
        }

        return sqlString;
    }

    @Override
    public String toString() {
        return "Room [ id = " + this.r_id + " number: " + this.r_number + " type: "
                + this.r_type + " status: " + this.r_status + " ]";
    }


}