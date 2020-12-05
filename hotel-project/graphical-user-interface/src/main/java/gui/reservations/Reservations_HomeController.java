package gui.reservations;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import base_classes.classes.Reservation;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import logic.DecodeOperation;
import logic.OperationType;
import logic.operations.UserOperations;

public class Reservations_HomeController implements Initializable {

    @FXML
    private Button add_btn;
    
    @FXML
    private DatePicker date_picker;

    @FXML
    private TableView<Reservation> reserv_table = new TableView<>();

    @FXML
    private TableColumn<Reservation, Integer> number_col;

    @FXML
    private TableColumn<Reservation, String> client_name_col;

    @FXML
    private TableColumn<Reservation, String> start_date_col;

    @FXML
    private TableColumn<Reservation, String> end_date_col;

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

    private static final Logger LOGGER = LogManager.getLogger(Reservations_HomeController.class);

    @FXML
    void addReservation(ActionEvent event) {
        try {
            LOGGER.info("User cliecked add reservation button.");
            LOGGER.debug("Starting add reservation.");
            Stage stage = new Stage();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("../reservations/addreservation.fxml")));
            stage.setScene(scene);
            stage.show();
            LOGGER.debug("Add reservation scene loaded succesfuly.");
        } catch (Exception e) {
            LOGGER.error("Loading exeption occured -> {}", e);
        }
    }

    @FXML
    void datePickerChanged(ActionEvent event) {
        LOGGER.info("User changed date.");
        LOGGER.debug("Starting date picker changed.");
        List<String> data = new ArrayList<>();
        data.add(date_picker.getValue().toString());
        activ.clear();

        LOGGER.debug("Date changet to -> {}", date_picker.getValue().toString());
        List<?> res = DecodeOperation.decodeLogicOperation(OperationType.GET_RESERVATIONS, null, data);
        if (res != null && res.size() != 0) {
            for (Object object : res) {
                activ.add((Reservation) object);
            }
        }

        reserv_table.getItems().clear();
        reserv_table.getItems().addAll(activ);
    }

    @FXML
    void rowClicked(MouseEvent event) {
        if (event.getClickCount() == 2) {
            System.out.println("2 click");

        }
    }

    @FXML
    void delete(KeyEvent event) {
        LOGGER.info("User pressed key -> {}", event.getCode());
        LOGGER.debug("Starting delete reservation.");
        User user_now = UserOperations.getUser_now().get(0);
        if (user_now.getUser_role() == URE.MANAGER) {
            if (event.getCode() == KeyCode.DELETE) {

                Alert al = new Alert(AlertType.CONFIRMATION);
                al.setContentText("Delete this reservation?");
                Optional<ButtonType> result = al.showAndWait();
                Reservation to_delete;

                if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
                    to_delete = reserv_table.getSelectionModel().getSelectedItem();
                    if (to_delete != null) {
                        LOGGER.debug("Reservation to delete -> {}", to_delete);
                        reserv_table.getItems().remove(to_delete);
                        DecodeOperation.decodeLogicOperation(OperationType.DELETE, to_delete, null);
                    }

                }
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LOGGER.debug("Starting initialize.");
        
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

        start_date_col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Reservation,String>,ObservableValue<String>>(){

            @Override
            public ObservableValue<String> call(CellDataFeatures<Reservation, String> param) {
                return new SimpleStringProperty(param.getValue().getReservation_form().getStart_date().toString());
            }
            
        });

        end_date_col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Reservation,String>,ObservableValue<String>>(){

            @Override
            public ObservableValue<String> call(CellDataFeatures<Reservation, String> param) {
                return new SimpleStringProperty(param.getValue().getReservation_form().getEnd_date().toString());
            }
            
        });

        room_number_col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Reservation,String>,ObservableValue<String>>(){

            @Override
            public ObservableValue<String> call(CellDataFeatures<Reservation, String> param) {
                return new SimpleStringProperty(param.getValue().getRoom().getR_number());
            }
            
        });

        List<String> data = new ArrayList<>();
        data.add(date_picker.getValue().toString());

        List<?> res = DecodeOperation.decodeLogicOperation(OperationType.GET_RESERVATIONS, null, data);
        if (res != null && res.size() != 0) {
            for (Object object : res) {
                activ.add((Reservation) object);
            }
        }

        reserv_table.getItems().setAll(activ);

    }

}

