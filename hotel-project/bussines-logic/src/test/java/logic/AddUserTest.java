package logic;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import base_classes.DBConnection;
import base_classes.classes.Hotel;
import base_classes.classes.User;
import base_classes.classes.emuns.URE;
import logic.operations.UserOperations;

public class AddUserTest {
    private Hotel h1 = new Hotel("Testivile");
    
    @AfterClass
    public static void clean_up() {
        DBConnection db = DecodeOperation.getDb();
        db.truncateAllTables();
    }
    
    @Before
    public void initialize() {
        DecodeOperation.decodeLogicOperation(OperationType.SAVE, h1, null);
    }

    @Test
    public void addUserTest() {
        List<String> data = new ArrayList<>();
        String user_name = "Webster66";
        String pass = "gvleFm1j3e_sh0N";
        String name = "Earline Reichert";
        String phone = "320-885-0009";
        String email = "Cali42@hotmail.com";
        String hotel = h1.getHotel_name();

        data.add(user_name);
        data.add(pass);
        data.add(name);
        data.add(phone);
        data.add(email);
        data.add(hotel);

        // Admin
        DecodeOperation.decodeLogicOperation(OperationType.ADD_TO_USERS, null, data);

        List<?> result = DecodeOperation.decodeLogicOperation(OperationType.GET_USERS, null, null);
        for (Object object : result) {
            User tmp = (User) object;
            if (tmp.getUser_name() == user_name) {
                assertEquals(URE.OWNER, tmp.getUser_role());
            }
        }

        // Owner
        UserOperations.setUser_now(new User("name", "password", URE.OWNER));

        data.clear();
        user_name = "Vincenzo_Raynor";
        data.add(user_name);
        data.add(pass);
        data.add(name);
        data.add(phone);
        data.add(email);
        data.add(hotel);

        DecodeOperation.decodeLogicOperation(OperationType.ADD_TO_USERS, null, data);
        result = DecodeOperation.decodeLogicOperation(OperationType.GET_USERS, null, null);
        for (Object object : result) {
            User tmp = (User) object;
            if (tmp.getUser_name() == user_name) {
                assertEquals(URE.MANAGER, tmp.getUser_role());
            }
        }

        // Manager
        User manager = new User("Hyman_Donnelly84", "password", URE.MANAGER);
        manager.addToHotel(h1);
        UserOperations.setUser_now(manager);

        data.clear();
        user_name = "Vincenza.Schultz64";
        data.add(user_name);
        data.add(pass);
        data.add(name);
        data.add(phone);
        data.add(email);
        data.add(hotel);

        DecodeOperation.decodeLogicOperation(OperationType.ADD_TO_USERS, null, data);
        result = DecodeOperation.decodeLogicOperation(OperationType.GET_USERS, null, null);
        for (Object object : result) {
            User tmp = (User) object;
            if (tmp.getUser_name() == user_name) {
                assertEquals(URE.RECEPTIONIST, tmp.getUser_role());
            }
        }
    }
}
