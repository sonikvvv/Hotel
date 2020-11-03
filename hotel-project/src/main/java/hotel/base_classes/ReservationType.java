package hotel.base_classes;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ReservationType {
    @Id
    private int reservation_type_id;
    private String reservation_type;

    public ReservationType() {}

    public ReservationType(String type) {
        this.reservation_type = type;
    }

    public String getReservation_type() {
        return reservation_type;
    }
    public int getReservation_type_id() {
        return reservation_type_id;
    }

    public void setReservation_type(String reservation_type) {
        this.reservation_type = reservation_type;
    }
    public void setReservation_type_id(int reservation_type_id) {
        this.reservation_type_id = reservation_type_id;
    }

    @Override
    public String toString() {
        return "Reservation type [ id = " + this.reservation_type_id + " type: " + this.reservation_type + " ]"; 
    }
}
