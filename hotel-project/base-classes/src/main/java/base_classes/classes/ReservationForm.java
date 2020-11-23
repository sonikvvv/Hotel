package base_classes.classes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ReservationForm {
    private String reservation_type;
    private String room_type;
    private String cancel_type;
    private String client_name;
    private LocalDate start_date;
    private LocalDate end_date;
    private int adults;
    private int kids;
    private int babys;
    private String food_type;
    
    @Column(columnDefinition = "Number(10,2)")
    private double total_price;
    private String status;
    private String notes;

    public ReservationForm() {
    }

    public ReservationForm(String reservation_type, String room_type, String cancel_type,
            LocalDate start_date, LocalDate end_date, int adults, int kids, int babys, String food_type, double total_price,
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

    public LocalDate getEnd_date() {
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

    public String getReservation_type() {
        return reservation_type;
    }

    public String getRoom_type() {
        return room_type;
    }

    public LocalDate getStart_date() {
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

    public void setEnd_date(LocalDate end_date) {
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

    public void setReservation_type(String reservation_type) {
        this.reservation_type = reservation_type;
    }

    public void setRoom_type(String room_type) {
        this.room_type = room_type;
    }

    public void setStart_date(LocalDate start_date) {
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
        ls.add("reservation_type");
        ls.add("cancel_type");
        ls.add("start_date");
        ls.add("end_date");
        ls.add("food_type");
        ls.add("status");
        ls.add("client_name");
        return ls;
    }

    @Override
    public String toString() {
        return "reservation type: " + this.reservation_type
                + " room type: " + this.room_type + " cancel type: " + this.cancel_type + " client name: " + this.client_name
                + " start date: " + this.start_date + " end date: " + this.end_date + " adults: " + this.adults
                + " kids: " + this.kids + " babys: " + this.babys + " food type: " + this.food_type + " total price: "
                + this.total_price + " status: " + this.status + " note: " + this.notes + " ";
    }

}
