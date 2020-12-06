package gui.user;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import base_classes.classes.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import logic.DecodeOperation;
import logic.OperationType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Add_UserController implements Initializable {

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

    private User user = null;

    public void setUser(User user) {
        LOGGER.debug("Starting set user with data -> {}", user);
        this.user = user;

        names_txt.setText(user.getName());
        phone_txt.setText(user.getPhone());
        username_txt.setText(user.getUser_name());
        email_txt.setText(user.getEmail());
        pass_txt.setText(user.getUser_password());
    }

    @FXML
    void save(ActionEvent event) {
        LOGGER.debug("Starting save user.");
        String username = username_txt.getText();
        String pass = pass_txt.getText();
        String name = names_txt.getText();
        String phone = phone_txt.getText();
        String email = email_txt.getText();

        if (username.length() == 0 || pass.length() == 0 || name.length() == 0 || phone.length() == 0
                || email.length() == 0) {
            Alert al = new Alert(AlertType.WARNING, "Don't leave empty fields!");
            al.showAndWait();
        } else {
            List<String> data = new ArrayList<>();
            data.add(username);
            data.add(pass);
            data.add(name);
            data.add(phone);
            data.add(email);
            
            if (user != null) {
                user.setName(name);
                user.setUser_name(username);
                user.setPhone(phone);
                user.setEmail(email);
                user.setUser_password(pass);
                DecodeOperation.decodeLogicOperation(OperationType.UPDATE, user, null);
                LOGGER.debug("Updating user to -> {}", user);
            }
            else {
                DecodeOperation.decodeLogicOperation(OperationType.ADD_TO_USERS, null, data);
            }

            Stage st = (Stage) names_txt.getScene().getWindow();
            st.close();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LOGGER.debug("Starting initialize.");
        if (user != null){
            names_txt.setText(user.getName());
            phone_txt.setText(user.getPhone());
            username_txt.setText(user.getUser_name());
            email_txt.setText(user.getEmail());
            pass_txt.setText(user.getUser_password());
        }
    }
}
