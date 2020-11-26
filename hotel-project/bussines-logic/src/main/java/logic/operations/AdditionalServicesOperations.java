package logic.operations;

import java.util.ArrayList;
import java.util.List;

import base_classes.DBConnection;
import base_classes.classes.AdditServices;
import base_classes.classes.Hotel;
import base_classes.classes.User;
import base_classes.classes.emuns.URE;

public class AdditionalServicesOperations {

    public static List<AdditServices> getAllAdditionalServices(DBConnection db) {
        User user_now = UserOperations.getUser_now().get(0);
        List<AdditServices> result = new ArrayList<>();
        
        if (user_now.getUser_role() == URE.ADMIN) {
            result = db.getAllAdditServices();
        } else {
            for (Hotel hotel : user_now.getHotel()) {
                result.addAll(db.getAdditServicesByHotel(hotel.getHotel_id()));
            }
        }
        return result;
    }
}
