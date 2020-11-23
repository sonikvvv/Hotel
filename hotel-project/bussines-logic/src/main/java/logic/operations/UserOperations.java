package logic.operations;

import java.util.ArrayList;
import java.util.List;

import base_classes.DBConnection;
import base_classes.classes.Hotel;
import base_classes.classes.User;
import base_classes.classes.emuns.URE;

public class UserOperations {
    private static User user_now = new User("name", "password", URE.OWNER);

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
        User user = db.getUserByUsername(data.get(0));
        List <String> result = new ArrayList<>();
        if (user == null) result.add("false");
        else {
            if (user.getUser_password().equals(data.get(1)))
                result.add("true");
                setUser_now(user);
        }
        return result;
    }

    public static List<User> getUsers(DBConnection db, List<String> data) {
        List<User> result = new ArrayList<>();
        // if (user_now.getUser_role() == URE.ADMIN)
            result = db.getAllUsers();
        // else if (user_now.getUser_role() == URE.OWNER){
            // List<Hotel> hotels = UserOperations.user_now.getHotel();
            // for (Hotel hotel : hotels) {
                // result.addAll(db.getUserByHotel(hotel.getHotel_id()));
            // }
        // }
        return result;
    }

    public static List<User> getReceptionists(DBConnection db, List<String> data) {
        List<User> result = 
        db.getUserByRole(URE.RECEPTIONIST);
        return result;
    }

    public static void addUser(DBConnection db, List<String> data) {
        User newUser = new User();
        newUser.setUser_name(data.get(0));
        newUser.setUser_password(data.get(1));
        newUser.setName(data.get(2));
        newUser.setPhone(data.get(3));
        newUser.setEmail(data.get(4));

        switch (user_now.getUser_role()) {
            case ADMIN:
                newUser.setUser_role(URE.OWNER);
                break;
            case MANAGER:
                newUser.setUser_role(URE.RECEPTIONIST);
                newUser.addToHotel(user_now.getHotel().get(0));
                break;
            case OWNER:
                newUser.setUser_role(URE.MANAGER);
                break;
            case RECEPTIONIST:
                break;
            default:
                break;
        }
        
        

    }
}
