package base_classes.classes;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import base_classes.classes.emuns.ADServicesE;

@Entity(name = "add_serv")
public class AdditServices {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ads_generator")
    @SequenceGenerator(name = "ads_generator", sequenceName = "ads_seq", allocationSize = 50)
    private int a_serv_id;
    private String a_serv_title;
    private double a_serv_price;

    public AdditServices() {
    }

    public AdditServices(String title, double price) {
        this.a_serv_title = title;
        this.a_serv_price = price;
    }

    public int getAddit_services_id() {
        return a_serv_id;
    }

    public double getAddit_services_price() {
        return a_serv_price;
    }

    public String getAddit_services_title() {
        return a_serv_title;
    }

    public void setAddit_services_id(int addit_services_id) {
        this.a_serv_id = addit_services_id;
    }

    public void setAddit_services_price(double addit_services_price) {
        this.a_serv_price = addit_services_price;
    }

    public void setAddit_services_title(String addit_services_title) {
        this.a_serv_title = addit_services_title;
    }

    public static String getTableName() {
        return "add_serv";
    }

    public static List<String> getFields() {
        List<String> ls = new ArrayList<>();
        ls.add("a_serv_id");
        ls.add("a_serv_title");
        ls.add("a_serv_price");
        return ls;
    }

    public String search(ADServicesE type) {
        String sqlString = "from " + getTableName() + " where ";
        List<String> fields = getFields();

        switch (type) {
            case ID:
                sqlString = sqlString + fields.get(0) + " = ";
                break;
            case TITLE:
                sqlString = sqlString + fields.get(1) + " = ";
                break;
            case PRICE:
                sqlString = sqlString + fields.get(2) + " = ";
                break;
        
            default:
                break;
        }

        return sqlString;
    }

    @Override
    public String toString() {
        return "Additional services [ id = " + this.a_serv_id + " title: " + this.a_serv_title
                + " price: " + this.a_serv_price + " ]";
    }
}
