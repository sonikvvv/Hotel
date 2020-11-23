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

@Entity(name = "add_serv")
public class AdditServices {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ads_generator")
    @SequenceGenerator(name = "ads_generator", sequenceName = "ads_seq", allocationSize = 1)
    private int serv_id;
    private String title;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private ServiceCategory category;
    
    private double price;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    public AdditServices() {
    }

    public AdditServices(String title, ServiceCategory category, double price) {
        this.title = title;
        this.category = category;
        this.price = price;
    }

    public AdditServices(String title, ServiceCategory category, double price, Hotel hotel) {
        this.title = title;
        this.category = category;
        this.price = price;
        this.hotel = hotel;
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
    public Hotel getHotel() {
        return hotel;
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
    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
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
        ls.add("hotel");
        return ls;
    }

    @Override
    public String toString() {
        return "Additional services [ id = " + this.serv_id + " title: " + this.title +
                " category: " + this.category.getCategory_title() + " price: " + this.price + " ]";
    }


}
