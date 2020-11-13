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
    private String a_serv_type;
    private double a_serv_price;

    public AdditServices() {
    }

    public AdditServices(String title, String type, double price) {
        this.a_serv_title = title;
        this.a_serv_price = price;
        this.a_serv_type = type;
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

    public String getA_serv_type() {
        return a_serv_type;
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
    
    public void setA_serv_type(String a_serv_type) {
        this.a_serv_type = a_serv_type;
    }

    public static String getTableName() {
        return "add_serv";
    }

    public static List<String> getFields() {
        List<String> ls = new ArrayList<>();
        ls.add("a_serv_id");
        ls.add("a_serv_title");
        ls.add("a_serv_price");
        ls.add("a_serv_type");
        return ls;
    }

    public static String search(ADServicesE type) {
        String sqlString = "from " + getTableName() + "t where t.";
        List<String> fields = getFields();

        switch (type) {
            case ID:
                sqlString = sqlString + fields.get(0) + " = ";
                break;
            case TITLE:
                sqlString = sqlString + fields.get(1) + " = '";
                break;
            case PRICE:
                sqlString = sqlString + fields.get(2) + " = ";
                break;
            case TYPE:
                sqlString = sqlString + fields.get(3) + " = '";
                break;
            case ALL:
                sqlString = "from " + getTableName();
                break;
            default:
                break;
        }

        return sqlString;
    }

    public List<String> toList() {
        List<String> res = new ArrayList<>();
        res.add(this.a_serv_id + "");
        res.add(this.a_serv_title);
        res.add(this.a_serv_price + "");
        return res;
    }

    @Override
    public String toString() {
        return "Additional services [ id = " + this.a_serv_id + " title: " + this.a_serv_title +
                " type: " + this.a_serv_type + " price: " + this.a_serv_price + " ]";
    }
}
