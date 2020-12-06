package base_classes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import base_classes.classes.AdditServices;
import base_classes.classes.ClientUsedServices;
import base_classes.classes.Clients;
import base_classes.classes.Country;
import base_classes.classes.Hotel;
import base_classes.classes.Reservation;
import base_classes.classes.ReservationForm;
import base_classes.classes.Room;
import base_classes.classes.ServiceCategory;
import base_classes.classes.User;
import base_classes.classes.emuns.SE;
import base_classes.classes.emuns.ServiceType;
import base_classes.classes.emuns.URE;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DBConnectionTest {
    private DBConnection db = new DBConnection();
    private Country country = new Country("Testiviles"); 
    private Hotel h1 = new Hotel("Testivile"); 
    private ReservationForm rf = new ReservationForm("reservation_type", "room_type", "cancel_type",
            LocalDate.parse("2020-10-13"), LocalDate.parse("2020-10-23"), 4, 0, 1, "food_type", 1050, "status", "notes",
            "client_name");
    private Room room = new Room("103", "Double", 1000, SE.FREE, h1); 
    private ServiceCategory sc = new ServiceCategory("Safe", ServiceType.PROSITIVE); 
    private User user = new User("Anastacio48", "6gGJ7LYSbNbMkom", "Mariah O'Kon", "940.249.9052 x164", URE.ADMIN); 
    private AdditServices ads = new AdditServices("Safe 14d", sc, 15, h1);
    private Clients client = new Clients("Susanna O'Connell", LocalDate.parse("2020-11-15"), false, "47195",
            LocalDate.parse("2020-05-13"), "67419", country, "client_note", "Multi-channelled", h1); 
    private ClientUsedServices cus = new ClientUsedServices(ads, 3, "note", h1);
    private Reservation res = new Reservation(user, rf, room, h1);

    public DBConnectionTest() {
        List<Hotel> hotels = new ArrayList<>();
        hotels.add(0, h1);
        user.setHotel(hotels);
    }

    @Test
    public void Test_1() { //save 
        db.saveObject(h1);
        db.saveObject(user);
        db.saveObject(room);
        db.saveObject(sc);
        db.saveObject(ads);
        db.saveObject(cus);
        db.saveObject(country);
        db.saveObject(client);
        db.saveObject(res);

        assertTrue(h1.getHotel_id() > 0);
        assertTrue(user.getUser_id() > 0);
        assertTrue(room.getR_id() > 0);
        assertTrue(sc.getCategory_id() > 0);
        assertTrue(ads.getServ_id() > 0);
        assertTrue(cus.getCus_id() > 0);
        assertTrue(country.getCountry_id() > 0);
        assertTrue(client.getC_id() > 0);
        assertTrue(res.getReservation_id() > 0);
    }

    @Test
    public void Test_11() { //get by id
        Hotel get_h1 = db.getHotelById(1);
        User get_user = db.getUserByID(1);
        Room get_room = db.getRoomByID(1);
        ServiceCategory get_sc = db.getServiceCategoryByID(1);
        AdditServices get_ads = db.getAdditServicesByID(1);
        ClientUsedServices get_cus = db.getClientUsedServicesByID(1);
        Country get_country = db.getCountryByID(1);
        Clients get_client = db.getClientByID(1);
        Reservation get_res = db.getReservationByID(1);

        assertEquals(h1.getHotel_name(), get_h1.getHotel_name());
        assertEquals(user.getName(), get_user.getName());
        assertEquals(room.getR_number(), get_room.getR_number());
        assertEquals(sc.getCategory_title(), get_sc.getCategory_title());
        assertEquals(ads.getTitle(), get_ads.getTitle());
        assertEquals(cus.getNote(), get_cus.getNote());
        assertEquals(country.getCountry_name(), get_country.getCountry_name());
        assertEquals(client.getC_name(), get_client.getC_name());
        assertEquals(res.getDate_made(), get_res.getDate_made());
    }

    @Test
    public void Test_12() { //get user by name
        User result = db.getUserByUsername(user.getUser_name());

        assertNotNull(result);
        assertEquals(user.getUser_name(), result.getUser_name());

        result = db.getUserByUsername("TEFVD");
        assertNull(result);
    }

    @Test
    public void Test_13() { //get all users
        List<User> result = db.getAllUsers();

        assertNotNull(result);
        assertTrue(result.size() > 0);
        assertTrue(result.get(0) instanceof User);
    }

    @Test
    public void Test_14() { //get all users by role
        List<User> result = db.getUserByRole(user.getUser_role());

        assertNotNull(result);
        assertTrue(result.size() > 0);
        assertTrue(result.get(0) instanceof User);

        result = db.getUserByRole(URE.RECEPTIONIST);
        assertTrue(result.size() == 0);

    }

    @Test
    public void Test_15() { //get all users by hotel
        List<User> result = db.getUserByHotel(1);

        assertNotNull(result);
        assertTrue(result.size() > 0);
        assertTrue(result.get(0) instanceof User);

        result = db.getUserByHotel(5);
        assertTrue(result.size() == 0);
    }

    @Test
    public void Test_16() { //get adsitional services by hotel
        List<AdditServices> result = db.getAdditServicesByHotel(1);

        assertNotNull(result);
        assertTrue(result.size() > 0);
        assertTrue(result.get(0) instanceof AdditServices);

        result = db.getAdditServicesByHotel(5);
        assertTrue(result.size() == 0);
    }

    @Test
    public void Test_17() { // get all additional services
        List<AdditServices> result = db.getAllAdditServices();

        assertNotNull(result);
        assertTrue(result.size() > 0);
        assertTrue(result.get(0) instanceof AdditServices);
    }

    @Test
    public void Test_18() { // get all service categories
        List<ServiceCategory> result = db.getAllServiceCategories();

        assertNotNull(result);
        assertTrue(result.size() > 0);
        assertTrue(result.get(0) instanceof ServiceCategory);
    }

    @Test
    public void Test_19() { // get hotel by name
        Hotel result = db.getHotelByName(h1.getHotel_name());

        assertNotNull(result);
        assertEquals(h1.getHotel_name(), result.getHotel_name());

        result = db.getHotelByName("fwrg");
        assertNull(result);
    }

    @Test
    public void Test_21() { // get all hotels
        List<Hotel> result = db.getAllHotels();

        assertNotNull(result);
        assertTrue(result.size() > 0);
        assertTrue(result.get(0) instanceof Hotel);
    }

    @Test
    public void Test_22() { //get all reservations by hotel
        List<Reservation> result = db.getAllReservationsByHotel(1);

        assertNotNull(result);
        assertTrue(result.size() > 0);
        assertTrue(result.get(0) instanceof Reservation);

        result = db.getAllReservationsByHotel(5);
        assertTrue(result.size() == 0);
    }

    @Test
    public void Test_23() { //get all rooms by hotel
        List<Room> result = db.getRoomsByHotel(1);

        assertNotNull(result);
        assertTrue(result.size() > 0);
        assertTrue(result.get(0) instanceof Room);

        result = db.getRoomsByHotel(5);
        assertTrue(result.size() == 0);
    }

    @Test
    public void Test_24() { // get all rooms
        List<Room> result = db.getAllRooms();

        assertNotNull(result);
        assertTrue(result.size() > 0);
        assertTrue(result.get(0) instanceof Room);
    }

    @Test
    public void Test_25() { // get clients by hotel
        List<Clients> result = db.getClientsByHotel(1);

        assertNotNull(result);
        assertTrue(result.size() > 0);
        assertTrue(result.get(0) instanceof Clients);

        result = db.getClientsByHotel(5);
        assertTrue(result.size() == 0);
    }

    @Test
    public void Test_26() { // get all clients
        List<Clients> result = db.getAllClients();

        assertNotNull(result);
        assertTrue(result.size() > 0);
        assertTrue(result.get(0) instanceof Clients);
    }

    @Test
    public void Test_27() { //get all reservations
        List<Reservation> result = db.getAllReservations();

        assertNotNull(result);
        assertTrue(result.size() > 0);
        assertTrue(result.get(0) instanceof Reservation);
    }

    @Test
    public void Test_28() { // get distinct room types
        List<String> result = db.getDistinctRoomTypes();

        assertNotNull(result);
        assertTrue(result.size() > 0);
    }

    @Test
    public void Test_29() { // get distinct additional services
        List<String> result = db.getDistinctAdditionalServices();

        assertNotNull(result);
        assertTrue(result.size() > 0);
    }

    @Test
    public void Test_3() { // update
        h1 = db.getHotelById(1);
        user = db.getUserByID(1);
        room = db.getRoomByID(1);
        sc = db.getServiceCategoryByID(1);
        ads = db.getAdditServicesByID(1);
        cus = db.getClientUsedServicesByID(1);
        country = db.getCountryByID(1);
        client = db.getClientByID(1);
        res = db.getReservationByID(1);

        h1.setHotel_name("Test");
        user.setName("Test");
        room.setR_number("Test");
        sc.setCategory_title("Test");
        ads.setTitle("Test");
        cus.setNote("Test");
        country.setCountry_name("Test");
        client.setC_name("Test");
        res.setDate_made(LocalDate.parse("2020-10-10"));

        db.updateObject(h1);
        db.updateObject(user);
        db.updateObject(room);
        db.updateObject(sc);
        db.updateObject(ads);
        db.updateObject(cus);
        db.updateObject(country);
        db.updateObject(client);
        db.updateObject(res);
        
        Hotel updated_h1 = db.getHotelById(1);
        User updated_user = db.getUserByID(1);
        Room updated_room = db.getRoomByID(1);
        ServiceCategory updated_sc = db.getServiceCategoryByID(1);
        AdditServices updated_ads = db.getAdditServicesByID(1);
        ClientUsedServices updated_cus = db.getClientUsedServicesByID(1);
        Country updated_country = db.getCountryByID(1);
        Clients updated_client = db.getClientByID(1);
        Reservation updated_res = db.getReservationByID(1);

        assertEquals(h1.getHotel_name(), updated_h1.getHotel_name());
        assertEquals(user.getName(), updated_user.getName());
        assertEquals(room.getR_number(), updated_room.getR_number());
        assertEquals(sc.getCategory_title(), updated_sc.getCategory_title());
        assertEquals(ads.getTitle(), updated_ads.getTitle());
        assertEquals(cus.getNote(), updated_cus.getNote());
        assertEquals(country.getCountry_name(), updated_country.getCountry_name());
        assertEquals(client.getC_name(), updated_client.getC_name());
        assertEquals(res.getDate_made(), updated_res.getDate_made());
    }

    @Test
    public void Test_31() { //delete 
        db.deleteObject(h1);
        db.deleteObject(user);
        db.deleteObject(room);
        db.deleteObject(sc);
        db.deleteObject(ads);
        db.deleteObject(cus);
        db.deleteObject(country);
        db.deleteObject(client);
        db.deleteObject(res);

        Hotel deleted_h1 = db.getHotelById(h1.getHotel_id());
        User deleted_user = db.getUserByID(user.getUser_id());
        Room deleted_room = db.getRoomByID(room.getR_id());
        ServiceCategory deleted_sc = db.getServiceCategoryByID(sc.getCategory_id());
        AdditServices deleted_ads = db.getAdditServicesByID(ads.getServ_id());
        ClientUsedServices deleted_cus = db.getClientUsedServicesByID(cus.getCus_id());
        Country deleted_country = db.getCountryByID(country.getCountry_id());
        Clients deleted_client = db.getClientByID(client.getC_id());
        Reservation deleted_res = db.getReservationByID(res.getReservation_id());

        assertNull(deleted_h1);
        assertNull(deleted_user);
        assertNull(deleted_room);
        assertNull(deleted_sc);
        assertNull(deleted_ads);
        assertNull(deleted_cus);
        assertNull(deleted_country);
        assertNull(deleted_client);
        assertNull(deleted_res);
    }
}