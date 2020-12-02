package gui.room;

import java.net.URL;
import java.util.ResourceBundle;

import base_classes.classes.emuns.SE;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;


public class Add_RoomController implements Initializable {


    @FXML
    private ComboBox<String> room_type_cb;
    private ObservableList<String> choose = FXCollections.observableArrayList("Dirty", "Out of Order", "Reserved", "Occupied", "Check Out", "Free");



    @FXML
    private TextField room_price;


    @FXML
    private TextField room_number_txt;

    

    @FXML
    void save(ActionEvent event) {
        if(room_number_txt.getLength()==0 || room_price.getLength()==0 || room_type_cb.getValue()==null)
        {
                Alert al = new Alert(AlertType.WARNING, "Please fill all the fields!");
            al.showAndWait();
        }
        else
        {
            //public Room(String r_number, String r_type, double price, SE r_status)
            switch(room_type_cb.getValue())
            {
                case "Dirty":
                break;

                case "Out of Order":
                break;

                case "Reserved":
                break;

                case "Occupied":
                break;

                case "Check Out":
                break;

                case "Free":
                break;
            }
        }
        
    }


    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        room_type_cb.setItems(choose);
    }



}
