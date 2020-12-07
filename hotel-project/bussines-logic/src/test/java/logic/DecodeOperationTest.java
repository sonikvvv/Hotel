package logic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import base_classes.classes.*;
import base_classes.classes.emuns.*;
import logic.operations.UserOperations;

public class DecodeOperationTest {
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
    private Reservation res = null;

    private LocalDate min = LocalDate.parse("2020-10-05");
    private LocalDate max = LocalDate.parse("2020-12-10");

    @Before
    public void initialize() {
        client.addToUsedServices(cus);
        room.addToClients(client);
        DecodeOperation.decodeLogicOperation(OperationType.UPDATE, h1, null);
        DecodeOperation.decodeLogicOperation(OperationType.UPDATE, user, null);
        DecodeOperation.decodeLogicOperation(OperationType.UPDATE, room, null);
        DecodeOperation.decodeLogicOperation(OperationType.UPDATE, sc, null);
        DecodeOperation.decodeLogicOperation(OperationType.UPDATE, ads, null);
        DecodeOperation.decodeLogicOperation(OperationType.UPDATE, cus, null);
        DecodeOperation.decodeLogicOperation(OperationType.UPDATE, country, null);
        DecodeOperation.decodeLogicOperation(OperationType.UPDATE, client, null);
        res = new Reservation(user, rf, room, h1);
        DecodeOperation.decodeLogicOperation(OperationType.UPDATE, res, null);
    }

    @Test
    public void getADSTest() {
        List<?> result = DecodeOperation.decodeLogicOperation(OperationType.GET_ADS, null, null);
        assertNotNull(result);
        assertTrue(result.size() > 0);
        assertTrue(result.get(0) instanceof AdditServices);
    }

    @Test
    public void getUsersTest() {
        List<?> result = DecodeOperation.decodeLogicOperation(OperationType.GET_USERS, null, null);
        assertNotNull(result);
        assertTrue(result.size() > 0);
        assertTrue(result.get(0) instanceof User);
    }

    @Test
    public void getRoomsTest() {
        List<?> result = DecodeOperation.decodeLogicOperation(OperationType.GET_ROOMS, null, null);
        assertNotNull(result);
        assertTrue(result.size() > 0);
        assertTrue(result.get(0) instanceof Room);
    }

    @Test
    public void getReceptionistTest() {
        List<?> result = DecodeOperation.decodeLogicOperation(OperationType.GET_RECEPTIONIST, null, null);
        assertNotNull(result);
        assertTrue(result.size() != 0);
    }

    @Test
    public void getUserNowTest() {
        List<?> result = DecodeOperation.decodeLogicOperation(OperationType.GET_USER_NOW, null, null);
        assertNotNull(result);
        assertTrue(result.size() > 0 && result.size() < 2);
        assertTrue(result.get(0) instanceof User);
    }

    @Test
    public void getHotelTest() {
        List<?> result = DecodeOperation.decodeLogicOperation(OperationType.GET_HOTEL, null, null);
        assertNotNull(result);
        assertTrue(result.size() > 0);
        assertTrue(result.get(0) instanceof Hotel);
    }

    @Test
    public void getRoomTypes() {
        List<?> result = DecodeOperation.decodeLogicOperation(OperationType.ROOM_TYPES, null, null);
        assertNotNull(result);
        assertTrue(result.size() > 0);
        assertTrue(result.get(0) instanceof String);
        assertTrue(((String)result.get(0)).equals(room.getR_type()));
    }

    @Test
    public void loginTest() { // cant get user from db
        List<String> data = new ArrayList<>();
        data.add(user.getUser_name());
        data.add(user.getUser_password());

        List<?> result = DecodeOperation.decodeLogicOperation(OperationType.LOGIN, null, data);
        assertNotNull(result);
        assertTrue(result.size() > 0);
        assertTrue(result.get(0) instanceof String);
        assertEquals("true", (String)result.get(0));

        data.clear();
        data.add("ferghrtg");
        data.add("Fewrg");
        result = DecodeOperation.decodeLogicOperation(OperationType.LOGIN, null, data);
        assertNotNull(result);
        assertTrue(result.size() > 0);
        assertTrue(result.get(0) instanceof String);
        assertEquals("false", (String)result.get(0));

    }

    @Test
    public void getReservationsTest() {
        List<String> data = new ArrayList<>();
        data.add("2020-12-07");

        List<?> result = DecodeOperation.decodeLogicOperation(OperationType.GET_RESERVATIONS, null, data);
        assertNotNull(result);
        assertTrue(result.size() > 0);
        assertTrue(result.get(0) instanceof Reservation);


        data.clear();
        data.add("2020-12-08");
        result = DecodeOperation.decodeLogicOperation(OperationType.GET_RESERVATIONS, null, data);
        assertNotNull(result);
        assertTrue(result.size() == 0);
    }

