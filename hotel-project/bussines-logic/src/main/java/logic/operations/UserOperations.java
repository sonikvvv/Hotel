package logic.operations;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import base_classes.DBConnection;
import base_classes.classes.Hotel;
import base_classes.classes.User;
import base_classes.classes.emuns.URE;

public class UserOperations {
    private static User user_now = new User("name", "password", URE.ADMIN);
    private static final Logger LOGGER = LogManager.getLogger(ClientOperations.class);

    public static List<User> getUser_now() {
        List<User> tmp = new ArrayList<>();
        tmp.add(user_now);
        return tmp;
    }

    public static void setUser_now(User user_now) {
        UserOperations.user_now = user_now;
    }

    public UserOperations() {
        Hotel h = new Hotel("Testivile");
        h.setHotel_id(1);
        user_now.addToHotel(h);
    }

    public static List<String> authenticationOperation(DBConnection db, List<String> data) {
        LOGGER.debug("Starting authenticationOperation with data {}.", data);
        User user = db.getUserByUsername(data.get(0));
        List <String> result = new ArrayList<>();
        if (user == null) result.add("false");
        else {
            if (user.getUser_password().equals(data.get(1)))
                result.add("true");
                setUser_now(user);
        }

        LOGGER.debug("Result. {}", result);
        return result;
    }

    public static List<User> getUsers(DBConnection db) {
        LOGGER.debug("Starting getUsers.");
        List<User> result = new ArrayList<>();
        if (user_now.getUser_role() == URE.ADMIN) {
            result = db.getAllUsers();
            LOGGER.debug("Getting users from all hotels.");
        } else if (user_now.getUser_role() == URE.OWNER){
            List<Hotel> hotels = UserOperations.user_now.getHotel();
            for (Hotel hotel : hotels) {
                result.addAll(db.getUserByHotel(hotel.getHotel_id()));
                LOGGER.debug("Getting users from hotel id: {}.", hotel.getHotel_id());
            }
        }

        LOGGER.debug("Result. {}", result);
        return result;
    }

    public static List<User> getReceptionists(DBConnection db) {
        LOGGER.debug("Starting getReceptionists.");
        User user_now = UserOperations.getUser_now().get(0);
        List<User> result = getUsers(db);
        List<User> receptionists = new ArrayList<>();
        for (User user : receptionists) {
            if (user.getUser_role() == URE.RECEPTIONIST){
                for (Hotel hotel : user.getHotel()) {
                    for (Hotel hotel2 : user_now.getHotel()) {
                        if (hotel.getHotel_id() == hotel2.getHotel_id()) {
                            result.add(user);
                            break;
                        }
                    }
                }
            }
        }

        LOGGER.debug("Result. {}", result);
        return result;
    }

    public static void addUser(DBConnection db, List<String> data) {
        LOGGER.debug("Starting addUser.");
        User newUser = new User();
        newUser.setUser_name(data.get(0));
        newUser.setUser_password(data.get(1));
        newUser.setName(data.get(2));
        newUser.setPhone(data.get(3));
        newUser.setEmail(data.get(4));

        switch (user_now.getUser_role()) {
            case ADMIN:
                LOGGER.debug("ADMIN");
                newUser.setUser_role(URE.OWNER);
                String[] hotels = data.get(5).split(",");
                List<Hotel> hotel_obj = new ArrayList<>();
                for (String string : hotels) {
                    Hotel tmp = db.getHotelByName(string);
                    if(tmp != null)
                        hotel_obj.add(tmp);
                }
                break;
            case MANAGER:
                LOGGER.debug("MANAGER");
                newUser.setUser_role(URE.RECEPTIONIST);
                newUser.addToHotel(user_now.getHotel().get(0));
                break;
            case OWNER:
                LOGGER.debug("OWNER");
                newUser.setUser_role(URE.MANAGER);
                String[] hotels_ = data.get(5).split(",");
                List<Hotel> hotel_obj_ = new ArrayList<>();
                for (String string : hotels_) {
                    Hotel tmp = db.getHotelByName(string);
                    if (tmp != null)
                        hotel_obj_.add(tmp);
                }
                break;
            default:
                break;
        }
        LOGGER.debug("Result. {}", newUser.toString());
        db.saveOrUpdateObject(newUser);

    }
}
