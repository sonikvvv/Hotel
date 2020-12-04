package gui.user;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import logic.DecodeOperation;
import logic.OperationType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Add_UserController {

    @FXML
    private TextField names_txt;

    @FXML
    private TextField phone_txt;

    @FXML
    private TextField username_txt;

    @FXML
    private TextField email_txt;

    @FXML
    private PasswordField pass_txt;

    private static final Logger LOGGER = LogManager.getLogger(Add_UserController.class);

    @FXML
    void save(ActionEvent event) {
        LOGGER.debug("Starting save user.");
        String username = username_txt.getText();
        String pass = pass_txt.getText();
        String name = names_txt.getText();
        String phone = phone_txt.getText();
        String email = email_txt.getText();

        if (username.length() == 0 || pass.length()  == 0 || name.length() == 0 || phone.length() == 0 || email.length() == 0) {
            Alert al = new Alert(AlertType.WARNING, "Don't leave empty fields!");
            al.showAndWait();
        }else {
            List<String> data = new ArrayList<>();
            data.add(username);
            data.add(pass);
            data.add(name);
            data.add(phone);
            data.add(email);
            DecodeOperation.decodeLogicOperation(OperationType.ADD_TO_USERS, null, data);
        }
    }
}
