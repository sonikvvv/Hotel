package logic.operations;

import java.util.ArrayList;
import java.util.List;

import base_classes.DBConnection;
import base_classes.classes.Hotel;


public class HotelOperations {
    private Hotel hotel_now;

    public Hotel getHotel_now() {
        return hotel_now;
    }

    public void setHotel_now(Hotel hotel_now) {
        this.hotel_now = hotel_now;
    }

	public static List<?> getHotels(DBConnection db, List<String> data) {
		Hotel hh = new Hotel("testivile");
		Hotel hh1 = new Hotel("testivile1");
		Hotel hh2 = new Hotel("testivile2");
        List<Hotel> hl = new ArrayList<>();
        hl.add(hh);
        hl.add(hh1);
        hl.add(hh2);
        return hl;
	}
}
