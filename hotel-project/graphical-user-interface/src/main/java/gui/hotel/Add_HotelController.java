package gui.hotel;

import base_classes.classes.Hotel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import logic.DecodeOperation;
import logic.OperationType;

public class Add_HotelController {

    @FXML
    private TextField hotel_name_txt;

    @FXML
    void save(ActionEvent event) {
        String hotel_name = hotel_name_txt.getText();

        if (hotel_name == null || hotel_name.length() == 0){
            Alert alert = new Alert(AlertType.WARNING, "Don't leave the field empty!");
            alert.showAndWait();
        } else {
            Hotel hotel = new Hotel(hotel_name);
            DecodeOperation.decodeLogicOperation(OperationType.SAVE_OR_UPDATE, hotel, null);
        }
    }

}
