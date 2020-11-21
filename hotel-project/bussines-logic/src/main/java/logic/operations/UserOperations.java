package logic.operations;

import java.util.ArrayList;
import java.util.List;

import base_classes.DBConnection;
import base_classes.classes.User;
import base_classes.classes.emuns.UE;
import base_classes.classes.emuns.URE;

public class UserOperations {
    private static User user_now;
    private static List<User> result = new ArrayList<>();

    public static List<User> getUser_now() {
        List<User> tmp = new ArrayList<>();
        tmp.add(user_now);
        return tmp;
    }

    public static void setUser_now(User user_now) {
        UserOperations.user_now = user_now;
    }

    public static List<?> authenticationOperation(DBConnection db, List<String> data) {
        List<User> res = db.getUserList(UE.NAME, data.get(0));
        List <String> result = new ArrayList<>();
        for (Object object : res) {
            User u = (User) object;
            if (u.getUser_password().equals(data.get(1)));
                result.add("true"); result.add(u.getUser_role().toString());
        }
        if (result.size() == 0 || result == null) result.add("false");
        return result;
    }

    public static List<User> getUsers(DBConnection db, List<String> data) {
        if( result.size() != 0) {
            return result;
        }else {
            User u = new User("Loraine75", "autiTYkKRO1S2g2", URE.OWNER);
            User u1 = new User("Hans.Lakin83", "jwr_6HCmF6EdMjr", URE.OWNER);
            User u2 = new User("Dejon_Casper34", "ECWnGq01UOj_5Mm", URE.OWNER);
            User u3 = new User("Nadia.Goldner", "jMcKKo_JBPGdWPD", URE.MANAGER);
            result.add(u);
            result.add(u1);
            result.add(u2);
            result.add(u3);
            
            return result;
        }
    }

    public static List<User> getReceptionists(DBConnection db, List<String> data) {
        if( result.size() != 0) {
            return result;
        }else {
            User u = new User("Loraine75", "autiTYkKRO1S2g2", URE.OWNER);
            User u1 = new User("Hans.Lakin83", "jwr_6HCmF6EdMjr", URE.OWNER);
            User u2 = new User("Dejon_Casper34", "ECWnGq01UOj_5Mm", URE.OWNER);
            User u3 = new User("Nadia.Goldner", "jMcKKo_JBPGdWPD", URE.MANAGER);
            result.add(u);
            result.add(u1);
            result.add(u2);
            result.add(u3);
            
            return result;
        }
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
                newUser.setHotel_ids(user_now.getHotel_ids());
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
