package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import base_classes.classes.User;
import base_classes.classes.emuns.URE;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import logic.operations.UserOperations;

public class HomeController extends Application implements Initializable {

    @FXML
    private Button see_all_notif_btn;

    @FXML
    private Button reservation_btn;

    @FXML
    private Button room_btn;

    @FXML
    private Button ads_btn;

    @FXML
    private Button ref_btn;

    @FXML
    private Button users_btn;

    @FXML
    private Button hotel_btn;

    @FXML
    private AnchorPane main_view_pane;

    private static final Logger LOGGER = LogManager.getLogger(HomeController.class);

    @FXML
    void ads(ActionEvent event) {
        try {
            LOGGER.info("User clciked additional services button.");
            LOGGER.debug("Starting additional services.");
            AnchorPane next = FXMLLoader.load(getClass().getResource("additional_services/ads_home.fxml"));
            main_view_pane.getChildren().setAll(next);
        } catch (Exception e) {
            LOGGER.error("Loading exeption occured -> {}", e);
        }
    }

    @FXML
    void ref(ActionEvent event) {
        try {
            LOGGER.info("User clicked references.");
            LOGGER.debug("Starting references.");
            User user_now = UserOperations.getUser_now().get(0);
            AnchorPane next = null;
            if (user_now.getUser_role() == URE.RECEPTIONIST) {
                LOGGER.debug("Loading receptionist references.");
                next = FXMLLoader.load(getClass().getResource("references/receptionist_reference.fxml"));
            } else {
                LOGGER.debug("Loading references.");
                next = FXMLLoader.load(getClass().getResource("references/reference_home.fxml"));
            }
            main_view_pane.getChildren().setAll(next);
        } catch (Exception e) {
            LOGGER.error("Loading exeption occured -> {}", e);
        }
    }

    @FXML
    void reservations(ActionEvent event) throws IOException {
        try {
            LOGGER.info("User clicked reservations button.");
            LOGGER.debug("Starting reservations.");
            AnchorPane next = FXMLLoader.load(getClass().getResource("reservations/reservations_home.fxml"));
            main_view_pane.getChildren().setAll(next);
        } catch (Exception e) {
            LOGGER.error("Loading exeption occured -> {}", e);
        }
    }

    @FXML
    void rooms(ActionEvent event) throws IOException {
        try {
            LOGGER.info("User clicked rooms button.");
            LOGGER.debug("Starting rooms.");
            AnchorPane next = FXMLLoader.load(getClass().getResource("room/room_home.fxml"));
            main_view_pane.getChildren().setAll(next);
        } catch (Exception e) {
            LOGGER.error("Loading exeption occured -> {}", e);
        }
    }

    @FXML
    void users(ActionEvent event) throws IOException {
        try {
            LOGGER.info("User clicked users button.");
            LOGGER.debug("Statring users.");
            AnchorPane next = FXMLLoader.load(getClass().getResource("user/user_home.fxml"));
            main_view_pane.getChildren().setAll(next);
        } catch (Exception e) {
            LOGGER.error("Loading exeption occured -> {}", e);
        }
    }

    @FXML
    void hotels(ActionEvent event) throws IOException {
        try {
            LOGGER.info("User clicked hotels button.");
            LOGGER.debug("Starting hotels.");
            AnchorPane next = FXMLLoader.load(getClass().getResource("hotel/hotel_home.fxml"));
            main_view_pane.getChildren().setAll(next);
        } catch (Exception e) {
            LOGGER.error("Loading exeption occured -> {}", e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LOGGER.debug("Starting initialize.");
        User user_now = UserOperations.getUser_now().get(0);
        if (user_now.getUser_role() != URE.ADMIN){
            hotel_btn.setVisible(false);
        }
        main_view_pane.getScene();

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.initStyle(StageStyle.UTILITY);
        primaryStage.setResizable(false);

    }

}
