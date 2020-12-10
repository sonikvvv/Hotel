package gui.reservations;

import java.sql.Date;

import base_classes.classes.Reservation;
import base_classes.classes.ReservationForm;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class editreservation_Controller 
{

    public void changeEverything(ReservationForm obekt)
    {
       this.reservation_name.setText(obekt.getClient_name());
       this.reservation_type.setText(obekt.getReservation_type());
       this.reservation_roomtype.setText(obekt.getRoom_type());
       this.reservation_canceltype.setText(obekt.getCancel_type());
       this.reservation_status.setText(obekt.getCancel_type());
       this.reservation_adults.setText(String.valueOf(obekt.getAdults()));
       this.reservation_babies.setText(String.valueOf(obekt.getBabys())); 
       this.reservation_foodtype.setText(obekt.getFood_type());
       this.reservation_notes.setText(obekt.getNotes());
       this.reservation_totalprice.setText(String.valueOf(obekt.getTotal_price()));
       this.reservation_startdate.setValue(obekt.getStart_date());
       this.reservation_enddate.setValue(obekt.getEnd_date());

    }

    @FXML
    private TextField reservation_name;

    @FXML
    private TextField reservation_adults;

    @FXML
    private TextField reservation_kids;

    @FXML
    private TextField reservation_babies;

    @FXML
    private TextField reservation_foodtype;

    @FXML
    private DatePicker reservation_startdate;

    @FXML
    private DatePicker reservation_enddate;

    @FXML
    private TextField reservation_status;

    @FXML
    private TextField reservation_canceltype;

    @FXML
    private TextField reservation_type;

    @FXML
    private TextField reservation_roomtype;

    @FXML
    private TextField reservation_totalprice;

    @FXML
    private TextArea reservation_notes;

    
   

}


