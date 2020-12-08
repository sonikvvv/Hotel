package logic.operations;

import java.time.LocalDate;

public class RoomBusyness {
    private LocalDate date;
    private int[] room_busynes;

    public RoomBusyness() {
    }
    
    public RoomBusyness(LocalDate date, int[] room_busynes) {
        this.setDate(date);
        this.setRoom_busynes(room_busynes);
    }

    public int[] getRoom_busynes() {
        return room_busynes;
    }

    public void setRoom_busynes(int[] room_busynes) {
        this.room_busynes = room_busynes;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        String res = "";
        for (int i : this.room_busynes) {
            res += i + " ";
        }
        return this.date.toString() + " [ " + res + "]";
    }
}
