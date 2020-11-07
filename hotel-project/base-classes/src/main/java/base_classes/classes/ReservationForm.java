package base_classes.classes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import base_classes.classes.emuns.FTE;
import base_classes.classes.emuns.RCTE;
import base_classes.classes.emuns.RFE;
import base_classes.classes.emuns.RTE;
import base_classes.classes.emuns.SE;

/**
 * The {@code Reservation Form} class contains the information, that the hotel
 * receives from tour agencies or booking sites.
 */
@Entity(name = "r_form")
public class ReservationForm {
    @Id
    private int reservation_form_id;
    @OneToMany
    private ReservationType reservation_type;
    @OneToMany
    private RoomType room_type;
    @OneToMany
    private ReservationCancelType cancel_type;
    private String vaucher;
    @Temporal(TemporalType.DATE)
    private Date start_date;
    @Temporal(TemporalType.DATE)
    private Date end_date;
    private int adults;
    private int kids;
    private int babys;
    @OneToMany
    private FoodType food_type;
    private double total_price;
    @OneToMany
    private ReservationStatus status;
    private String notes;

    public ReservationForm() {
    }

    public ReservationForm(ReservationType reservation_type, RoomType room_type, ReservationCancelType cancel_type,
            String vaucher, Date start_date, Date end_date, int adults, int kids, int babys, FoodType food_type,
            double total_price, ReservationStatus status, String notes) {
        this.reservation_type = reservation_type;
        this.room_type = room_type;
        this.cancel_type = cancel_type;
        this.vaucher = vaucher;
        this.start_date = start_date;
        this.end_date = end_date;
        this.adults = adults;
        this.kids = kids;
        this.babys = babys;
        this.food_type = food_type;
        this.total_price = total_price;
        this.status = status;
        this.notes = notes;
    }

    public int getAdults() {
        return adults;
    }

    public int getBabys() {
        return babys;
    }

    public ReservationCancelType getCancel_type() {
        return cancel_type;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public FoodType getFood_type() {
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

    public ReservationType getReservation_type() {
        return reservation_type;
    }

    public RoomType getRoom_type() {
        return room_type;
    }

    public Date getStart_date() {
        return start_date;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public double getTotal_price() {
        return total_price;
    }

    public String getVaucher() {
        return vaucher;
    }

    public void setAdults(int adults) {
        this.adults = adults;
    }

    public void setBabys(int babys) {
        this.babys = babys;
    }

    public void setCancel_type(ReservationCancelType cancel_type) {
        this.cancel_type = cancel_type;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public void setFood_type(FoodType food_type) {
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

    public void setReservation_type(ReservationType reservation_type) {
        this.reservation_type = reservation_type;
    }

    public void setRoom_type(RoomType room_type) {
        this.room_type = room_type;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    public void setVaucher(String vaucher) {
        this.vaucher = vaucher;
    }

    public static List<String> getFields() {
        List<String> ls = new ArrayList<>();
        ls.add("reservation_form_id");
        ls.add("reservation_type");
        ls.add("room_type");
        ls.add("cancel_type");
        ls.add("vaucher");
        ls.add("start_date");
        ls.add("end_date");
        ls.add("adults");
        ls.add("kids");
        ls.add("babys");
        ls.add("food_type");
        ls.add("status");
        return ls;
    }

    public static String getTableName() {
        return "r_form";
    }

    public String search(RFE type) {
        String sqlString = "from " + getTableName() + " where ";
        List<String> fields = getFields();

        switch (type) {
            case ID:
                sqlString = sqlString + fields.get(0) + " = ";
                break;
            case TYPE:
                sqlString = sqlString + fields.get(1) + " = " + this.reservation_type.search(RTE.TYPE);
                break;
            case ROOM_TYPE:
                sqlString = sqlString + fields.get(2) + " = " + this.room_type.search(RTE.TYPE);
                break;
            case CANCEL_TYPE:
                sqlString = sqlString + fields.get(3) + " = " + this.cancel_type.search(RCTE.CANCEL_TYPE);
                break;
            case VAUCHER:
                sqlString = sqlString + fields.get(4) + " = ";
                break;
            case START_DATE:
                sqlString = sqlString + fields.get(5) + " = ";
                break;
            case END_DATE:
                sqlString = sqlString + fields.get(6) + " = ";
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
                sqlString = sqlString + fields.get(10) + " = " + this.food_type.search(FTE.TYPE);
                break;
            case STATUS:
                sqlString = sqlString + fields.get(11) + " = " + this.status.search(SE.STATUS);
                break;

            default:
                break;
        }

        return sqlString;
    }

    @Override
    public String toString() {
        return "Reservation form [ id = " + this.reservation_form_id + " reservation type: "
                + this.reservation_type.getR_type() + " room type: " + this.room_type.getRoom_type()
                + " cancel type: " + this.cancel_type.getReservation_cancel_type() + " vaucher: " + this.vaucher
                + " start date: " + this.start_date + " end date: " + this.end_date + " adults: " + this.adults
                + " kids: " + this.kids + " babys: " + this.babys + " food type: " + this.food_type.getFood_type()
                + " total price: " + this.total_price + " status: " + this.status.getReservation_status() + " note: "
                + this.notes + " ]";
    }

}
