package base_classes.classes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Type;

import base_classes.classes.emuns.ADServicesE;
import base_classes.classes.emuns.CUSe;

@Entity(name = "c_used_serv")
public class ClientUsedServices {
    @Id
    private int cus_id;
    
    @OneToOne
    private AdditServices addit_service;
    private int quantity;
    private Date date;
    private String note;

    @Type(type = "true_false")
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

    public int getCus_id() {
        return cus_id;
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

    public void setCus_id(int cus_id) {
        this.cus_id = cus_id;
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

    public static List<String> getFields() {
        List<String> ls = new ArrayList<>();
        ls.add("cus_id");
        ls.add("addit_service");
        ls.add("quantity");
        ls.add("date");
        return ls;
    }

    public static String getTableName() {
        return "c_used_serv";
    }

    public String search(CUSe type) {
        String sqlString = "from " + getTableName() + " where ";
        List<String> fields = getFields();

        switch (type) {
            case ID:
                sqlString = sqlString + fields.get(0) + " = ";
                break;
            case ADDIT_SERVICE_ID:
                sqlString = sqlString + fields.get(1) + " = ";
                break;
            case ADDIT_SERVICE_NAME:
                sqlString = sqlString + fields.get(1) + " = " + this.addit_service.search(ADServicesE.TITLE);
                break;
            case QUANTITY:
                sqlString = sqlString + fields.get(2) + " = ";
                break;
            case DATE:
                sqlString = sqlString + fields.get(3) + " = ";
                break;

            default:
                break;
        }

        return sqlString;
    }

    @Override
    public String toString() {
        return "Used additional services [ id = " + this.cus_id + " additional service: "
                + this.addit_service.getAddit_services_title() + " price: "
                + this.addit_service.getAddit_services_price() + " date: " + this.date + " quantity: " + this.quantity
                + " paid: " + this.paid + " note: " + this.note + " ]";
    }

}
