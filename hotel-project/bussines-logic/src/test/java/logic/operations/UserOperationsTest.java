package logic.operations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import base_classes.classes.User;
import base_classes.classes.emuns.URE;

public class UserOperationsTest {
    private User user_now = new User("name", "password", URE.ADMIN);

    public UserOperationsTest() {
        UserOperations.setUser_now(user_now);
    }

    @Test
    public void setUser_nowTest() {
        assertNotNull(UserOperations.getUser_now().get(0));
    }

    @Test
    public void getUser_nowTest() {
        assertNotNull(UserOperations.getUser_now().get(0));
        assertEquals(user_now, UserOperations.getUser_now().get(0));
    }
}
