package gui.login;

import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import logic.OperationType;
import logic.*;

public class LoginController {

    @FXML
    private TextField username_txt;

    @FXML
    private PasswordField pas_txt;

    @FXML
    void logIn(MouseEvent event) {
        System.out.println(username_txt.getText() + " " + pas_txt.getText());

        List<String> data = new ArrayList<>();
        data.add(username_txt.getText());
        data.add(pas_txt.getText());

        List result = DecodeOperation.decodeLogicOperation(OperationType.LOGIN, null, data);
        Boolean flag = Boolean.valueOf( (String) result.get(0));

        if (flag == false) {
            username_txt.setStyle("-fx-border-color: #ff0000");
        }
    }

}
