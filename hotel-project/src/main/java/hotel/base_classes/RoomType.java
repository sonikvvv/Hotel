package hotel.base_classes;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class RoomType {
    @Id
    private int room_type_id;
    private String room_type;

    public RoomType() {}

    public RoomType(String type) {
        this.room_type = type;
    }

    public int getRoom_id() {
        return room_type_id;
    }
    
    public String getRoom_type() {
        return room_type;
    }

    public void setRoom_id(int room_id) {
        this.room_type_id = room_id;
    }

    public void setRoom_type(String room_type) {
        this.room_type = room_type;
    }

    @Override
    public String toString() {
        return "Room type [ id = " + this.room_type_id + " type: " + this.room_type + " ]";
    }
}
