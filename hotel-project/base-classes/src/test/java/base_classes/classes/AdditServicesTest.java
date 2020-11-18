package base_classes.classes;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import base_classes.classes.emuns.ADServicesE;
import base_classes.classes.emuns.ServiceType;


public class AdditServicesTest {
    private String title;
    private Double price;
    private String category_title;
    private String table_name;
    private ServiceType type;
    private List<String> test = new ArrayList<>();
    private ServiceCategory sc = null;
    private AdditServices ad = null;
    private int id;

    public AdditServicesTest() {
        title = "test1";
        price = 5.2;
        category_title = "test";
        id = 1;
        table_name = "add_serv";
        type = ServiceType.PROSITIVE;
        test.add("serv_id");
        test.add("title");
        test.add("price");
        test.add("category");
        test.add("hotel_id");
        test.add("rait");
        sc = new ServiceCategory(category_title, type);
        ad = new AdditServices(title, sc, price);
        ad.setServ_id(id);
    }

    @Test
    public void getTableNameTest() {
        assertEquals("get table name",table_name, 
        AdditServices.getTableName());
    }

    @Test
    public void getAddit_services_priceTest() {
        assertEquals("get serv price", price, (Double) ad.getPrice());
    }

    @Test
    public void getAddit_services_titleTest() {
        assertEquals("get serv title", title, ad.getTitle());
    }

    @Test
    public void getFieldsTest() {
        assertEquals(test.get(0), AdditServices.getFields().get(0));
        assertEquals(test.get(1), AdditServices.getFields().get(1));
        assertEquals(test.get(2), AdditServices.getFields().get(2));
        assertEquals(test.get(3), AdditServices.getFields().get(3));
        assertEquals(test.get(4), AdditServices.getFields().get(4));
    }

    @Test
    public void getID() {
        assertEquals("get id", id, ad.getServ_id());
    }

    @Test
    public void searchTest() {
        assertEquals(("from " + AdditServices.getTableName() + "t where t." + test.get(0)+ " = :value"),
            AdditServices.search(ADServicesE.ID));

        assertEquals(("from " + AdditServices.getTableName() + "t where t." + test.get(1)+ " = :value"), 
            AdditServices.search(ADServicesE.TITLE));

        assertEquals(("from " + AdditServices.getTableName() + "t where t." + test.get(2)+ " = :value"), 
            AdditServices.search(ADServicesE.PRICE));

        assertEquals(("from " + AdditServices.getTableName() + "t where t." + test.get(3)+ "."
            + ServiceCategory.getFields().get(0) + " = :value"), AdditServices.search(ADServicesE.CATEGORY_ID));

        assertEquals(("from " + AdditServices.getTableName() + "t where t." + test.get(3)+ "."
            + ServiceCategory.getFields().get(1) + " = :value"), AdditServices.search(ADServicesE.CATEGORY_NAME));

        assertEquals(("from " + AdditServices.getTableName() + "t where t." + test.get(4)+  " = :value"),
        AdditServices.search(ADServicesE.HOTEL_ID));
    }

    @Test
    public void serviceCategoryTest() {
        assertEquals(type, sc.getType());
        assertEquals(category_title, sc.getCategory_title());
    }

    @Test
    public void test() {
        String value = "2020-11-16";
        String[] t = value.split("-");
        //Integer.valueOf(tmp[2] + 1);

        System.out.println(Integer.valueOf(t[2]) + 1);
    }

}
