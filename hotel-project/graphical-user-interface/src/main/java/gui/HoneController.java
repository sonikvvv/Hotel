package gui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class HoneController {

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
    private AnchorPane main_view_pane;

    @FXML
    void ads(ActionEvent event) throws IOException {
        AnchorPane next = FXMLLoader.load(getClass().getResource("additional_services/ads_home.fxml"));
        main_view_pane.getChildren().setAll(next);

    }

    @FXML
    void ref(ActionEvent event) throws IOException {
        AnchorPane next = FXMLLoader.load(getClass().getResource("references/reference_home.fxml"));
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

}