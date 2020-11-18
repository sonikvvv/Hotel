package base_classes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.query.Query;
import org.junit.Test;

import base_classes.classes.*;
import base_classes.classes.emuns.*;

@SuppressWarnings("rawtypes")
public class DBConnectionTest {
    private DBConnection db = null;
    private Hotel h1 = null;
    private Hotel h2 = null;
    private User u = null;
    private User u1 = null;
    private User u2 = null;
    private User u3 = null;
    private User u4 = null;
    private User u5 = null;
    private User u6 = null;
    private User u7 = null;
    private User u8 = null;
    private User u9 = null;
    private ServiceCategory sc = null;
    private ServiceCategory sc1 = null;
    private ServiceCategory sc2 = null;
    private ServiceCategory sc3 = null;
    private ServiceCategory sc4 = null;
    private ServiceCategory sc5 = null;
    private ServiceCategory sc6 = null;
    private ServiceCategory sc7 = null;
    private AdditServices as = null;
    private AdditServices as1 = null;
    private AdditServices as2 = null;
    private AdditServices as3 = null;
    private AdditServices as4 = null;
    private AdditServices as5 = null;
    private AdditServices as6 = null;
    private AdditServices as7 = null;
    private AdditServices as8 = null;
    private AdditServices as9 = null;
    private AdditServices as10 = null;
    private Clients c = null;
    private Clients c1 = null;
    private Clients c2 = null;
    private Clients c3 = null;
    private Clients c4 = null;
    private Clients c5 = null;
    private ClientUsedServices cus = null;
    private ClientUsedServices cus1 = null;
    private ClientUsedServices cus2 = null;
    private ClientUsedServices cus3 = null;
    private ClientUsedServices cus4 = null;
    private ClientUsedServices cus5 = null;
    private ClientUsedServices cus6 = null;
    private ClientUsedServices cus7 = null;
    private ClientUsedServices cus8  = null;
    private ClientUsedServices cus9  = null;
    private ClientUsedServices cus10  = null;
    private ClientUsedServices cus11  = null;
    private ClientUsedServices cus12 = null;
    private ClientUsedServices cus13 = null;
    private ClientUsedServices cus14 = null;
    private ClientUsedServices cus15 = null;
    private ReservationForm fr = null;
    private ReservationForm fr1 = null;
    private ReservationForm fr2 = null;
    private ReservationForm fr3 = null;
    private Room ro = null;
    private Room ro1 = null;
    private Room ro2 = null;
    private Room ro3 = null;
    private Room ro4 = null;
    private Room ro5 = null;
    private Room ro6 = null;
    private Room ro7 = null;
    private Room ro8 = null;
    private Room ro9 = null;
    private Room ro10 = null;
    private Room ro11 = null;
    private Room ro12 = null;
    private Room ro13 = null;
    private Room ro14 = null;
    private Room ro15 = null;
    private Room ro16 = null;
    private Room ro17 = null;
    private List<Room> rr = null;
    private List<Room> rr1 = null;
    private List<Room> rr2 = null;
    private List<Room> rr3 = null;
    private Reservation r = null;
    private Reservation r1 = null;
    private Reservation r2 = null;
    private Reservation r3 = null;
    
