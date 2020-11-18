package gui.room;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import base_classes.classes.Room;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

public class room_HomeController implements Initializable {

    @FXML
    private DatePicker fromDate;

    @FXML
    private DatePicker toDate;

    @FXML
    private VBox vbox_room;

    @FXML
    private Circle occupied_crl;

    @FXML
    private Circle out_of_order_crl;

    @FXML
    private Circle free_crl;

    @FXML
    private Circle dirty_crl;

    @FXML
    private Circle check_out_crl;

    @FXML
    void add_btn(ActionEvent event) {
        // TODO: get user role
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Room> roomList;


    }

}
