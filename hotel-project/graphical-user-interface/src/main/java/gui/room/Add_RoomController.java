package gui.room;

import java.net.URL;
import java.util.ResourceBundle;

import base_classes.classes.Room;
import base_classes.classes.emuns.SE;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import logic.DecodeOperation;
import logic.OperationType;

public class Add_RoomController implements Initializable {

    @FXML
    private ComboBox<String> room_type_cb;
    private ObservableList<String> choose = FXCollections.observableArrayList("Single", "Double", "Triple", "Quad",
            "Queen", "King", "Twin", "Suite");

    @FXML
    private TextField room_price;

    @FXML
    private TextField room_number_txt;

    @FXML
    void save(ActionEvent event) {
        if (room_number_txt.getLength() == 0 || room_price.getLength() == 0 || room_type_cb.getValue() == null) {
            Alert al = new Alert(AlertType.WARNING, "Please fill all the fields!");
            al.showAndWait();
        }

        else if (room_price.getText().matches("[0-9]+") && room_number_txt.getText().matches("[0-9]+")) {
            double newprice = Double.valueOf(room_price.getText());
            Room staq = new Room(room_number_txt.getText(), room_type_cb.getValue(), newprice, SE.FREE);
            DecodeOperation.decodeLogicOperation(OperationType.SAVE, staq, null);
            Stage st = (Stage) room_price.getScene().getWindow();
            st.close();
        } else {
            Alert al = new Alert(AlertType.WARNING, "Please type the price with the correct data!");
            al.showAndWait();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        room_type_cb.setItems(choose);
        // room_type_cb.setEditable(true);
    }

}
