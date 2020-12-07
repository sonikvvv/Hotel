package base_classes.classes;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import base_classes.classes.emuns.SE;

@Entity(name = "room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "room_generator")
    @SequenceGenerator(name = "room_generator", sequenceName = "room_seq", allocationSize = 1)
    private int r_id;
    
    private String r_number;
    private String r_type;

    @Column(columnDefinition = "Number(10,2)")
    private double price; 

    @ManyToMany
    private List<Clients> clients = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private SE r_status;

    @ManyToMany
    private List<Raiting> rait = new ArrayList<>();

    @ManyToOne
    private Hotel hotel;

    public Room() {
    }

    public Room(String r_number, String r_type, double price, SE r_status) {
        this.r_number = r_number;
        this.r_type = r_type;
        this.price = price;
        this.r_status = r_status;
        this.rait.add(new Raiting());
    }

    public Room(String r_number, String r_type, double price, SE r_status, Hotel hotel) {
        this.r_number = r_number;
        this.r_type = r_type;
        this.price = price;
        this.r_status = r_status;
        this.hotel = hotel;
        this.rait.add(new Raiting());
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
    public Hotel getHotel() {
        return hotel;
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
    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
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
        ls.add("hotel");
        ls.add("rait");
        return ls;
    }

    public static String getTableName() {
        return "room";
    }

    @Override
    public String toString() {
        return "Room [ id = " + this.r_id + " number: " + this.r_number + " type: "
                + this.r_type + " status: " + this.r_status + " ]";
    }


}