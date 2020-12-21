package gui.reservations;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import base_classes.classes.Reservation;
import base_classes.classes.ReservationForm;
import base_classes.classes.Room;
import base_classes.classes.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import logic.DecodeOperation;
import logic.OperationType;
import logic.operations.UserOperations;

public class AddReservation_Controller implements Initializable {

    @FXML
    private DatePicker start_date;

    @FXML
    private DatePicker end_date;

    @FXML
    private TextField name_txt;

    @FXML
    private TextField adults_txt;

    @FXML
    private TextField kids_txt;

    @FXML
    private TextField babies_txt;

    @FXML
    private TextField total_price_txt;

    @FXML
    private TextField reservation_type_txt;

    @FXML
    private TextField room_type_txt;

    @FXML
    private TextField cancel_type_txt;

    @FXML
    private TextField status_txt;

    @FXML
    private TextField food_type_txt;

    @FXML
    private ComboBox<String> type_room_cb;

    @FXML
    private ComboBox<String> room_number_cb;

    @FXML
    private TextArea notes_txt;

    private Reservation res = null;

    private List<Room> rooms = new ArrayList<>();

    private List<String> room_types = new ArrayList<>();

    private static final Logger LOGGER = LogManager.getLogger(Reservations_HomeController.class);

    public void setRes(Reservation res) {
        LOGGER.debug("Setting reservation to -> {}", res);
        this.res = res;
        name_txt.setText(res.getReservation_form().getClient_name());
        adults_txt.setText(res.getReservation_form().getAdults() + "");
        kids_txt.setText(res.getReservation_form().getKids() + "");
        babies_txt.setText(res.getReservation_form().getBabys() + "");
        food_type_txt.setText(res.getReservation_form().getFood_type());
        start_date.setValue(res.getReservation_form().getStart_date());
        end_date.setValue(res.getReservation_form().getEnd_date());
        status_txt.setText(res.getReservation_form().getStatus());
        cancel_type_txt.setText(res.getReservation_form().getCancel_type());
        reservation_type_txt.setText(res.getReservation_form().getReservation_type());
        room_type_txt.setText(res.getReservation_form().getRoom_type());
        total_price_txt.setText(res.getReservation_form().getTotal_price() + "");
        notes_txt.setText(res.getReservation_form().getNotes());
        load_rooms();
        int type = room_types.indexOf(res.getRoom().getR_type());
        type_room_cb.getSelectionModel().select(room_types.get(type));
        room_number_cb.getSelectionModel().select(res.getRoom().getR_number());
    }

    @FXML
    void ChangeRoomList(ActionEvent event) {
        LOGGER.debug("Starting change room list.");
        String type = type_room_cb.getSelectionModel().getSelectedItem();
        List<String> room_n = new ArrayList<>();
        for (Room room2 : rooms) {
            if (room2.getR_type().equals(type)) {
                room_n.add(room2.getR_number());
            }
        }

        room_number_cb.getItems().setAll(room_n);
    }

    public void load_rooms() {
        LOGGER.debug("Starting load rooms.");
        rooms.clear();
        room_types.clear();

        List<String> data = new ArrayList<>();
        data.add(start_date.getValue().toString());
        data.add(end_date.getValue().toString());

        List<?> result = DecodeOperation.decodeLogicOperation(OperationType.GET_FREE_ROOMS, null, data);
        for (Object object : result) {
            rooms.add((Room) object);
        }

        result = DecodeOperation.decodeLogicOperation(OperationType.ROOM_TYPES, null, null);
        for (Object object : result) {
            room_types.add((String) object);
        }

        type_room_cb.getItems().setAll(room_types);
    }

    @FXML
    void next(ActionEvent event) {
        LOGGER.debug("Starting next.");
        String name = name_txt.getText();
        String adults = adults_txt.getText();
        String kids = kids_txt.getText();
        String babies = babies_txt.getText();
        String food_type = food_type_txt.getText();
        LocalDate start_date_ = start_date.getValue();
        LocalDate end_date_ = end_date.getValue();
        String status = status_txt.getText();
        String cancel_type = cancel_type_txt.getText();
        String reservation_type = reservation_type_txt.getText();
        String room_type = room_type_txt.getText();
        String total_price = total_price_txt.getText();
        String notes = notes_txt.getText();
        String room_n = room_number_cb.getSelectionModel().getSelectedItem();
        String room_t = type_room_cb.getSelectionModel().getSelectedItem();

        if (name != null || adults != null || kids != null || babies != null || food_type != null || status != null
                || cancel_type != null || reservation_type != null || room_type != null || total_price != null
                || start_date_ != null || end_date_ != null) {

            if (name.length() != 0 || adults.length() != 0 || kids.length() != 0 || babies.length() != 0
                    || food_type.length() != 0 || status.length() != 0 || cancel_type.length() != 0
                    || reservation_type.length() != 0 || room_type.length() != 0 || total_price.length() != 0) {

                if (adults.matches("([0-9]*)") || kids.matches("([0-9]*)") || babies.matches("([0-9]*)")
                        || total_price.matches("([0-9]*)\\,|\\.([0-9]*)")) {
                    if (res != null) {
                        ReservationForm rf = new ReservationForm(reservation_type, room_type, cancel_type,
                                start_date_, end_date_, Integer.valueOf(adults),
                                Integer.valueOf(kids), Integer.valueOf(babies), food_type, Double.valueOf(total_price),
                                status, notes, name);
                        
                        if (res.getRoom().getR_number().equals(room_n) && res.getRoom().getR_type().equals(room_t)) {
                            // the room is the same
                        }else {
                            Room room = null;
                            for (Room room_ : rooms) {
                                if (room_.getR_number().equals(room_n)) {
                                    room = room_;
                                    break;
                                }
                            }
                            res.setRoom(room);
                        }
                        
                        res.setReservation_form(rf);
                        LOGGER.debug("Reservation changed to -> {}", res);
                        DecodeOperation.decodeLogicOperation(OperationType.UPDATE, res, null);

                        Stage st = (Stage) adults_txt.getScene().getWindow();
                        st.close();
                    } else {
                        ReservationForm rf = new ReservationForm(reservation_type, room_type, cancel_type,
                                start_date_, end_date_, Integer.valueOf(adults),
                                Integer.valueOf(kids), Integer.valueOf(babies), food_type, Double.valueOf(total_price),
                                status, notes, name);

                        User user_now = UserOperations.getUser_now().get(0);
                        Room room = null;
                        for (Room room_ : rooms) {
                            if (room_.getR_number().equals(room_n)){
                                room = room_;
                                break;
                            }
                        }
                        res = new Reservation(user_now, rf, room, user_now.getHotel().get(0));

                        LOGGER.debug("New reservation -> {}", res);
                        DecodeOperation.decodeLogicOperation(OperationType.SAVE, res, null);

                        Stage st = (Stage) adults_txt.getScene().getWindow();
                        st.close();
                    }
                } else {
                    Alert al = new Alert(AlertType.WARNING,
                            "Fields for price, adults, kids and babies are for numbers only!");
                    al.showAndWait();
                }
            } else {
                Alert al = new Alert(AlertType.WARNING, "Don't leave empty fields!");
                al.showAndWait();
            }
        } else {
            Alert al = new Alert(AlertType.WARNING, "Don't leave empty fields!");
            al.showAndWait();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LOGGER.debug("Starting initialize.");

        if (res == null) {
            start_date.setValue(LocalDate.now());
            end_date.setValue(LocalDate.now());
        } else setRes(res);

        load_rooms();
    }
}
