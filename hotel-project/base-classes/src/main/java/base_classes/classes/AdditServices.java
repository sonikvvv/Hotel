package base_classes.classes;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import base_classes.classes.emuns.ADServicesE;

@Entity(name = "add_serv")
public class AdditServices {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ads_generator")
    @SequenceGenerator(name = "ads_generator", sequenceName = "ads_seq")
    private int serv_id;
    private String title;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private ServiceCategory category;
    
    private double price;
    private int hotel_id;

    public AdditServices() {
    }

    public AdditServices(String title, ServiceCategory category, double price) {
        this.title = title;
        this.category = category;
        this.price = price;
    }

    public AdditServices(String title, ServiceCategory category, double price, int hotel_id) {
        this.title = title;
        this.category = category;
        this.price = price;
        this.hotel_id = hotel_id;
    }

    public ServiceCategory getCategory() {
        return category;
    }
    public double getPrice() {
        return price;
    }
    public int getServ_id() {
        return serv_id;
    }
    public String getTitle() {
        return title;
    }
    public int getHotel_id() {
        return hotel_id;
    }


    public void setCategory(ServiceCategory category) {
        this.category = category;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public void setServ_id(int serv_id) {
        this.serv_id = serv_id;
    }
    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    public static String getTableName() {
        return "add_serv";
    }

    public static List<String> getFields() {
        List<String> ls = new ArrayList<>();
        ls.add("serv_id");
        ls.add("title");
        ls.add("price");
        ls.add("category");
        ls.add("hotel_id");
        return ls;
    }

    public static String search(ADServicesE type) {
        String sqlString = "from " + getTableName() + "t where t.";
        List<String> fields = getFields();

        switch (type) {
            case ID:
                sqlString = sqlString + fields.get(0) + " = :value";
                break;
            case TITLE:
                sqlString = sqlString + fields.get(1) + " = :value";
                break;
            case PRICE:
                sqlString = sqlString + fields.get(2) + " = :value";
                break;
            case CATEGORY_ID:
                sqlString = sqlString + fields.get(3) + "." + ServiceCategory.getFields().get(0) + " = :value";
                break;
            case CATEGORY_NAME:
                sqlString = sqlString + fields.get(3) + "." + ServiceCategory.getFields().get(1) + " = :value";
                break;
            case HOTEL_ID:
                sqlString = sqlString + fields.get(4) + " = :value";
                break;
            default:
                break;
        }

        return sqlString;
    }

    @Override
    public String toString() {
        return "Additional services [ id = " + this.serv_id + " title: " + this.title +
                " category: " + this.category.getCategory_title() + " price: " + this.price + " ]";
    }


}
