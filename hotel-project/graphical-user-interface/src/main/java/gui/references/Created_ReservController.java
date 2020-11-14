package gui.references;

import base_classes.classes.ReservationForm;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class Created_ReservController {

    @FXML
    private AnchorPane reserv_pane;

    @FXML
    private TableView<ReservationForm> reserv_table;

    @FXML
    private TableColumn<ReservationForm, Integer> number_col;

    @FXML
    private TableColumn<ReservationForm, ?> status_col;

    @FXML
    private TableColumn<ReservationForm, ?> hotel_col;

    @FXML
    private TableColumn<ReservationForm, ?> client_name_col;

    @FXML
    private TableColumn<ReservationForm, ?> reserv_type_col;

    @FXML
    private TableColumn<ReservationForm, ?> room_type_col;

    @FXML
    private TableColumn<ReservationForm, ?> cancel_type_col;

    @FXML
    private TableColumn<ReservationForm, ?> food_type_col;

    @FXML
    private TableColumn<ReservationForm, ?> start_date_col;

    @FXML
    private TableColumn<ReservationForm, ?> end_date_col;

    @FXML
    private TableColumn<ReservationForm, ?> adults_col;

    @FXML
    private TableColumn<ReservationForm, ?> kids_col;

    @FXML
    private TableColumn<ReservationForm, ?> babies_col;

    @FXML
    private TableColumn<ReservationForm, ?> price_col;

    @FXML
    private TableColumn<ReservationForm, ?> notes_col;

}
