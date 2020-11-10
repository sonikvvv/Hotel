package base_classes.classes;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

import base_classes.classes.emuns.RTE;

@Entity(name = "r_type")
public class ReservationType {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reserv_t_generator")
    @SequenceGenerator(name = "reserv_t_generator", sequenceName = "reserv_t_seq", allocationSize = 50)
    private int rt_id;
    private String r_type;

    public ReservationType() {}

    public ReservationType(String type) {
        this.r_type = type;
    }

    public String getR_type() {
        return r_type;
    }
    public int getR_type_id() {
        return rt_id;
    }

    public void setR_type(String reservation_type) {
        this.r_type = reservation_type;
    }
    public void setR_type_id(int reservation_type_id) {
        this.rt_id = reservation_type_id;
    }

    public static List<String> getFields() {
        List<String> ls = new ArrayList<>();
        ls.add("rt_id");
        ls.add("r_type");
        return ls;
    }

    public static String getTableName() {
        return "r_type";
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
        return "Reservation type [ id = " + this.rt_id + " type: " + this.r_type + " ]"; 
    }
}
