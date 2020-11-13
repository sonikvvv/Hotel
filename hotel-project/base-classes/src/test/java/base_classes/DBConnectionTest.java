package base_classes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.Test;

import base_classes.classes.*;
import base_classes.classes.emuns.*;

@SuppressWarnings("rawtypes")
public class DBConnectionTest {
    private DBConnection db = null;
    
    public DBConnectionTest() {
        db = new DBConnection();
    }

    @Test
    public void getUserTest() {
        User u = new User("ves", "test", URE.ADMIN);
        db.saveObject(u);

        List<User> res = db.getUserList(UE.ROLE, "ADMIN");
        assertEquals("test for enum", u, res.get(0));

        res = db.getUserList(UE.NAME, "ves");
        assertEquals("test for enum", u.getUser_name(), res.get(0).getUser_name());

        res = db.getUserList(UE.ID, u.getUser_id() + "");
        assertEquals(u.getUser_id(), res.get(0).getUser_id());

        User u2 = new User("ko", "meh", URE.ADMIN);
        db.saveObject(u2);
        res = db.getUserList(UE.ROLE, "ADMIN");
        assertTrue("only one object", (res.size() != 1)? true:false);
    }

    @Test
    public void getRoomTest() {
        // Room r = new Room("103", 5, "double", "free");
        // db.saveObject(r);

        // List<Room> rr = db.getRoomList(RoomE.ROOM_TYPE, "double");
        // assertEquals(r, rr.get(0));

        // rr = db.getRoomList(RoomE.NUMBER, r.getRoom_number());
        // assertEquals("not the same numbers", r.getRoom_number(), rr.get(0).getRoom_number());

        // Room r2 = new Room("103", 6, "single", "free");
        // db.saveObject(r2);

        // rr = db.getRoomList(RoomE.NUMBER, "103");
        // assertTrue(rr.size()!= 1? true:false);
    }


    @Test
    public void getClientFromDateTest() {
        // Clients c1 = new Clients("ves", new java.sql.Date(1999, 04, 18), false, "1516171819", new Date(2013, 5, 10), "", 5, new Country("Bulgaria"), "",
        //         "");
        // db.saveObject(c1);

        // List re = db.getSession().createQuery("from clients au where au.check_in like to_date('11.11.2020', 'DD.MM.YYYY')")
        //     .list();

        // //db.get("from clients c where c.check_in = to_date('11.11.2020', 'DD.MM.YYYY')");

        // assertNotNull("no res", re.isEmpty());
    }
}
