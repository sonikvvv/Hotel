package logic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import base_classes.DBConnection;
import base_classes.classes.*;
import base_classes.classes.emuns.*;
import logic.operations.RoomBusyness;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DecodeOperationTest {
    private static Country country = new Country("Testiviles");
    private static Hotel h1 = new Hotel("Testivile");
    private static ReservationForm rf = new ReservationForm("reservation_type", "room_type", "cancel_type",
            LocalDate.parse("2020-10-13"), LocalDate.parse("2020-10-23"), 4, 0, 1, "food_type", 1050, "status", "notes",
            "client_name");
    private static Room room = new Room("103", "Double", 1000, SE.FREE, h1);
    private static ServiceCategory sc = new ServiceCategory("Safe", ServiceType.PROSITIVE);
    private static User user = new User("Anastacio48", "6gGJ7LYSbNbMkom", "Mariah O'Kon", "940.249.9052 x164", URE.ADMIN);
    private static AdditServices ads = new AdditServices("Safe 14d", sc, 15, h1);
    private static Clients client = new Clients("Susanna O'Connell", LocalDate.parse("2020-11-15"), false, "47195",
            LocalDate.parse("2020-05-13"), "67419", country, "client_note", "Multi-channelled", h1);
    private static ClientUsedServices cus = new ClientUsedServices(ads, 3, "note", h1);
    private static Reservation res = null;

    private static LocalDate min = LocalDate.parse("2020-10-05");
    private static LocalDate max = LocalDate.parse("2020-12-10");
    private static LocalDate ref_min = null;
    private static LocalDate ref_max = null;


    @Test
    public void Test_1() { // save
        cus.setPurchase_date(LocalDateTime.of(2020, 11, 10, 0, 0, 0));
        client.setCheck_in(LocalDateTime.of(2020, 11, 10, 0, 0, 0));
        for (Raiting raiting : client.getRait()) {
            raiting.setDate_made(LocalDateTime.of(2020, 11, 10, 0, 0, 0));
        }
        DecodeOperation.decodeLogicOperation(OperationType.SAVE, h1, null);
        DecodeOperation.decodeLogicOperation(OperationType.SAVE, user, null);
        DecodeOperation.decodeLogicOperation(OperationType.SAVE, room, null);
        DecodeOperation.decodeLogicOperation(OperationType.SAVE, sc, null);
        DecodeOperation.decodeLogicOperation(OperationType.SAVE, ads, null);
        DecodeOperation.decodeLogicOperation(OperationType.SAVE, cus, null);
        DecodeOperation.decodeLogicOperation(OperationType.SAVE, country, null);
        
        room.addToClients(client);
        DecodeOperation.decodeLogicOperation(OperationType.SAVE, client, null);
        res = new Reservation(user, rf, room, h1);
        res.setDate_made(LocalDate.of(2020, 11, 10));
        DecodeOperation.decodeLogicOperation(OperationType.SAVE, res, null);

        ref_min = LocalDate.now();
        ref_min = ref_min.plusDays(5);
        ref_max = LocalDate.now();
        ref_max = ref_max.plusDays(20);

    }

    @AfterClass
    public static void clean_up() {
        DBConnection db = DecodeOperation.getDb();
        db.truncateAllTables();
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
        assertTrue(result.size() == 0);
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
    public void getClientsTest() {
        List<?> result = DecodeOperation.decodeLogicOperation(OperationType.GET_CLIENTS, null, null);
        assertNotNull(result);
        assertTrue(result.size() > 0);
        assertTrue(result.get(0) instanceof Clients);
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
        data.add(LocalDate.of(2020, 11, 10).toString());

        List<?> result = DecodeOperation.decodeLogicOperation(OperationType.GET_RESERVATIONS, null, data);
        assertNotNull(result);
        assertTrue(result.size() > 0);
        assertTrue(result.get(0) instanceof Reservation);


        data.clear();
        data.add(ref_min.toString());
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
        data.add(ref_min.toString());
        data.add(ref_max.toString());
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
        data.add(ref_min.toString());
        data.add(ref_max.toString());
        result = DecodeOperation.decodeLogicOperation(OperationType.CLIENT_RAITING, null, data);
        assertNotNull(result);
        assertTrue(result.size() == 0);
    }

    @Test
    public void getUsedServicesTest() {
        client.addToUsedServices(cus);
        DecodeOperation.decodeLogicOperation(OperationType.UPDATE, client, null);
        List<String> data = new ArrayList<>();
        data.add(min.toString());
        data.add(max.toString());

        List<?> result = DecodeOperation.decodeLogicOperation(OperationType.USED_SERVICES, null, data);
        assertNotNull(result);
        assertTrue(result.size() > 0);
        assertTrue(result.get(0) instanceof ClientUsedServices);

        data.clear();
        data.add(ref_min.toString());
        data.add(ref_max.toString());
        result = DecodeOperation.decodeLogicOperation(OperationType.USED_SERVICES, null, data);
        assertNotNull(result);
        assertTrue(result.size() == 0);

        client.detach();
        DecodeOperation.decodeLogicOperation(OperationType.UPDATE, client, null);
    }

    @Test
    public void getRoomRaitingTest() {
        List<String> data = new ArrayList<>();
        data.add(min.toString());
        data.add(max.toString());

        List<?> result = DecodeOperation.decodeLogicOperation(OperationType.ROOM_RAITING, null, data);
        assertNotNull(result);
        assertTrue(result.size() > 0);
        assertTrue(result.get(0) instanceof String);

        data.clear();
        data.add(ref_min.toString());
        data.add(ref_max.toString());
        result = DecodeOperation.decodeLogicOperation(OperationType.ROOM_RAITING, null, data);
        assertNotNull(result);
        assertTrue(result.size() != 0);
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
        data.add(ref_min.toString());
        data.add(ref_max.toString());
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
        data.add(ref_min.toString());
        data.add(ref_max.toString());
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
    public void getRecepcionistReference() {
        List<String> data = new ArrayList<>();
        data.add(min.toString());
        data.add(max.toString());

        List<?> result = DecodeOperation.decodeLogicOperation(OperationType.RECEPTIONIST_REFERENCE, null, data);
        assertNotNull(result);
        // for (Object object : result) {
        //     RoomBusyness tmp = (RoomBusyness) object;
        //     System.out.println(tmp);
        // }
        assertTrue(result.size() > 0);
        assertTrue(result.get(0) instanceof RoomBusyness);

        data.clear();
        data.add(ref_min.toString());
        data.add(ref_max.toString());
        result = DecodeOperation.decodeLogicOperation(OperationType.RECEPTIONIST_REFERENCE, null, data);
        assertNotNull(result);

        for (Object object : result) {
            RoomBusyness tmp = (RoomBusyness) object;
            assertTrue(tmp.getRoom_busynes()[0] == 0);
        }
    }
}
