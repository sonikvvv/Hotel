package gui.hotel;

import java.net.URL;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import base_classes.classes.Hotel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import logic.DecodeOperation;
import logic.OperationType;

public class Add_HotelController implements Initializable {

    @FXML
    private TextField hotel_name_txt;

    private static final Logger LOGGER = LogManager.getLogger(Add_HotelController.class);

    private Hotel hotel = null;

    public void setHotel(Hotel hotel) {
        LOGGER.debug("Starting set hotel with data -> {}", hotel);
        this.hotel = hotel;
        hotel_name_txt.setText(hotel.getHotel_name());
    }

    @FXML
    void save(ActionEvent event) {
        LOGGER.info("Add hotel save button clicked.");
        LOGGER.debug("Starting save new hotel.");
        String hotel_name = hotel_name_txt.getText();

        if (hotel_name == null || hotel_name.length() == 0) {
            Alert alert = new Alert(AlertType.WARNING, "Don't leave the field empty!");
            alert.showAndWait();
        } else {
            if (hotel == null) {
                hotel = new Hotel(hotel_name);
                LOGGER.debug("Creating new hotel: {}", hotel);
                DecodeOperation.decodeLogicOperation(OperationType.SAVE, hotel, null);
            }
            else {
                hotel.setHotel_name(hotel_name);
                LOGGER.debug("Renaming existing hotel to: {}", hotel);
                DecodeOperation.decodeLogicOperation(OperationType.UPDATE, hotel, null);
            }

            Stage st = (Stage) hotel_name_txt.getScene().getWindow();
            st.close();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LOGGER.debug("Starting initialize.");
        if (hotel != null) {
            hotel_name_txt.setText(hotel.getHotel_name());
        }
    }

}
