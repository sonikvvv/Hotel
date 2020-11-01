package hotel.base_classes;

public class ReservationCancelType {
    private int reservation_cancel_type_id;
    private String reservation_cancel_type;

    public ReservationCancelType() {
    }

    public ReservationCancelType(String type) {
        this.reservation_cancel_type = type;
    }

    public String getReservation_cancel_type() {
        return reservation_cancel_type;
    }

    public int getReservation_cancel_type_id() {
        return reservation_cancel_type_id;
    }

    public void setReservation_cancel_type(String reservation_type) {
        this.reservation_cancel_type = reservation_type;
    }

    public void setReservation_cancel_type_id(int reservation_type_id) {
        this.reservation_cancel_type_id = reservation_type_id;
    }

    @Override
    public String toString() {
        return "Reservation cancel type [ id = " + this.reservation_cancel_type_id + " type: " + this.reservation_cancel_type + " ]";
    }
}
