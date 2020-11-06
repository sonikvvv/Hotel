package base_classes.classes;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The {@code Reservation Form} class contains the information, that the hotel
 * receives from tour agencies or booking sites.
 */
@Entity
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

    @Override
    public String toString() {
        return "Reservation form [ id = " + this.reservation_form_id + " reservation type: "
                + this.reservation_type.getReservation_type() + " room type: " + this.room_type.getRoom_type()
                + " cancel type: " + this.cancel_type.getReservation_cancel_type() + " vaucher: " + this.vaucher
                + " start date: " + this.start_date + " end date: " + this.end_date + " adults: " + this.adults
                + " kids: " + this.kids + " babys: " + this.babys + " food type: " + this.food_type.getFood_type()
                + " total price: " + this.total_price + " status: " + this.status.getReservation_status() + " note: "
                + this.notes + " ]";
    }

}
