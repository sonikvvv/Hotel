package gui;

import base_classes.classes.UserRoles;
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

        //User user = new User("name", "password", new UserRoles("test"));
        
        UserRoles ur = new UserRoles("title");
        System.out.println(ur);
        
    }

}