    @Test
    public void getClientInfoTest() {
        List<String> data = new ArrayList<>();
        data.add(min.toString());
        data.add(max.toString());

        List<?> result = DecodeOperation.decodeLogicOperation(OperationType.CLIENT_INFO, null, data);
        assertNotNull(result);
        assertTrue(result.size() > 0);
        assertTrue(result.get(0) instanceof Clients);

        data.clear();
        data.add("2020-12-08");
        data.add("2020-12-20");
        result = DecodeOperation.decodeLogicOperation(OperationType.CLIENT_INFO, null, data);
        assertNotNull(result);
        assertTrue(result.size() == 0);
    }

    @Test
    public void getClientRaitingTest() {
        List<String> data = new ArrayList<>();
        data.add(min.toString());
        data.add(max.toString());

        List<?> result = DecodeOperation.decodeLogicOperation(OperationType.CLIENT_RAITING, null, data);
        assertNotNull(result);
        assertTrue(result.size() > 0);
        assertTrue(result.get(0) instanceof Clients);

        data.clear();
        data.add("2020-12-08");
        data.add("2020-12-20");
        result = DecodeOperation.decodeLogicOperation(OperationType.CLIENT_RAITING, null, data);
        assertNotNull(result);
        assertTrue(result.size() == 0);
    }

    @Test
    public void getUsedServicesTest() {
        List<String> data = new ArrayList<>();
        data.add(min.toString());
        data.add(max.toString());

        List<?> result = DecodeOperation.decodeLogicOperation(OperationType.USED_SERVICES, null, data);
        assertNotNull(result);
        assertTrue(result.size() > 0);
        assertTrue(result.get(0) instanceof ClientUsedServices);

        data.clear();
        data.add("2020-12-08");
        data.add("2020-12-20");
        result = DecodeOperation.decodeLogicOperation(OperationType.USED_SERVICES, null, data);
        assertNotNull(result);
        assertTrue(result.size() == 0);
    }

    @Test
    public void getRoomRaitingTest() {
        List<String> data = new ArrayList<>();
        data.add(min.toString());
        data.add(max.toString());

        List<?> result = DecodeOperation.decodeLogicOperation(OperationType.ROOM_RAITING, null, data);
        assertNotNull(result);
        assertTrue(result.size() > 0);
        assertTrue(result.get(0) instanceof Room);

        data.clear();
        data.add("2020-12-08");
        data.add("2020-12-20");
        result = DecodeOperation.decodeLogicOperation(OperationType.ROOM_RAITING, null, data);
        assertNotNull(result);
        assertTrue(result.size() == 0);
    }

    @Test
    public void getCreatedReservationsTest() {
        List<String> data = new ArrayList<>();
        data.add(min.toString());
        data.add(max.toString());

        List<?> result = DecodeOperation.decodeLogicOperation(OperationType.CREATED_RESERVATIONS, null, data);
        assertNotNull(result);
        assertTrue(result.size() > 0);
        assertTrue(result.get(0) instanceof Reservation);

        data.clear();
        data.add("2020-12-08");
        data.add("2020-12-20");
        result = DecodeOperation.decodeLogicOperation(OperationType.CREATED_RESERVATIONS, null, data);
        assertNotNull(result);
        assertTrue(result.size() == 0);
    }

    @Test
    public void getReceptCreatedReservationsTest() {
        List<String> data = new ArrayList<>();
        data.add(min.toString());
        data.add(max.toString());
        data.add(user.getUser_name());

        List<?> result = DecodeOperation.decodeLogicOperation(OperationType.RECEPT_CREATED_RESERVATIONS, null, data);
        assertNotNull(result);
        assertTrue(result.size() > 0);
        assertTrue(result.get(0) instanceof Reservation);

        data.clear();
        data.add("2020-12-08");
        data.add("2020-12-20");
        data.add(user.getUser_name());
        result = DecodeOperation.decodeLogicOperation(OperationType.RECEPT_CREATED_RESERVATIONS, null, data);
        assertNotNull(result);
        assertTrue(result.size() == 0);
    }

    @Test
    public void checkOutTest() {
        List<String> data = new ArrayList<>();
        data.add(room.getR_id() + "");
        data.add(room.getClients().get(0).getC_name());
        data.add("flag");

        DecodeOperation.decodeLogicOperation(OperationType.CHECKOUT, null, data);

        List<?> result = DecodeOperation.decodeLogicOperation(OperationType.GET_ROOMS, null, null);
        Room room = (Room) result.get(0);
        assertTrue(room.getClients().size() == 0); // 3 rooms
        assertEquals(SE.DIRTY, room.getR_status());
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

        //Admin
        DecodeOperation.decodeLogicOperation(OperationType.ADD_TO_USERS, null, data);

        List<?> result = DecodeOperation.decodeLogicOperation(OperationType.GET_USERS, null, null);
        for (Object object : result) {
            User tmp = (User) object;
            if (tmp.getUser_name() == user_name) {
                assertEquals(URE.OWNER, tmp.getUser_role());
            }
        }

        //Owner
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

        //Manager
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

        manager = new User("name", "password", URE.ADMIN);
        manager.addToHotel(h1);
        UserOperations.setUser_now(manager);
    }
    
}
