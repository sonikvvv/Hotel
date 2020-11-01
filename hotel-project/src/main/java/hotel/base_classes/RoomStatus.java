package hotel.base_classes;

public class RoomStatus {
    private int room_status_id;
    private String room_status;

    public RoomStatus() {}

    public RoomStatus(String room_status) {
        this.room_status = room_status;
    }

    public String getRoom_status() {
        return room_status;
    }
    public int getRoom_status_id() {
        return room_status_id;
    }

    public void setRoom_status(String room_status) {
        this.room_status = room_status;
    }
    public void setRoom_status_id(int room_status_id) {
        this.room_status_id = room_status_id;
    }

    @Override
    public String toString() {
        return "Room status [ id = " + this.room_status_id + " status: " + this.room_status + " ]";
    }
}
