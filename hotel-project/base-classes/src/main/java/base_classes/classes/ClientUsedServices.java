package base_classes.classes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.Type;

import base_classes.classes.emuns.ADServicesE;
import base_classes.classes.emuns.CUSe;

@Entity(name = "c_used_serv")
public class ClientUsedServices {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cus_generator")
    @SequenceGenerator(name = "cus_generator", sequenceName = "cus_seq", allocationSize = 50)
    private int cus_id;
    
    @OneToOne(cascade = CascadeType.ALL)
    private AdditServices addit_service;
    private int quantity;
    private Date purchase_date;
    private String note;

    @Type(type = "true_false")
    private boolean paid = false;

    public ClientUsedServices() {
    }

    public ClientUsedServices(AdditServices addit_service, int quantity, String note) {
        this.addit_service = addit_service;
        this.quantity = quantity;
        this.purchase_date = new Date();
        this.note = note;
    }

    public AdditServices getAddit_service() {
        return addit_service;
    }

    public int getCus_id() {
        return cus_id;
    }

    public Date getDate() {
        return purchase_date;
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

    public void setDate(Date purchase_date) {
        this.purchase_date = purchase_date;
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
        ls.add("purchase_date");
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
                + this.addit_service.getAddit_services_price() + " purchase_date: " + this.purchase_date + " quantity: " + this.quantity
                + " paid: " + this.paid + " note: " + this.note + " ]";
    }

}
