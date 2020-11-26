package base_classes.classes;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import base_classes.classes.emuns.ServiceType;

public class ServiceCategoryTest {
    private String title;
    private List<String> test = new ArrayList<>();
    private ServiceCategory sc = null;
    private ServiceType type;
    private String table_name;
    private int id;

    public ServiceCategoryTest() {
        title = "test";
        type = ServiceType.PROSITIVE;
        id = 1;
        table_name = "service_category";
        test.add("category_id");
        test.add("category_title");
        test.add("type");
        sc = new ServiceCategory(title, type);
        sc.setCategory_id(id);
    }
    
    @Test
    public void getTableNameTest() {
        assertEquals(table_name, ServiceCategory.getTableName());
    }
    
    @Test
    public void getFieldsTest() {
        assertEquals(test, ServiceCategory.getFields());
    }
    
}
