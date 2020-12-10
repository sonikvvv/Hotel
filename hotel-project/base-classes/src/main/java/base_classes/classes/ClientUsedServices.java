package base_classes.classes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PreRemove;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.Type;

@Entity(name = "c_used_serv")
public class ClientUsedServices {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cus_generator")
    @SequenceGenerator(name = "cus_generator", sequenceName = "cus_seq", allocationSize = 1)
    private int cus_id;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "serv_id")
    private AdditServices addit_service;
    private int quantity;
    private LocalDateTime purchase_date;
    private String note;

    @Column(columnDefinition = "Number(10,2)")
    private double total;

    @Type(type = "true_false")
    private boolean paid = false;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    public ClientUsedServices() {
    }

    public ClientUsedServices(AdditServices addit_service, int quantity, String note) {
        this.addit_service = addit_service;
        this.quantity = quantity;
        this.purchase_date = LocalDateTime.now();
        this.note = note;
        calcTotal();
    }

    public ClientUsedServices(AdditServices addit_service, int quantity, String note, Hotel hotel) {
        this.addit_service = addit_service;
        this.quantity = quantity;
        this.purchase_date = LocalDateTime.now();
        this.note = note;
        this.hotel = hotel;
    }

    public AdditServices getAddit_service() {
        return addit_service;
    }

    public int getCus_id() {
        return cus_id;
    }

    public String getNote() {
        return note;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean getPaid() {
        return paid;
    }
    
    public Hotel getHotel() {
        return hotel;
    }

    public LocalDateTime getPurchase_date() {
        return purchase_date;
    }

    public double getTotal() {
        return total;
    }


    public void setAddit_service(AdditServices addit_service) {
        this.addit_service = addit_service;
    }

    public void setCus_id(int cus_id) {
        this.cus_id = cus_id;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPurchase_date(LocalDateTime purchase_date) {
        this.purchase_date = purchase_date;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    private void calcTotal() {
        double total = this.quantity * this.addit_service.getPrice();
        this.total = total;
    }

    @PreRemove
    public void detach() {
        this.addit_service = null;
        this.hotel = null;
    }

    public static List<String> getFields() {
        List<String> ls = new ArrayList<>();
        ls.add("cus_id");
        ls.add("addit_service");
        ls.add("quantity");
        ls.add("purchase_date");
        return ls;
    }

    public static String getTableName() {
        return "c_used_serv";
    }

    @Override
    public String toString() {
        return "Used additional services [ id = " + this.cus_id + " additional service: "
                + this.addit_service.getTitle() + " price: "
                + this.addit_service.getPrice() + " purchase_date: " + this.purchase_date + " quantity: " + this.quantity
                + " paid: " + this.paid + " note: " + this.note + " ]";
    }

}
