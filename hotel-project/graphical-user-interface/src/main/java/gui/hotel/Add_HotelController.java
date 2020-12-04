package gui.hotel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
    
    private static final Logger LOGGER = LogManager.getLogger(Add_HotelController.class);

    @FXML
    void save(ActionEvent event) {
        LOGGER.info("Add hotel save button clicked.");
        LOGGER.debug("Starting save new hotel.");
        String hotel_name = hotel_name_txt.getText();

        if (hotel_name == null || hotel_name.length() == 0){
            Alert alert = new Alert(AlertType.WARNING, "Don't leave the field empty!");
            alert.showAndWait();
        } else {
            Hotel hotel = new Hotel(hotel_name);
            LOGGER.debug("Creating new hotel: {}", hotel);
            DecodeOperation.decodeLogicOperation(OperationType.SAVE_OR_UPDATE, hotel, null);
        }
    }

}
