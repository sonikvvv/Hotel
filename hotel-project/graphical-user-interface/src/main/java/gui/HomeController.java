package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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

    @FXML
    void ads(ActionEvent event) throws IOException {
        AnchorPane next = FXMLLoader.load(getClass().getResource("additional_services/ads_home.fxml"));
        main_view_pane.getChildren().setAll(next);

    }

    @FXML
    void ref(ActionEvent event) throws IOException {
        User user_now = UserOperations.getUser_now().get(0);
        AnchorPane next = null;
        if (user_now.getUser_role() == URE.RECEPTIONIST){
            next = FXMLLoader.load(getClass().getResource("references/receptionist_reference.fxml"));
        } else {
            next = FXMLLoader.load(getClass().getResource("references/reference_home.fxml"));
        }
        main_view_pane.getChildren().setAll(next);
    }

    @FXML
    void reservations(ActionEvent event) throws IOException {
        AnchorPane next = FXMLLoader.load(getClass().getResource("reservations/reservations_home.fxml"));
        main_view_pane.getChildren().setAll(next);
    }

    @FXML
    void rooms(ActionEvent event) throws IOException {
        AnchorPane next = FXMLLoader.load(getClass().getResource("room/room_home.fxml"));
        main_view_pane.getChildren().setAll(next);
    }

    @FXML
    void users(ActionEvent event) throws IOException {
        AnchorPane next = FXMLLoader.load(getClass().getResource("user/user_home.fxml"));
        main_view_pane.getChildren().setAll(next);
    }

    @FXML
    void hotels(ActionEvent event) throws IOException {
        AnchorPane next = FXMLLoader.load(getClass().getResource("hotel/hotel_home.fxml"));
        main_view_pane.getChildren().setAll(next);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
