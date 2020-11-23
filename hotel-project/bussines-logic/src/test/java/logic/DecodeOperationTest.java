package logic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import base_classes.classes.AdditServices;
import base_classes.classes.User;
import base_classes.classes.emuns.URE;

public class DecodeOperationTest {

    @Test
    public void getADSTest() {
        OperationType type = OperationType.GET_ADS;
        List<String> data = new ArrayList<>();
        data.add(1 + "");
        List<?> result = DecodeOperation.decodeLogicOperation(type, null, data);
        assertNotNull(result);
        assertTrue((result.size() == 0)? false:true);
        assertTrue((result.get(0) instanceof AdditServices)? true:false);
    }

    @Test
    public void authenticationTest() {
        User user_now = new User("name", "password", URE.ADMIN);
        OperationType type = OperationType.LOGIN;
        List<String> data = new ArrayList<>();
        data.add(user_now.getUser_name());
        data.add(user_now.getUser_password());
        List<?> result = DecodeOperation.decodeLogicOperation(type, null, data);
        for (Object object : result) {
            assertEquals("false", (String) object);
        }

        // if data base if full with test data
        // data.clear();
        // data.add("Uriah89");
        // data.add("kdaP4797Ldq0OHK");
        // result = DecodeOperation.decodeLogicOperation(type, null, data);
        // for (Object object : result) {
        //     assertEquals("true", (String) object);
        // }
        
    }

    @Test
    public void LOGIN() {
        List<String> data = new ArrayList<>();
        data.add("Loraine75");
        data.add("autiTYkKRO1S2g2");

        DecodeOperation.decodeLogicOperation(OperationType.LOGIN, null, data);
    }
    
}
