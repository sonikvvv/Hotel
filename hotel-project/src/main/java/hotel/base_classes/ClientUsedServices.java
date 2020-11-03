package hotel.base_classes;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class ClientUsedServices {
    private int client_used_services_id;
    
    private AdditServices addit_service;
    private int quantity;

    @Temporal(TemporalType.DATE)
    private Date date;
    private String note;


    private boolean paid = false;

    public ClientUsedServices() {
    }

    public ClientUsedServices(AdditServices addit_service, int quantity, String note) {
        this.addit_service = addit_service;
        this.quantity = quantity;
        this.date = new Date();
        this.note = note;
    }

    public AdditServices getAddit_service() {
        return addit_service;
    }

    public int getClient_used_services_id() {
        return client_used_services_id;
    }

    public Date getDate() {
        return date;
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

    public void setAddit_service(AdditServices addit_service) {
        this.addit_service = addit_service;
    }

    public void setClient_used_services_id(int client_used_services_id) {
        this.client_used_services_id = client_used_services_id;
    }

    public void setDate(Date date) {
        this.date = date;
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

    @Override
    public String toString() {
        return "Used additional services [ id = " + this.client_used_services_id + " additional service: "
                + this.addit_service.getAddit_services_title() + " price: "
                + this.addit_service.getAddit_services_price() + " date: " + this.date + " quantity: " + this.quantity
                + " paid: " + this.paid + " note: " + this.note + " ]";
    }

}