    public DBConnectionTest() {
        db = new DBConnection();

        h1 = new Hotel("Testivile");
        h2 = new Hotel("Mehvile");

        db.saveOrUpdateObject(h1);
        db.saveOrUpdateObject(h2);

        u = new User("Loraine75", "autiTYkKRO1S2g2", URE.OWNER);
        u1 = new User("Hans.Lakin83", "jwr_6HCmF6EdMjr", URE.OWNER);
        u2 = new User("Dejon_Casper34", "ECWnGq01UOj_5Mm", URE.OWNER);
        u3 = new User("Nadia.Goldner", "jMcKKo_JBPGdWPD", URE.MANAGER);
        u4 = new User("Colton.Bergnaum", "KcL_6uglshryOTG", URE.MANAGER);
        u5 = new User("Brayan_Torphy63", "svNLz129ZCT1yKk", URE.MANAGER);
        u6 = new User("Uriah89", "kdaP4797Ldq0OHK", URE.RECEPTIONIST);
        u7 = new User("Josie.Emmerich", "Vc6rNpBOB0Thxmr", URE.RECEPTIONIST);
        u8 = new User("Bernhard_Braun", "2NqBCSTWpt5vIYh", URE.RECEPTIONIST);
        u9 = new User("Brenna.Dietrich88", "YrjsuzduxyV81ya", URE.RECEPTIONIST);

        h1.addToUsers(u);
        h1.addToUsers(u1);
        h1.addToUsers(u3);
        h1.addToUsers(u6);
        h1.addToUsers(u7);

        h2.addToUsers(u);
        h2.addToUsers(u4);
        h2.addToUsers(u5);
        h2.addToUsers(u8);
        h2.addToUsers(u9);

        sc = new ServiceCategory("Clothing", ServiceType.PROSITIVE);
        sc1 = new ServiceCategory("Automotive", ServiceType.PROSITIVE);
        sc2 = new ServiceCategory("Music", ServiceType.PROSITIVE);
        sc3 = new ServiceCategory("Automotive", ServiceType.PROSITIVE);
        sc4 = new ServiceCategory("Shoes", ServiceType.NEGATIVE);
        sc5 = new ServiceCategory("Toys", ServiceType.NEGATIVE);
        sc6 = new ServiceCategory("Outdoors", ServiceType.NEGATIVE);
        sc7 = new ServiceCategory("Health", ServiceType.NEGATIVE);


        as = new AdditServices("Awesome Frozen Mouse", sc, 75.00);
        as1 = new AdditServices("Incredible Frozen Shirt", sc4, 892.00);
        as2 = new AdditServices("Intelligent Cotton Fish", sc4, 439.00);
        as3 = new AdditServices("Practical Wooden Tuna", sc3, 299.00);
        as4 = new AdditServices("Small Rubber Car", sc1, 991.00);
        as5 = new AdditServices("Licensed Metal Chicken", sc3, 689.00);
        as6 = new AdditServices("Sleek Fresh Fish", sc2, 296.00);
        as7 = new AdditServices("Tasty Wooden Shoes", sc5, 661.00);
        as8 = new AdditServices("Refined Concrete Chips", sc1, 127.00);
        as9 = new AdditServices("Sleek Granite Chicken", sc7, 770.00);
        as10 = new AdditServices("Rustic Granite Shoes", sc3, 535.00);

        c = new Clients("Amina Jakubowski", LocalDate.now(), false, "30506", LocalDate.of(2020, 11, 10),
                "car_number", new Country("Testivile"), "client_note", "vaucher", 1);
        c1 = new Clients("Annamarie Haag", LocalDate.now(), true, "67547", LocalDate.of(2020, 11, 15),
                "car_number", new Country("Falkland Islands (Malvinas)"), "client_note", "vaucher", 2);
        c2 = new Clients("Bud McClure II", LocalDate.now(), true, "48931", LocalDate.of(2020, 11, 16),
                "car_number", new Country("Anguilla"), "client_note", "vaucher", 1);

        c3 = new Clients("Marley Brakus", LocalDate.now(), true, "14411", LocalDate.of(2020, 11, 17),
                "car_number", new Country("Virgin Islands, U.S."), "client_note", "vaucher", 2);
        c4 = new Clients("Amie Moen", LocalDate.now(), true, "81962", LocalDate.of(2020, 11, 17), "car_number",
                new Country("Argentina"), "client_note", "vaucher", 3);
        c5 = new Clients("Wendell Volkman I", LocalDate.now(), true, "5330", LocalDate.of(2020, 11, 18),
                "car_number", new Country("Iraq"), "client_note", "vaucher", 1);


        cus = new ClientUsedServices(as1, 1, "note");
        cus1 = new ClientUsedServices(as9, 2, "note");
        cus2 = new ClientUsedServices(as1, 3, "note");
        cus3 = new ClientUsedServices(as6, 1, "note");


        c.addToUsedServices(cus);
        c.addToUsedServices(cus1);
        c.addToUsedServices(cus2);
        c.addToUsedServices(cus3);

        cus4 = new ClientUsedServices(as6, 2, "note");
        cus5 = new ClientUsedServices(as1, 3, "note");
        cus6 = new ClientUsedServices(as5, 3, "note");
        cus7 = new ClientUsedServices(as7, 3, "note");

        c1.addToUsedServices(cus4);
        c1.addToUsedServices(cus5);
        c1.addToUsedServices(cus6);
        c1.addToUsedServices(cus7);

        cus8 = new ClientUsedServices(as5, 3, "note");
        cus9 = new ClientUsedServices(as9, 3, "note");
        cus10 = new ClientUsedServices(as1, 3, "note");
        cus11 = new ClientUsedServices(as10, 3, "note");

        c2.addToUsedServices(cus8);
        c2.addToUsedServices(cus9);
        c2.addToUsedServices(cus10);
        c2.addToUsedServices(cus11);

        cus12 = new ClientUsedServices(as5, 3, "note");
        cus13 = new ClientUsedServices(as9, 3, "note");

        c3.addToUsedServices(cus12);
        c3.addToUsedServices(cus13);

        cus14 = new ClientUsedServices(as5, 3, "note");
        cus15 = new ClientUsedServices(as10, 3, "note");

        c4.addToUsedServices(cus14);
        c4.addToUsedServices(cus15);
        fr = new ReservationForm("test", "Double", "t", LocalDate.of(2020, 11, 8),
                LocalDate.of(2020, 11, 13), 2, 0, 0, "fb", 1000, "expired", "notes", "Marlon Kunde");
        fr1 = new ReservationForm("test", "Double", "t", LocalDate.of(2020, 11, 6),
                LocalDate.of(2020, 11, 10), 4, 2, 0, "fb", 957, "expired", "notes", "Paula Funk");
        fr2 = new ReservationForm("test", "single", "t", LocalDate.of(2020, 11, 7),
                LocalDate.of(2020, 11, 17), 4, 4, 0, "fb", 453, "expired", "notes", "Ryleigh Hilpert");
        fr3 = new ReservationForm("test", "single", "t", LocalDate.of(2020, 12, 12),
                LocalDate.of(2020, 11, 17), 2, 2, 0, "fb", 732, "expired", "notes", "Ruben Rice");

        ro = new Room("20", "double", 447.21, SE.FREE);
        ro1 = new Room("79", "single", 274.22, SE.OCCUPIED);
        ro2 = new Room("42", "single", 371.19, SE.FREE);

        ro3 = new Room("73", "double", 454.60, SE.OCCUPIED);
        ro4 = new Room("65", "double", 250.79, SE.OCCUPIED);
        ro5 = new Room("79", "double", 451.43, SE.RESERVED);

        ro6 = new Room("61", "double", 440.07, SE.DIRTY);
        ro7 = new Room("64", "single", 431.91, SE.DIRTY);
        ro8 = new Room("26", "double", 216.81, SE.DIRTY);

        ro9 = new Room("17", "double", 290.59, SE.OUT_OF_ORDER);
        ro10 = new Room("35", "single", 472.41, SE.RESERVED);
        ro11 = new Room("40", "double", 317.77, SE.RESERVED);
        ro12 = new Room("89", "single", 245.89, SE.FREE);

        ro13 = new Room("89", "double", 242.80, SE.FREE);
        ro14 = new Room("89", "single", 216.34, SE.OUT_OF_ORDER);
        ro15 = new Room("89", "double", 442.24, SE.RESERVED);

        ro16 = new Room("89", "double", 280.67, SE.OUT_OF_ORDER);
        ro17 = new Room("89", "single", 367.78, SE.FREE);

        ro.addToClients(c);
        ro6.addToClients(c1);
        ro12.addToClients(c2);
        ro9.addToClients(c3);
        ro4.addToClients(c4);
        ro13.addToClients(c5);

        rr = new ArrayList<>();
        rr.add(ro1);

        rr1 = new ArrayList<>();
        rr1.add(ro17);
        rr1.add(ro2);

        rr2 = new ArrayList<>();
        rr2.add(ro10);

        rr3 = new ArrayList<>();
        rr3.add(ro8);
        rr3.add(ro8);
        rr3.add(ro4);

        r = new Reservation(u, fr, rr);
        r1 = new Reservation(u2, fr3, rr2);
        r2 = new Reservation(u8, fr2, rr1);
        r3 = new Reservation(u8, fr1, rr3);

        h1.addToRooms(ro);
        h1.addToRooms(ro1);
        h1.addToRooms(ro2);

        h1.addToRooms(ro3);
        h1.addToRooms(ro4);
        h1.addToRooms(ro5);

        h1.addToRooms(ro6);
        h1.addToRooms(ro7);
        h1.addToRooms(ro8);

        h2.addToRooms(ro9);
        h2.addToRooms(ro10);
        h2.addToRooms(ro11);
        h2.addToRooms(ro12);
        h2.addToRooms(ro13);
        h2.addToRooms(ro14);
        h2.addToRooms(ro15);
        h2.addToRooms(ro16);
        h2.addToRooms(ro17);

        h1.addToReservations(r);
        h2.addToReservations(r1);
        h2.addToReservations(r2);
        h1.addToReservations(r3);


        db.saveOrUpdateObject(sc);
        db.saveOrUpdateObject(sc1);
        db.saveOrUpdateObject(sc2);
        db.saveOrUpdateObject(sc3);
        db.saveOrUpdateObject(sc4);
        db.saveOrUpdateObject(sc5);
        db.saveOrUpdateObject(sc6);
        db.saveOrUpdateObject(sc7);

        db.saveOrUpdateObject(as);
        db.saveOrUpdateObject(as1);
        db.saveOrUpdateObject(as2);
        db.saveOrUpdateObject(as3);
        db.saveOrUpdateObject(as4);
        db.saveOrUpdateObject(as5);
        db.saveOrUpdateObject(as6);
        db.saveOrUpdateObject(as7);
        db.saveOrUpdateObject(as8);
        db.saveOrUpdateObject(as9);
        db.saveOrUpdateObject(as10);

        db.saveOrUpdateObject(c);
        db.saveOrUpdateObject(c1);
        db.saveOrUpdateObject(c2);
        db.saveOrUpdateObject(c3);
        db.saveOrUpdateObject(c4);
        db.saveOrUpdateObject(c5);

        db.saveOrUpdateObject(c);
        db.saveOrUpdateObject(c1);
        db.saveOrUpdateObject(c2);
        db.saveOrUpdateObject(c3);
        db.saveOrUpdateObject(c4);
        db.saveOrUpdateObject(c5);

        db.saveOrUpdateObject(h1);
        db.saveOrUpdateObject(h2);
    }

