package base_classes.classes;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

import base_classes.classes.emuns.RTE;

@Entity(name = "room_type")
public class RoomType {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "room_t_generator")
    @SequenceGenerator(name = "room_t_generator", sequenceName = "room_t_seq", allocationSize = 50)
    private int rt_id;
    private String room_type;

    public RoomType() {
    }

    public RoomType(String type) {
        this.room_type = type;
    }

    public int getRoom_id() {
        return rt_id;
    }

    public String getRoom_type() {
        return room_type;
    }

    public void setRoom_id(int room_id) {
        this.rt_id = room_id;
    }

    public void setRoom_type(String room_type) {
        this.room_type = room_type;
    }

    public static List<String> getFields() {
        List<String> ls = new ArrayList<>();
        ls.add("rt_id");
        ls.add("room_type");
        return ls;
    }

    public static String getTableName() {
        return "room_type";
    }

    public static String search(RTE type) {
        String sqlString = "from " + getTableName() + " where ";
        List<String> fields = getFields();

        switch (type) {
            case ID:
                sqlString = sqlString + fields.get(0) + " = ";
                break;
            case TYPE:
                sqlString = sqlString + fields.get(1) + " = ";
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
        return "Room type [ id = " + this.rt_id + " type: " + this.room_type + " ]";
    }
}
