package base_classes.classes;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import base_classes.classes.emuns.RCTE;

@Entity(name = "r_cancel_type")
public class ReservationCancelType {
    @Id
    private int rct_id;
    private String r_cancel_type;

    public ReservationCancelType() {
    }

    public ReservationCancelType(String type) {
        this.r_cancel_type = type;
    }

    public String getReservation_cancel_type() {
        return r_cancel_type;
    }

    public int getReservation_cancel_type_id() {
        return rct_id;
    }

    public void setReservation_cancel_type(String reservation_type) {
        this.r_cancel_type = reservation_type;
    }

    public void setReservation_cancel_type_id(int reservation_type_id) {
        this.rct_id = reservation_type_id;
    }

    public static List<String> getFields() {
        List<String> ls = new ArrayList<>();
        ls.add("rct_id");
        ls.add("r_cancel_type");
        return ls;
    }

    public static String getTableName() {
        return "r_cancel_type";
    }

    public String search(RCTE type) {
        String sqlString = "from " + getTableName() + " where ";
        List<String> fields = getFields();

        switch (type) {
            case ID:
                sqlString = sqlString + fields.get(0) + " = ";
                break;
            case CANCEL_TYPE:
                sqlString = sqlString + fields.get(1) + " = ";
                break;
            default:
                break;
        }

        return sqlString;
    }

    @Override
    public String toString() {
        return "Reservation cancel type [ id = " + this.rct_id + " type: " + this.r_cancel_type + " ]";
    }
}