    @Test
    public void batch() {
            db.saveOrUpdateObject(h1);
            db.saveOrUpdateObject(h2);

            // db.saveOrUpdateObject(u);
            // db.saveOrUpdateObject(u1);
            // db.saveOrUpdateObject(u2);
            // db.saveOrUpdateObject(u3);
            // db.saveOrUpdateObject(u4);
            // db.saveOrUpdateObject(u5);
            // db.saveOrUpdateObject(u6);
            // db.saveOrUpdateObject(u7);
            // db.saveOrUpdateObject(u8);
            // db.saveOrUpdateObject(u9);

            db.saveOrUpdateObject(h1);
            db.saveOrUpdateObject(h2);
    
            db.saveOrUpdateObject(sc);
            db.saveOrUpdateObject(sc1);
            db.saveOrUpdateObject(sc2);
            db.saveOrUpdateObject(sc3);
            db.saveOrUpdateObject(sc4);
            db.saveOrUpdateObject(sc5);
            db.saveOrUpdateObject(sc6);
            db.saveOrUpdateObject(sc7);
 
            db.saveOrUpdateObject(as);
            db.saveOrUpdateObject(as1);
            db.saveOrUpdateObject(as2);
            db.saveOrUpdateObject(as3);
            db.saveOrUpdateObject(as4);
            db.saveOrUpdateObject(as5);
            db.saveOrUpdateObject(as6);
            db.saveOrUpdateObject(as7);
            db.saveOrUpdateObject(as8);
            db.saveOrUpdateObject(as9);
            db.saveOrUpdateObject(as10);

            db.saveOrUpdateObject(c);
            db.saveOrUpdateObject(c1);
            db.saveOrUpdateObject(c2);
            db.saveOrUpdateObject(c3);
            db.saveOrUpdateObject(c4);
            db.saveOrUpdateObject(c5);

            db.saveOrUpdateObject(c);
            db.saveOrUpdateObject(c1);
            db.saveOrUpdateObject(c2);
            db.saveOrUpdateObject(c3);
            db.saveOrUpdateObject(c4);
            db.saveOrUpdateObject(c5);

            db.saveOrUpdateObject(h1);
            db.saveOrUpdateObject(h2);
    }

