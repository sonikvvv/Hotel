package base_classes;

//import static org.junit.Assert.assertTrue;

import org.junit.Test;

import base_classes.classes.User;
import base_classes.classes.UserRoles;
//import base_classes.classes.emuns.UE;

public class DBConnectionTest {
    
    @Test
    public void getUserTest() {
        DBConnection db = new DBConnection();
        User u = new User("ves", "test", new UserRoles("admin"));
        db.saveObject(u);
        //User ur =  db.getUser(UE.NAME, null);
        //db.saveObject(ur);
        //assertTrue(ur.toString() ,(ur.getUser_id() == 1)? true: false);

        //// assertNotNull("Object is not created", ur);

        //AdditServices as = new AdditServices("loby bar", 6);
        //db.saveObject(as);
        //ClientUsedServices cus = new ClientUsedServices(as, 1, "note");
        //db.saveObject(cus);
    }
}
