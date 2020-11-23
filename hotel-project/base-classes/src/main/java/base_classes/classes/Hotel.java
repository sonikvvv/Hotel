package base_classes.classes;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import base_classes.classes.emuns.HE;

@Entity(name = "hotel")
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hotel_generator")
    @SequenceGenerator(name = "hotel_generator", sequenceName = "hotel_seq", allocationSize = 1)
    private int hotel_id;
    private String hotel_name;

    public Hotel() {}

    public Hotel(String name) {
        this.hotel_name = name;
    }

    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    public String getHotel_name() {
        return hotel_name;
    }

    public void setHotel_name(String hotel_name) {
        this.hotel_name = hotel_name;
    }

    public static List<String> getFields() {
        List<String> ls = new ArrayList<>();
        ls.add("hotel_id");
        ls.add("hotel_name");
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
            default:
                break;
        }

        return sqlString;
    }

    @Override
    public String toString() {
        return "Hotel [ id = " + this.hotel_id + " name:  " + this.hotel_name + " ]";
    }
}