    @Test
    public void getUserTest() {
        User u = new User("ves", "test", URE.ADMIN);
        db.saveOrUpdateObject(u);
        List<User> res = null;

        res = db.getUserList(UE.ID, u.getUser_id() + "");
        assertEquals(u.getUser_id(), res.get(0).getUser_id());
        res = db.getUserList(UE.ROLE, "ADMIN");
        assertEquals("test for enum", u, res.get(0));

        res = db.getUserList(UE.NAME, "ves");
        assertEquals("test for enum", u.getUser_name(), res.get(0).getUser_name());

        

        User u2 = new User("ko", "meh", URE.ADMIN);
        db.saveOrUpdateObject(u2);
        res = db.getUserList(UE.ROLE, "ADMIN");
        assertTrue("only one object", (res.size() != 1) ? true : false);
    }

    @Test
    public void clientUsedServices() {
        ServiceCategory sc = new ServiceCategory("saif", ServiceType.PROSITIVE);
        AdditServices adss = new AdditServices("saife 14D", sc, 10);
        AdditServices ad = new AdditServices("saife 15D", sc, 12);
        db.saveOrUpdateObject(adss);
        db.saveOrUpdateObject(ad);
        ClientUsedServices cus = new ClientUsedServices(adss, 1, "note");
        ClientUsedServices cus1 = new ClientUsedServices(adss, 2, "note");
        ClientUsedServices cus2 = new ClientUsedServices(adss, 3, "note");
        ClientUsedServices cus3 = new ClientUsedServices(ad, 1, "note");
        ClientUsedServices cus4 = new ClientUsedServices(ad, 2, "note");
        ClientUsedServices cus5 = new ClientUsedServices(ad, 3, "note");

        db.saveOrUpdateObject(cus);
        db.saveOrUpdateObject(cus1);
        db.saveOrUpdateObject(cus2);
        db.saveOrUpdateObject(cus3);
        db.saveOrUpdateObject(cus4);
        db.saveOrUpdateObject(cus5);

        // db.getSession().getTransaction().commit();

        ClientUsedServices cus6 = new ClientUsedServices(ad, 4, "note");
        // db.getSession().beginTransaction();
        db.saveOrUpdateObject(cus6);
        //db.getSession().getTransaction().commit();
    }
    

