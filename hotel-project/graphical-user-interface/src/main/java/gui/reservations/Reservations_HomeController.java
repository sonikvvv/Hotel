package gui.reservations;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import base_classes.classes.Reservation;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import logic.DecodeOperation;
import logic.OperationType;

public class Reservations_HomeController implements Initializable {

    @FXML
    private DatePicker date_picker;

    @FXML
    private TableView<Reservation> reserv_table = new TableView<>();

    @FXML
    private TableColumn<Reservation, Integer> number_col;

    @FXML
    private TableColumn<Reservation, String> client_name_col;

    @FXML
    private TableColumn<Reservation, LocalDate> start_date_col;

    @FXML
    private TableColumn<Reservation, LocalDate> end_date_col;

    @FXML
    private TableColumn<Reservation, Number> adult_col;

    @FXML
    private TableColumn<Reservation, Number> kids_col;

    @FXML
    private TableColumn<Reservation, Number> babies_col;

    @FXML
    private TableColumn<Reservation, String> food_type_col;

    @FXML
    private TableColumn<Reservation, String> room_type_col;

    @FXML
    private TableColumn<Reservation, String> room_number_col;

    @FXML
    private TableColumn<Reservation, Number> price_col;

    private ObservableList<Reservation> activ = FXCollections.observableArrayList();

    @FXML
    void addReservation(ActionEvent event) {

    }

    @FXML
    void datePickerChanged(ActionEvent event) {
        List<String> data = new ArrayList<>();
        data.add(date_picker.getValue().toString());

        List<?> res = DecodeOperation.decodeLogicOperation(OperationType.GET_RESERVATIONS, null, null); //TODO: search by date
        if (res != null && res.size() != 0) {
            for (Object object : res) {
                activ.add((Reservation) object);
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        date_picker.setValue(LocalDate.now());

        number_col.setCellValueFactory(new PropertyValueFactory<>("reservation_id"));

        client_name_col.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Reservation, String>, ObservableValue<String>>() {
                    @Override
                    public ObservableValue<String> call(CellDataFeatures<Reservation, String> param) {
                        return new SimpleStringProperty(param.getValue().getReservation_form().getClient_name());
                    }
                });

        room_type_col.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Reservation, String>, ObservableValue<String>>() {
                    @Override
                    public ObservableValue<String> call(CellDataFeatures<Reservation, String> param) {
                        return new SimpleStringProperty(param.getValue().getReservation_form().getRoom_type());
                    }
                });

        food_type_col.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Reservation, String>, ObservableValue<String>>() {
                    @Override
                    public ObservableValue<String> call(CellDataFeatures<Reservation, String> param) {
                        return new SimpleStringProperty(param.getValue().getReservation_form().getFood_type());
                    }
                });

        adult_col.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Reservation, Number>, ObservableValue<Number>>() {
                    @Override
                    public ObservableValue<Number> call(CellDataFeatures<Reservation, Number> param) {
                        return new SimpleIntegerProperty(param.getValue().getReservation_form().getAdults());
                    }
                });

        kids_col.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Reservation, Number>, ObservableValue<Number>>() {
                    @Override
                    public ObservableValue<Number> call(CellDataFeatures<Reservation, Number> param) {
                        return new SimpleIntegerProperty(param.getValue().getReservation_form().getKids());
                    }
                });

        babies_col.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Reservation, Number>, ObservableValue<Number>>() {
                    @Override
                    public ObservableValue<Number> call(CellDataFeatures<Reservation, Number> param) {
                        return new SimpleIntegerProperty(param.getValue().getReservation_form().getBabys());
                    }
                });

        price_col.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Reservation, Number>, ObservableValue<Number>>() {
                    @Override
                    public ObservableValue<Number> call(CellDataFeatures<Reservation, Number> param) {
                        return new SimpleDoubleProperty(param.getValue().getReservation_form().getTotal_price());
                    }
                });

        

        List<String> data = new ArrayList<>();
        data.add(date_picker.getValue().toString());

        List<?> res = DecodeOperation.decodeLogicOperation(OperationType.GET_RESERVATIONS, null, null);
        if (res != null && res.size() != 0) {
            for (Object object : res) {
                activ.add((Reservation) object);
            }
        }

        reserv_table.getItems().setAll(activ);

    }

}

