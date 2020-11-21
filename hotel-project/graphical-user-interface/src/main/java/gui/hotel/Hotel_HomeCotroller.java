package gui.hotel;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import base_classes.classes.Hotel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import logic.DecodeOperation;
import logic.OperationType;

public class Hotel_HomeCotroller implements Initializable {

    @FXML
    private TableView<Hotel> hotel_table;

    @FXML
    private TableColumn<Hotel, String> hotel_name_col;

    private ObservableList<Hotel> activ = FXCollections.observableArrayList();

    @FXML
    void saveHotelName(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        hotel_name_col.setCellValueFactory(new PropertyValueFactory<>("hotel_name"));

        List<?> result = DecodeOperation.decodeLogicOperation(OperationType.GET_HOTEL, null, null);
        if (result != null && result.size() != 0) {
            for (Object object : result) {
                activ.add((Hotel) object);
            }
        }

    }

}