    @Test
    public void getClientByDateAndHotelTest() {
       
        // db.saveOrUpdateObject(c);
        // db.saveOrUpdateObject(c5);
        // db.saveOrUpdateObject(c4);
        // db.saveOrUpdateObject(c3);
        // db.saveOrUpdateObject(c2);
        // db.saveOrUpdateObject(c1);

        LocalDate ld = LocalDate.of(2020, 11, 16);
        Query<Clients> q = db.getSession().createQuery("from clients t where t.hotel_id = :id and t.check_in between :start and :end", Clients.class);
        q.setParameter("id", 1);
        q.setParameter("start", LocalDateTime.of(2020, 11, 15, 0, 0, 0));
        q.setParameter("end", LocalDateTime.of(2020, 11, 18, 0, 0, 0));

        List<Clients> t = q.list();

        List<?> res = db.getSession().createQuery("from clients t where t.check_in between to_date('14.11.2020', 'DD.MM.YYYY') and to_date('17.11.2020', 'DD.MM.YYYY')").list();
        for (Object object : res) {
            Clients tmp = (Clients) object;
            if (tmp.getHotel_id() != 2) res.remove(tmp);
        }

        System.out.println(res);
    }

    @Test
    public void getHotelTest() {
        List<Hotel> h = db.getHotelInfoList(HE.ID, 1 + "");
        assertEquals(h1.getHotel_name(), h.get(0).getHotel_name());
    }

