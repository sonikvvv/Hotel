package base_classes.classes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import base_classes.classes.emuns.ServiceType;

public class ClientsTest {
    private int c_id;
    private String c_name;
    private LocalDate c_bd;
    private boolean c_sex;
    private String c_passport_number;
    private LocalDate c_passport_date;
    private String c_car_number;
    private String c_note;
    private LocalDateTime check_in;
    private LocalDateTime check_out;
    private double total;
    private String vaucher;
    private Country country1 = null;
    private Clients client = null;
    private ClientUsedServices cus = null;
    private ClientUsedServices cus1 = null;
    private AdditServices ads = null;
    private AdditServices ads1 = null;
    private ServiceCategory sc = null;
    private ServiceCategory sc1 = null;

    public ClientsTest() {
        country1 = new Country("Testivile");
        c_id = 1;
        c_name = "Laney Dickens";
        c_bd = LocalDate.of(1999, 05, 20);
        c_sex = false;
        c_passport_number = "50127";
        c_passport_date = LocalDate.of(2019, 11, 15);
        c_car_number = "";
        c_note = "SLiyiLNLwVVlpsHsXF";
        check_in = LocalDateTime.of(2020, 3, 10, 0, 0, 0);
        check_out = LocalDateTime.of(2020, 3, 19, 0, 0, 0);
        //total = 1000.5;
        vaucher = "ivory";
        total = 0;
        client = new Clients(c_name, c_bd, c_sex, c_passport_number, c_passport_date, c_car_number, country1, c_note, vaucher);
        client.setC_id(c_id);
        client.setCheck_in(check_in);
        client.setCheck_out(check_out);

        sc = new ServiceCategory("room service", ServiceType.PROSITIVE);
        sc1 = new ServiceCategory("saif", ServiceType.NEGATIVE);

        ads = new AdditServices("breakfast", sc, 50);
        ads1 = new AdditServices("safe 14D", sc1, 15);

        cus = new ClientUsedServices(ads, 1, "note");
        cus1 = new ClientUsedServices(ads1, 1, "note");
    }

    @Test
    public void getSexTest() {
        assertEquals("F", client.getC_sex());
    }

    @Test
    public void totalTest() {
        assertTrue(total == client.getTotal()? true: false);
        assertTrue(client.getCuds().isEmpty());
        client.addToUsedServices(cus);
        total = total + cus.getQuantity() * ads.getPrice();
        assertFalse(client.getCuds().isEmpty());
        assertTrue(total == client.getTotal() ? true : false);
        client.addToUsedServices(cus1);
        total = total + cus1.getQuantity() * ads1.getPrice();
        assertTrue(client.getCuds().size() == 2? true:false);
        assertTrue(client.getTotal() == total? true:false);
    }

    @Test
    public void getTableNameTest() {
        assertEquals("clients", Clients.getTableName());
    }

    @Test
    public void getFieldsTest() {
        List<String> ls = new ArrayList<>();
        ls.add("c_id");
        ls.add("c_name");
        ls.add("c_bd");
        ls.add("c_sex");

        ls.add("c_passport_number");
        ls.add("c_passport_date");
        ls.add("c_car_number");
        ls.add("country");

        ls.add("check_in");
        ls.add("check_out");
        ls.add("vaucher");
        ls.add("hotel_id");

        ls.add("rait");
        
        assertEquals(ls, Clients.getFields());
        assertEquals(ls.get(0), Clients.getFields().get(0));
        assertEquals(ls.get(1), Clients.getFields().get(1));
        assertEquals(ls.get(2), Clients.getFields().get(2));
        assertEquals(ls.get(3), Clients.getFields().get(3));
        assertEquals(ls.get(4), Clients.getFields().get(4));
        assertEquals(ls.get(5), Clients.getFields().get(5));
        assertEquals(ls.get(6), Clients.getFields().get(6));
        assertEquals(ls.get(7), Clients.getFields().get(7));
        assertEquals(ls.get(8), Clients.getFields().get(8));
        assertEquals(ls.get(9), Clients.getFields().get(9));
        assertEquals(ls.get(10), Clients.getFields().get(10));
        assertEquals(ls.get(11), Clients.getFields().get(11));
        assertEquals(ls.get(12), Clients.getFields().get(12));
    }
    
}
