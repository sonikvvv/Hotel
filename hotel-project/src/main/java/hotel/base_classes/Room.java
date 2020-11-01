package hotel.base_classes;

public class Room {
    private int room_id;
    private String room_number;
    private double room_rating;
    private RoomType room_type;
    private RoomStatus status;

    public Room() {
    }

    public Room(String number, double rating, RoomType type, RoomStatus status) {
        this.room_number = number;
        this.room_rating = rating;
        this.room_type = type;
        this.status = status;
    }

    public int getRoom_id() {
        return room_id;
    }

    public String getRoom_number() {
        return room_number;
    }

    public double getRoom_rating() {
        return room_rating;
    }

    public RoomType getRoom_type() {
        return room_type;
    }

    public RoomStatus getStatus() {
        return status;
    }

    
    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public void setRoom_number(String room_number) {
        this.room_number = room_number;
    }

    public void setRoom_rating(double room_rating) {
        this.room_rating = room_rating;
    }

    public void setRoom_type(RoomType room_type) {
        this.room_type = room_type;
    }

    public void setStatus(RoomStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Room [ id = " + this.room_id + " number: " + this.room_number + " type: "
                + this.room_type.getRoom_type() + " status: " + this.status.getRoom_status() + " rating: "
                + this.room_rating + " ]";
    }
}