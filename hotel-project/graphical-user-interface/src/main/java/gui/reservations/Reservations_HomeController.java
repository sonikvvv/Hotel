package gui.reservations;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import base_classes.classes.Reservation;
import base_classes.classes.ReservationForm;
import base_classes.classes.User;
import base_classes.classes.emuns.URE;
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
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class Reservations_HomeController implements Initializable {

    @FXML
    private DatePicker fromDate;

    @FXML
    private DatePicker toDate;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fromDate.setValue(LocalDate.now());
        toDate.setValue(LocalDate.now());

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

        LocalDate fromD = fromDate.getValue();
        LocalDate toD = toDate.getValue();

        List<String> data = new ArrayList<>();
        data.add(fromD.toString());
        data.add(toD.toString());
        data.add("1");

        // List<?> res = TODO: get rooms
        // DecodeOperation.decodeLogicOperation(OperationType.CREATED_RESERVATIONS,
        // null, data);

        // for (Object object : res) {
        // activ.add((Reservation) object);
        // }

        ReservationForm fr = new ReservationForm("reservation_type", "room_type", "cancel_type",
                LocalDate.of(2020, 11, 15), LocalDate.of(2020, 12, 15), 1, 1, 0, "food_type", 1000, "status", "notes",
                "client_name");

        Reservation r = new Reservation(new User("mej", "fedlslf", URE.ADMIN), fr, null);
        Reservation r1 = new Reservation(new User("mej", "fedlslf", URE.ADMIN), fr, null);

        activ.add(r);
        activ.add(r1);

        reserv_table.getItems().setAll(activ);

    }

}
