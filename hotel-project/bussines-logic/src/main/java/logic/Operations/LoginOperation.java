package logic.Operations;

import java.util.ArrayList;
import java.util.List;

import base_classes.ClassesE;
import base_classes.DBOperationType;
import base_classes.DecodeDBOperation;

public class LoginOperation {
    
    public List<String> loginOperation(List<String> data) {
        return DecodeDBOperation.decodeDBOperation(DBOperationType.CHECK_USER, ClassesE.NULL, data);
    }

    public static void main(String[] args) {
        List<String> tes = new ArrayList<>();
        tes.add("ves");
        tes.add("test");
        LoginOperation lo = new LoginOperation();
        lo.loginOperation(tes);
    }
}
