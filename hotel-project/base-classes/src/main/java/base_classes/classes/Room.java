package base_classes.classes;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

import base_classes.classes.emuns.RTE;
import base_classes.classes.emuns.RoomE;
import base_classes.classes.emuns.SE;

@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "room_generator")
    @SequenceGenerator(name = "room_generator", sequenceName = "room_seq", allocationSize = 50)
    private int r_id;
    private String r_number;
    private double r_rating;

    @OneToOne(cascade = CascadeType.ALL)
    private RoomType r_type;
    @OneToOne(cascade = CascadeType.ALL)
    private RoomStatus r_status;

    public Room() {
    }

    public Room(String number, double rating, RoomType type, RoomStatus status) {
        this.r_number = number;
        this.r_rating = rating;
        this.r_type = type;
        this.r_status = status;
    }

    public int getRoom_id() {
        return r_id;
    }

    public String getRoom_number() {
        return r_number;
    }

    public double getRoom_rating() {
        return r_rating;
    }

    public RoomType getRoom_type() {
        return r_type;
    }

    public RoomStatus getStatus() {
        return r_status;
    }

    
    public void setRoom_id(int r_id) {
        this.r_id = r_id;
    }

    public void setRoom_number(String r_number) {
        this.r_number = r_number;
    }

    public void setRoom_rating(double r_rating) {
        this.r_rating = r_rating;
    }

    public void setRoom_type(RoomType r_type) {
        this.r_type = r_type;
    }

    public void setStatus(RoomStatus status) {
        this.r_status = status;
    }

    public static List<String> getFields() {
        List<String> ls = new ArrayList<>();
        ls.add("r_id");
        ls.add("r_number");
        ls.add("r_rating");
        ls.add("r_type");
        ls.add("r_status");
        return ls;
    }

    public static String getTableName() {
        return "room";
    }

    public static String search(RoomE type) {
        String sqlString = "from " + getTableName() + " where ";
        List<String> fields = getFields();

        switch (type) {
            case ID:
                sqlString = sqlString + fields.get(0) + " = ";
                break;
            case NUMBER:
                sqlString = sqlString + fields.get(1) + " = ";
                break;
            case RAITING:
                sqlString = sqlString + fields.get(2) + " = ";
                break;
            case ROOM_TYPE:
                sqlString = sqlString + fields.get(3) + " = " + RoomType.search(RTE.TYPE);
                break;
            case ROOM_STATUS:
                sqlString = sqlString + fields.get(4) + " = " + RoomStatus.search(SE.STATUS);
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
        return "Room [ id = " + this.r_id + " number: " + this.r_number + " type: "
                + this.r_type.getRoom_type() + " status: " + this.r_status.getRoom_status() + " rating: "
                + this.r_rating + " ]";
    }
}