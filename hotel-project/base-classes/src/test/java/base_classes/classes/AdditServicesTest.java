package base_classes.classes;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import base_classes.classes.emuns.ADServicesE;


public class AdditServicesTest {
    private String title;
    private Double price;
    private List<String> test = new ArrayList<>();
    private AdditServices ad = null;
    private int id;

    public AdditServicesTest() {
        title = "test1";
        price = 5.2;
        id = 1;
        test.add("a_serv_id");
        test.add("a_serv_title");
        test.add("a_serv_price");
        ad = new AdditServices(title, price);
        ad.setAddit_services_id(id);
    }

    @Test
    public void getTableNameTest() {
        assertEquals("get table name","add_serv", AdditServices.getTableName());
    }

    @Test
    public void getAddit_services_priceTest() {
        assertEquals("get serv price", price, (Double) ad.getAddit_services_price());
    }

    @Test
    public void getAddit_services_titleTest() {
        assertEquals("get serv title", title, ad.getAddit_services_title());
    }

    @Test
    public void getFieldsTest() {
        assertEquals("get field 0", test.get(0), AdditServices.getFields().get(0));
        assertEquals("get field 1", test.get(1), AdditServices.getFields().get(1));
        assertEquals("get field 2", test.get(2), AdditServices.getFields().get(2));
    }

    @Test
    public void getID() {
        assertEquals("get id", id, ad.getAddit_services_id());
    }

    @Test
    public void searchTest() {
        assertEquals(("from " + AdditServices.getTableName() + " where " + test.get(0)+ " = "), 
                AdditServices.search(ADServicesE.ID));
        assertEquals(("from " + AdditServices.getTableName() + " where " + test.get(1)+ " = "), 
                AdditServices.search(ADServicesE.TITLE));
        assertEquals(("from " + AdditServices.getTableName() + " where " + test.get(2)+ " = "), 
                AdditServices.search(ADServicesE.PRICE));
    }

    

}
