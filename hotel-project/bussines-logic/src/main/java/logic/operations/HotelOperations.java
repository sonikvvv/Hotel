package logic.operations;

import java.util.ArrayList;
import java.util.List;

import base_classes.DBConnection;
import base_classes.classes.Hotel;

public class HotelOperations {

	public static List<?> getHotels(DBConnection db, List<String> data) {
		Hotel hh = new Hotel("testivile");
        List<Hotel> hl = new ArrayList<>();
        hl.add(hh);
        return hl;
	}
}
