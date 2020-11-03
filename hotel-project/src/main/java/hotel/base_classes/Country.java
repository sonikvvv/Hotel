package hotel.base_classes;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Country {
    @Id
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

    @Override
    public String toString() {
        return "Country [ id = " + this.country_id + " county name: " + this.country_name + " ]";
    }
}
