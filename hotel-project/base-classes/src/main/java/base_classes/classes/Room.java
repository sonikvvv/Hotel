package base_classes.classes;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

import base_classes.classes.emuns.RoomE;

@Entity(name = "room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "room_generator")
    @SequenceGenerator(name = "room_generator", sequenceName = "room_seq", allocationSize = 50)
    private int r_id;
    
    private String r_number;
    private String r_type;

    @Enumerated(EnumType.STRING)
    private RoomE r_status;

    public Room() {
    }

    public Room(String number, String type, RoomE status) {
        this.r_number = number;
        this.r_type = type;
        this.r_status = status;
    }

    public int getRoom_id() {
        return r_id;
    }

    public String getRoom_number() {
        return r_number;
    }

    public String getRoom_type() {
        return r_type;
    }

    public RoomE getStatus() {
        return r_status;
    }

    
    public void setRoom_id(int r_id) {
        this.r_id = r_id;
    }

    public void setRoom_number(String r_number) {
        this.r_number = r_number;
    }

    public void setRoom_type(String r_type) {
        this.r_type = r_type;
    }

    public void setStatus(RoomE status) {
        this.r_status = status;
    }

    public static List<String> getFields() {
        List<String> ls = new ArrayList<>();
        ls.add("r_id");
        ls.add("r_number");
        ls.add("r_type");
        ls.add("r_status");
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
                sqlString = sqlString + fields.get(3) + " = '";
                break;
            case ROOM_STATUS:
                sqlString = sqlString + fields.get(4) + " = '";
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
                + this.r_type + " status: " + this.r_status + " ]";
    }
}