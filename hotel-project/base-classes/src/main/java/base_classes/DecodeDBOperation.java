package base_classes;

import java.util.ArrayList;
import java.util.List;
import base_classes.classes.*;
import base_classes.classes.emuns.*;

public class DecodeDBOperation {
    public static List<String> decodeDBOperation(DBOperationType type_db, ClassesE type_c, List<String> data) {
        DBConnection db = new DBConnection();
        List<String> res = new ArrayList<>();
        switch (type_db) {
            case save:

                db.saveObject(null);
                res.add("false");
                break;
            case getUser:

                res.add("false");
                break;
            case CHECK_USER:
                User check_user = db.getUser(UE.NAME, data.get(0));
                if (check_user == null)
                    res.add("false");
                else {
                    if (check_user.getUser_password().equalsIgnoreCase(data.get(1)))
                        res.add("true");
                }
                break;
            default:

        }

        return res;
    }
}
