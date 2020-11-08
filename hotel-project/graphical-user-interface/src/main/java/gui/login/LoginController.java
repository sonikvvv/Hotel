package gui.login;

import java.util.List;

import base_classes.DBConnection;
import base_classes.classes.User;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class LoginController {

    @FXML
    private TextField username_txt;

    @FXML
    private PasswordField pas_txt;

    @FXML
    void logIn(MouseEvent event) {
        System.out.println(username_txt.getText() + " " + pas_txt.getText());

        //List<String> data
    }

}
