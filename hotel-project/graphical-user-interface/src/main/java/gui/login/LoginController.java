package gui.login;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import logic.OperationType;
import logic.DecodeOperation;

public class LoginController {

    @FXML
    private TextField username_txt;

    @FXML
    private PasswordField pas_txt;

    private static final Logger LOGGER = LogManager.getLogger(LoginController.class);

    @FXML
    void logIn(MouseEvent event) {
        LOGGER.info("User clicked login button.");
        LOGGER.debug("Starting login.");
        List<String> data = new ArrayList<>();
        String username = username_txt.getText();
        String pas = pas_txt.getText();

        if (username == null || username.length() == 0) {
            Alert alert = new Alert(AlertType.WARNING, "Don't leave empty fields!");
            alert.showAndWait();
        } else {
            LOGGER.debug("Logging in with username: {}", username);

            data.add(username);
            data.add(pas);
            
            List<?> result = DecodeOperation.decodeLogicOperation(OperationType.LOGIN, null, data);
            Boolean flag = Boolean.valueOf( (String) result.get(0));

            if (flag == false) {
                username_txt.setStyle("-fx-border-color: #ff0000"); // TODO: change the denied login identification
                LOGGER.debug("Login denied.");
            }else {
                try {
                    LOGGER.debug("Login accepted.");
                    username_txt.getScene().getWindow().hide();
                    Stage st = new Stage();
                    Scene sc = new Scene(FXMLLoader.load(getClass().getResource("../home_view1.fxml")));
                    st.setScene(sc);
                    st.show();
                    LOGGER.debug("Home view scene loaded succesfuly.");
                } catch (Exception e) {
                    LOGGER.error("Loading exeption occured -> {}", e);
                }
            }
        }
    }
}