    @Test
    public void getClientsByDateTest() {
        List<String> t = new ArrayList<>();
        t.add("2020-11-16");
        t.add("2020-11-17");
        t.add("1");
        List<Clients> res = db.getClientByDateList(t); // 1 3 6
        assertEquals(c.getC_name(), res.get(0).getC_name());
        assertEquals(c2.getC_name(), res.get(1).getC_name());
        assertEquals(c5.getC_name(), res.get(2).getC_name());
        //System.out.println(res);
    }

    @Test
    public void getServicesByDateTest() {
        List<String> t = new ArrayList<>();
        t.add("2020-11-16");
        t.add("2020-11-17");
        t.add("1");
        List<ClientUsedServices> res = db.getClientServicesByDate(t); // 1 2 3 4 9 10 11 12
        assertEquals(cus.getAddit_service().getTitle(), res.get(0).getAddit_service().getTitle());
        assertEquals(cus1.getAddit_service().getTitle(), res.get(1).getAddit_service().getTitle());
        assertEquals(cus2.getAddit_service().getTitle(), res.get(2).getAddit_service().getTitle());
        assertEquals(cus3.getAddit_service().getTitle(), res.get(3).getAddit_service().getTitle());
        assertEquals(cus8.getAddit_service().getTitle(), res.get(4).getAddit_service().getTitle());
        assertEquals(cus9.getAddit_service().getTitle(), res.get(5).getAddit_service().getTitle());
        assertEquals(cus10.getAddit_service().getTitle(), res.get(6).getAddit_service().getTitle());
        assertEquals(cus11.getAddit_service().getTitle(), res.get(7).getAddit_service().getTitle());
    }

    @Test
    public void getReservationsByDateTest() {
        List<String> t = new ArrayList<>();
        t.add("2020-11-15");
        t.add("2020-11-16");
        t.add("1");

        List<Reservation> res = db.getReservationsByDate(t);
        assertEquals(r3.getReservation_form().getClient_name(), res.get(0).getReservation_form().getClient_name());
    }

    @Test
    public void getReservationsByUserAndDateTest() {
        List<String> t = new ArrayList<>();
        t.add("2020-11-15");
        t.add("2020-11-16");
        t.add("8");

        List<Reservation> res = db.getReceptionistMadeReservations(t); // 2 r1
        assertEquals(r1.getReceptionist().getName(),
        res.get(0).getReceptionist().getName());
    }


}
