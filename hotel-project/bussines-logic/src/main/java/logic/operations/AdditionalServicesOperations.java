package logic.operations;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import base_classes.DBConnection;
import base_classes.classes.AdditServices;
import base_classes.classes.Hotel;
import base_classes.classes.User;
import base_classes.classes.emuns.URE;

public class AdditionalServicesOperations {
    private static final Logger LOGGER = LogManager.getLogger(AdditionalServicesOperations.class);

    public static List<AdditServices> getAllAdditionalServices(DBConnection db) {
        LOGGER.debug("Starting getAllAdditionalServices.");
        User user_now = UserOperations.getUser_now().get(0);
        List<AdditServices> result = new ArrayList<>();
        
        if (user_now.getUser_role() == URE.ADMIN) {
            result = db.getAllAdditServices();
            LOGGER.debug("Getting additional services for all hotels.");
        } else {
            for (Hotel hotel : user_now.getHotel()) {
                result.addAll(db.getAdditServicesByHotel(hotel.getHotel_id()));
                LOGGER.debug("Getting additional services for hotel id: {}", hotel.getHotel_id());
            }
        }
        return result;
    }
}
