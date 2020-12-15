package gui.reservations;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;

import base_classes.classes.Reservation;
import base_classes.classes.ReservationForm;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
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
    public void addReservation() throws IOException
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
        int intAdults;
        int intKids;
        int intBabies;
        double doubleTotalPrice;

        LocalDate nachalo = reservation_startdate.getValue();
        LocalDate krai = reservation_enddate.getValue();

        if (name.trim().isEmpty() == true || type.trim().isEmpty() == true || cancelType.trim().isEmpty() == true
                || roomType.trim().isEmpty() == true || status.trim().isEmpty() == true
                || foodType.trim().isEmpty() == true || adults.trim().isEmpty() == true || kids.trim().isEmpty() == true
                || babies.trim().isEmpty() == true || totalPrice.trim().isEmpty() == true || nachalo == null || krai == null) {
            
            Alert al = new Alert(AlertType.WARNING, "Don't leave empty fields!");
            al.showAndWait();
        } 
        // else if (!(name.contains("[a-zA-Z]+") || type.contains("[a-zA-Z]+") || cancelType.contains("[a-zA-Z]+") 
        // || roomType.contains("[a-zA-Z]+") || status.contains("[a-zA-Z]+") || foodType.contains("[a-zA-Z]+") || 
        // adults.matches("[0-9]+") || kids.matches("[0-9]+") || babies.matches("[0-9]+")  || totalPrice.matches("[0-9]+")))
        
        else if (totalPrice.matches("[0-9]+") && adults.matches("[0-9]+") && kids.matches("[0-9]+") && babies.matches("[0-9]+"))
        {
            intAdults = Integer.valueOf(adults);
            intKids =  Integer.valueOf(kids);
            intBabies  = Integer.valueOf(babies);
            doubleTotalPrice = Double.valueOf(totalPrice);
            URL location = editreservation_Controller.class.getResource("../reservations/chooseRoom.fxml");
            FXMLLoader loader = new FXMLLoader(location);
            Parent parent = loader.load();
            chooseRoom_controller cont = loader.getController();
            cont.changeDate(nachalo, krai);
            //cont.load();

           Stage st = new Stage();
           Scene sc;
           sc = new Scene(parent);
           st.setScene(sc);
           st.showAndWait();
            // // List<String> t = new ArrayList<>();

            // ReservationForm forma = new ReservationForm(type, roomType, cancelType, nachalo, krai, intAdults, intKids,
            //         intBabies, foodType, doubleTotalPrice, status, notes, name);
            // Reservation reservation = new Reservation(UserOperations.getUser_now().get(0), forma, null); // TODO: select room in hotel
            // //DecodeOperation.decodeLogicOperation(OperationType.SAVE_OR_UPDATE, reservation, null);

        }
        else
        {
            Alert al = new Alert(AlertType.WARNING, "Don't leave fields with wrong type of information!");
            al.showAndWait();
        }
        
    }
}
