package base_classes.classes;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class AdditServices {
    @Id
    private int addit_services_id;
    private String addit_services_title;
    private double addit_services_price;

    public AdditServices() {
    }

    public AdditServices(String title, double price) {
        this.addit_services_title = title;
        this.addit_services_price = price;
    }

    public int getAddit_services_id() {
        return addit_services_id;
    }

    public double getAddit_services_price() {
        return addit_services_price;
    }

    public String getAddit_services_title() {
        return addit_services_title;
    }

    public void setAddit_services_id(int addit_services_id) {
        this.addit_services_id = addit_services_id;
    }

    public void setAddit_services_price(double addit_services_price) {
        this.addit_services_price = addit_services_price;
    }

    public void setAddit_services_title(String addit_services_title) {
        this.addit_services_title = addit_services_title;
    }

    @Override
    public String toString() {
        return "Additional services [ id = " + this.addit_services_id + " title: " + this.addit_services_title
                + " price: " + this.addit_services_price + " ]";
    }
}
