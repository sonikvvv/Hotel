package base_classes.classes;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import base_classes.classes.emuns.CountryE;

@Entity
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "country_generator")
    @SequenceGenerator(name = "country_generator", sequenceName = "country_seq", allocationSize = 50)
    private int country_id;
    private String country_name;

    public Country() {}

    public Country(String country_name) {
        this.country_name = country_name;
    }

    public int getCountry_id() {
        return country_id;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_id(int country_id) {
        this.country_id = country_id;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    public static List<String> getFields() {
        List<String> ls = new ArrayList<>();
        ls.add("country_id");
        ls.add("country_name");
        return ls;
    }

    public static String getTableName() {
        return "country";
    }

    public static String search(CountryE type) {
        String sqlString = "from " + getTableName() + "t where t.";
        List<String> fields = getFields();

        switch (type) {
            case ID:
                sqlString = sqlString + fields.get(0) + " = ";
                break;
            case COUNTRY_NAME:
                sqlString = sqlString + "lower(" + fields.get(1) + ") = lower('";
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
        return "Country [ id = " + this.country_id + " county name: " + this.country_name + " ]";
    }
}
