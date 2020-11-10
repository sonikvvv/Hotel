package logic.operations;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;


public class LoginOperationTest {
    
    @Test
    public void authenticationTest() {
        List<String> tes = new ArrayList<>();
        tes.add("ves");
        tes.add("test");
        LoginOperation.authenticationOperation(tes);
    }
}
