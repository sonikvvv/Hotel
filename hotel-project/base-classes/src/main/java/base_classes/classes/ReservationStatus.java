package base_classes.classes;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ReservationStatus {
    @Id
    private int reservation_status_id;
    private String reservation_status;

    public ReservationStatus() {
    }

    public ReservationStatus(String status) {
        this.reservation_status = status;
    }

    public String getReservation_status() {
        return reservation_status;
    }

    public int getReservation_status_id() {
        return reservation_status_id;
    }

    public void setReservation_status(String reservation_status) {
        this.reservation_status = reservation_status;
    }

    public void setReservation_status_id(int reservation_status_id) {
        this.reservation_status_id = reservation_status_id;
    }

    @Override
    public String toString() {
        return "Reservation status [ id = " + this.reservation_status_id + " status: " + this.reservation_status + " ]";
    }
}
