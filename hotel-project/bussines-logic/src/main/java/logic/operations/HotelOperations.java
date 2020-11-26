package logic.operations;

import java.util.ArrayList;
import java.util.List;

import base_classes.DBConnection;
import base_classes.classes.Hotel;
import base_classes.classes.User;
import base_classes.classes.emuns.URE;


public class HotelOperations {
    private Hotel hotel_now;

    public Hotel getHotel_now() {
        return hotel_now;
    }

    public void setHotel_now(Hotel hotel_now) {
        this.hotel_now = hotel_now;
    }

	public static List<Hotel> getHotels(DBConnection db) {
        User user_now = UserOperations.getUser_now().get(0);
        List<Hotel> hotels = new ArrayList<>();
        if (user_now.getUser_role() == URE.ADMIN) {
            hotels = db.getAllHotels();
        }
        else {
            for (Hotel hotel : user_now.getHotel()) {
                hotels.add(db.getHotelById(hotel.getHotel_id()));
            }
        }
        return hotels;
	}
}
