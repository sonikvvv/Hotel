package base_classes.classes;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

import base_classes.classes.emuns.SE;

@Entity(name = "room_status")
public class RoomStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "room_s_generator")
    @SequenceGenerator(name = "room_s_generator", sequenceName = "room_s_seq", allocationSize = 50)
    private int rs_id;
    private String room_status;

    public RoomStatus() {}

    public RoomStatus(String room_status) {
        this.room_status = room_status;
    }

    public String getRoom_status() {
        return room_status;
    }
    public int getRoom_status_id() {
        return rs_id;
    }

    public void setRoom_status(String room_status) {
        this.room_status = room_status;
    }
    public void setRoom_status_id(int room_status_id) {
        this.rs_id = room_status_id;
    }

    public static List<String> getFields() {
        List<String> ls = new ArrayList<>();
        ls.add("rs_id");
        ls.add("room_status");
        return ls;
    }

    public static String getTableName() {
        return "room_status";
    }

    public String search(SE type) {
        String sqlString = "from " + getTableName() + " where ";
        List<String> fields = getFields();

        switch (type) {
            case ID:
                sqlString = sqlString + fields.get(0) + " = ";
                break;
            case STATUS:
                sqlString = sqlString + fields.get(1) + " = ";
                break;
            default:
                break;
        }

        return sqlString;
    }

    @Override
    public String toString() {
        return "Room status [ id = " + this.rs_id + " status: " + this.room_status + " ]";
    }
}
