package logic.operations;

import java.util.ArrayList;
import java.util.List;

import base_classes.DBConnection;
import base_classes.classes.User;
import base_classes.classes.emuns.UE;

@SuppressWarnings("rawtypes")
public class UserOperations {

    public static List authenticationOperation(DBConnection db, List<String> data) {
        List res = db.getUserList(UE.NAME, data.get(0));
        List <String> result = new ArrayList<>();
        for (Object object : res) {
            User u = (User) object;
            if (u.getUser_password().equals(data.get(1)));
                result.add("true"); result.add(u.getUser_role().toString());
        }
        if (result.size() == 0 || result == null) result.add("false");
        return result;
    }

    public static List getReceptCreatedReservations(DBConnection db, List<String> data) {
        return null; //db.getRceptionistReservations(data.get(0));
    }
}
