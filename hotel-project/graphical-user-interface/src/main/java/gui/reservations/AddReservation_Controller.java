package gui.reservations;

import java.time.LocalDate;

import base_classes.classes.Reservation;
import base_classes.classes.ReservationForm;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import logic.DecodeOperation;
import logic.OperationType;
import logic.operations.UserOperations;

public class AddReservation_Controller {
    
    @FXML
    private TextField reservation_name;

    @FXML
    private TextField reservation_type;

    @FXML
    private TextField reservation_canceltype;

    @FXML
    private TextField reservation_roomtype;

    @FXML
    private TextField reservation_status;

    @FXML
    private TextField reservation_adults;

    @FXML
    private TextField reservation_kids;

    @FXML
    private TextField reservation_babies;

    @FXML
    private TextField reservation_foodtype;

    @FXML
    private TextArea reservation_notes;

    @FXML
    private DatePicker reservation_startdate;

    @FXML
    private DatePicker reservation_enddate;

    @FXML
    private TextField reservation_totalprice;

   
    @FXML
    public void addReservation()
    {
        String name = reservation_name.getText();
        String type = reservation_type.getText();
        String cancelType = reservation_canceltype.getText();
        String roomType = reservation_roomtype.getText();
        String status = reservation_status.getText();
        String foodType = reservation_foodtype.getText();
        String notes = reservation_notes.getText();
        String adults = reservation_adults.getText();
        String kids = reservation_kids.getText();
        String babies = reservation_babies.getText();
        String totalPrice = reservation_totalprice.getText();
        int intAdults = Integer.valueOf(adults);
        int intKids = Integer.valueOf(kids);
        int intBabies = Integer.valueOf(babies);
        double doubleTotalPrice = Double.valueOf(totalPrice);

        LocalDate nachalo = reservation_startdate.getValue();
        LocalDate krai = reservation_enddate.getValue();

        if (name.trim().isEmpty() == true || type.trim().isEmpty() == true || cancelType.trim().isEmpty() == true
                || roomType.trim().isEmpty() == true || status.trim().isEmpty() == true
                || foodType.trim().isEmpty() == true || adults.trim().isEmpty() == true || kids.trim().isEmpty() == true
                || babies.trim().isEmpty() == true || totalPrice.trim().isEmpty() == true) {
            
            Alert al = new Alert(AlertType.WARNING, "Don't leave empty fields!");
            al.showAndWait();
        } else {
            // List<String> t = new ArrayList<>();

            ReservationForm forma = new ReservationForm(type, roomType, cancelType, nachalo, krai, intAdults, intKids,
                    intBabies, foodType, doubleTotalPrice, status, notes, name);
            Reservation reservation = new Reservation(UserOperations.getUser_now().get(0), forma, null); // TODO: select room in hotel
            //DecodeOperation.decodeLogicOperation(OperationType.SAVE_OR_UPDATE, reservation, null);

        }

        
    }
}
