package base_classes.classes;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import base_classes.classes.emuns.URE;

public class UserTest {
    private User u = null;
    private String name;
    private int id;
    private List<String> fieldsTest = new ArrayList<>();
    private String pass;
    private URE role;
    private String table_name;

    public UserTest(){
        name = "test";
        id = 1;
        pass = "1234";
        table_name = "app_user";
        role = URE.ADMIN;
        fieldsTest.add("user_id");
        fieldsTest.add("user_name");
        fieldsTest.add("user_password");
        fieldsTest.add("user_role");
        fieldsTest.add("hotel");
        u = new User(name, pass, role);
        u.setUser_id(id);
    }

    @Test
    public void getTableNameTest() {
        assertEquals("get table name", table_name, User.getTableName());
    }

    @Test
    public void fieldsTest() {
        assertEquals("fields test", fieldsTest, User.getFields());
    }
       
}
