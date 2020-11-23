package logic.operations;

import java.util.List;

import base_classes.DBConnection;
import base_classes.classes.AdditServices;

public class AdditionalServicesOperations {

    public static List<AdditServices> getAllAdditionalServices(DBConnection db, int hotel_id) {
        List<AdditServices> result = 
        db.getAdditServicesByHotel(hotel_id);
        return result;
    }
}
