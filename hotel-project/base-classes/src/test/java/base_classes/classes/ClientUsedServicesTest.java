package base_classes.classes;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import base_classes.classes.emuns.ServiceType;

public class ClientUsedServicesTest {
    private ClientUsedServices cus = null;
    private String title;
    private Double price;
    private String category_title;
    private ServiceType type;
    private List<String> test = new ArrayList<>();
    private ServiceCategory sc = null;
    private AdditServices ad = null;
    private int id;
    private int quantity;
    private String note;
    private String table_name;

    public ClientUsedServicesTest() {
        title = "test1";
        price = 5.2;
        category_title = "test";
        id = 1;
        type = ServiceType.PROSITIVE;
        table_name = "c_used_serv";
        test.add("cus_id");
        test.add("addit_service");
        test.add("quantity");
        test.add("purchase_date");
        sc = new ServiceCategory(category_title, type);
        ad = new AdditServices(title, sc, price);
        ad.setServ_id(id);

        quantity = 3;
        note = "note";

        cus = new ClientUsedServices(ad, quantity, note);
    }

    @Test
    public void getTableNameTest() {
        assertEquals(table_name, ClientUsedServices.getTableName());
    }

    @Test
    public void getFieldsTest() {
        assertEquals(test.get(0), ClientUsedServices.getFields().get(0));
        assertEquals(test.get(1), ClientUsedServices.getFields().get(1));
        assertEquals(test.get(2), ClientUsedServices.getFields().get(2));
        assertEquals(test.get(3), ClientUsedServices.getFields().get(3));
    }

    @Test
    public void serviceCategoryTest() {
        assertEquals(type, sc.getType());
        assertEquals(category_title, sc.getCategory_title());
    }

    @Test
    public void additionalServicesTest() {
        assertEquals(ad, cus.getAddit_service());
    }


     
}
