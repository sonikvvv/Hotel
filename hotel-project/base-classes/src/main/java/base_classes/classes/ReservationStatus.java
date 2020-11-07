package base_classes.classes;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import base_classes.classes.emuns.SE;

@Entity(name = "r_status")
public class ReservationStatus {
    @Id
    private int rs_id;
    private String r_status;

    public ReservationStatus() {
    }

    public ReservationStatus(String status) {
        this.r_status = status;
    }

    public String getReservation_status() {
        return r_status;
    }

    public int getReservation_status_id() {
        return rs_id;
    }

    public void setReservation_status(String reservation_status) {
        this.r_status = reservation_status;
    }

    public void setReservation_status_id(int reservation_status_id) {
        this.rs_id = reservation_status_id;
    }

    public static List<String> getFields() {
        List<String> ls = new ArrayList<>();
        ls.add("rs_id");
        ls.add("r_status");
        return ls;
    }

    public static String getTableName() {
        return "r_status";
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
        return "Reservation status [ id = " + this.rs_id + " status: " + this.r_status + " ]";
    }
}
