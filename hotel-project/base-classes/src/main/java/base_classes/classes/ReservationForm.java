package base_classes.classes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import base_classes.classes.emuns.RFE;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

/**
 * The {@code Reservation Form} class contains the information, that the hotel
 * receives from tour agencies or booking sites.
 */
@Entity(name = "r_form")
public class ReservationForm {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rf_generator")
    @SequenceGenerator(name = "rf_generator", sequenceName = "rf_seq", allocationSize = 50)
    private int reservation_form_id;

    private String reservation_type;
    private String room_type;
    private String cancel_type;
    private String client_name;

    @Temporal(TemporalType.DATE)
    private Date start_date;

    @Temporal(TemporalType.DATE)
    private Date end_date;

    private int adults;
    private int kids;
    private int babys;
    private String food_type;
    private double total_price;
    private String status;
    private String notes;

    public ReservationForm() {
    }

    public ReservationForm(String reservation_type, String room_type, String cancel_type,
            Date start_date, Date end_date, int adults, int kids, int babys, String food_type, double total_price,
            String status, String notes, String client_name) {
        this.reservation_type = reservation_type;
        this.room_type = room_type;
        this.cancel_type = cancel_type;
        this.start_date = start_date;
        this.end_date = end_date;
        this.adults = adults;
        this.kids = kids;
        this.babys = babys;
        this.food_type = food_type;
        this.total_price = total_price;
        this.status = status;
        this.notes = notes;
        this.client_name = client_name;
    }

    public int getAdults() {
        return adults;
    }

    public int getBabys() {
        return babys;
    }

    public String getCancel_type() {
        return cancel_type;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public String getFood_type() {
        return food_type;
    }

    public int getKids() {
        return kids;
    }

    public String getNotes() {
        return notes;
    }

    public int getReservation_form_id() {
        return reservation_form_id;
    }

    public String getReservation_type() {
        return reservation_type;
    }

    public String getRoom_type() {
        return room_type;
    }

    public Date getStart_date() {
        return start_date;
    }

    public String getStatus() {
        return status;
    }

    public double getTotal_price() {
        return total_price;
    }

    public String getClient_name() {
        return client_name;
    }



    public void setAdults(int adults) {
        this.adults = adults;
    }

    public void setBabys(int babys) {
        this.babys = babys;
    }

    public void setCancel_type(String cancel_type) {
        this.cancel_type = cancel_type;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public void setFood_type(String food_type) {
        this.food_type = food_type;
    }

    public void setKids(int kids) {
        this.kids = kids;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setReservation_form_id(int reservation_form_id) {
        this.reservation_form_id = reservation_form_id;
    }

    public void setReservation_type(String reservation_type) {
        this.reservation_type = reservation_type;
    }

    public void setRoom_type(String room_type) {
        this.room_type = room_type;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    public void setClient_name(String client_name) {
        this.client_name = client_name;
    }

    public static List<String> getFields() {
        List<String> ls = new ArrayList<>();
        ls.add("reservation_form_id");
        ls.add("reservation_type");
        ls.add("room_type");
        ls.add("cancel_type");
        ls.add("start_date");
        ls.add("end_date");
        ls.add("adults");
        ls.add("kids");
        ls.add("babys");
        ls.add("food_type");
        ls.add("status");
        ls.add("client_name");
        return ls;
    }

    public static String getTableName() {
        return "r_form";
    }

    public static String search(RFE type) {
        String sqlString = "from " + getTableName() + " where ";
        List<String> fields = getFields();

        switch (type) {
            case ID:
                sqlString = sqlString + fields.get(0) + " = ";
                break;
            case TYPE:
                sqlString = sqlString + fields.get(1) + " = '";
                break;
            case ROOM_TYPE:
                sqlString = sqlString + fields.get(2) + " = '";
                break;
            case CANCEL_TYPE:
                sqlString = sqlString + fields.get(3) + " = '";
                break;
            case START_DATE:
                sqlString = sqlString + fields.get(5) + " like to_date('";
                break;
            case END_DATE:
                sqlString = sqlString + fields.get(6) + " like to_date('";
                break;
            case ADULTS:
                sqlString = sqlString + fields.get(7) + " = ";
                break;
            case KIDS:
                sqlString = sqlString + fields.get(8) + " = ";
                break;
            case BABYS:
                sqlString = sqlString + fields.get(9) + " = ";
                break;
            case FOOD_TYPE:
                sqlString = sqlString + fields.get(10) + " = '";
                break;
            case STATUS:
                sqlString = sqlString + fields.get(11) + " = '";
                break;
            case CLIENT_NAME:
                sqlString = sqlString + fields.get(12) + " = '";
            case ALL:
                sqlString = "from " + getTableName();
                break;

            default:
                break;
        }

        return sqlString;
    }

    @Override
    public String toString() {
        return "Reservation form [ id = " + this.reservation_form_id + " reservation type: " + this.reservation_type
                + " room type: " + this.room_type + " cancel type: " + this.cancel_type + " client name: " + this.client_name
                + " start date: " + this.start_date + " end date: " + this.end_date + " adults: " + this.adults
                + " kids: " + this.kids + " babys: " + this.babys + " food type: " + this.food_type + " total price: "
                + this.total_price + " status: " + this.status + " note: " + this.notes + " ]";
    }

}
