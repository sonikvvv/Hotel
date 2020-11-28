package gui.hotel;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import base_classes.classes.Hotel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import logic.DecodeOperation;
import logic.OperationType;

public class Hotel_HomeCotroller implements Initializable {

    @FXML
    private TableView<Hotel> hotel_table;

    @FXML
    private TableColumn<Hotel, String> hotel_name_col;

    private ObservableList<Hotel> activ = FXCollections.observableArrayList();

    @FXML
    void addHotel(ActionEvent event) {
        try {
            Stage st = new Stage();
            Scene sc;
            sc = new Scene(FXMLLoader.load(getClass().getResource("add_hotel.fxml")));
            st.setScene(sc);
            st.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void keyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.DELETE) {
            Alert al = new Alert(AlertType.CONFIRMATION);
            al.setContentText("Delete this hotel?");
            Optional<ButtonType> result = al.showAndWait();
            Hotel to_delete;

            if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
                to_delete = hotel_table.getSelectionModel().getSelectedItem();
                if (to_delete != null) {
                    hotel_table.getItems().remove(to_delete);
                    DecodeOperation.decodeLogicOperation(OperationType.DELETE, to_delete, null);
                }
            }
        }
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

        hotel_table.getItems().setAll(activ);
    }

}
