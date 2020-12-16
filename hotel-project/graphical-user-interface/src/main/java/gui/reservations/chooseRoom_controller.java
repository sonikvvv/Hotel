package gui.reservations;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import base_classes.classes.Reservation;
import base_classes.classes.Room;
import base_classes.classes.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import logic.DecodeOperation;
import logic.OperationType;
import logic.operations.UserOperations;

public class chooseRoom_controller implements Initializable {

    private static LocalDate startingDate;
    private static LocalDate endingDate;

    public void changeDate(LocalDate one, LocalDate two) {
        int broqch = 0;
        startingDate = one;
        endingDate = two;
        List<String> data = new ArrayList<>();
        data.add(startingDate.toString());
        data.add(endingDate.toString());
        List<?> results = DecodeOperation.decodeLogicOperation(OperationType.GET_FREE_ROOMS, null, data);
      // //List<?> results = DecodeOperation.decodeLogicOperation(OperationType.GET_ROOMS, null, null);
        if (results != null && results.size() != 0) {
            for (Object object : results) {
                Room room = (Room) object;
                broqch++;
                freeRooms.add(room);

            }
           System.out.println(String.valueOf(broqch + " STAI OBSHTO"));
           combobox_typeofroom.setItems(choose);
        }

    }
//TODO The Room function get_Rtype doesn't return the correct thing (it returns "r_type")
    @FXML
    void ChangeRoomList(ActionEvent event) {
        System.out.println("Vlqzohme v nachaloto");
        for (Room room : freeRooms) 
        {
            System.out.println("FOR-A se izpulnqva !");
            System.out.println(room.getR_type());
            //if(room.getR_type() == combobox_typeofroom.getValue().toString())
            if(room.getR_type() == room.getR_type())
            {
                System.out.println("VUV IF-A");
                roomlist.add(room.getR_number());
                
            }
        }
        System.out.println("SLED FOR-A");
        combobox_specificroom.setItems(roomlist);
    }

    @FXML
    void testchange(ActionEvent event) {
        combobox_specificroom.setItems(choose);
    }

    private ObservableList<String> roomlist = FXCollections.observableArrayList();

    private ObservableList<String> choose = FXCollections.observableArrayList("Single", "Double", "Triple", "Quad",
            "Queen", "King", "Twin", "Suite");

    @FXML
    private ComboBox<String> combobox_typeofroom;

    @FXML
    private ComboBox<String> combobox_specificroom;

    @FXML
    private AnchorPane mainAnchorPane;

    @FXML
    private GridPane mainGridPane;

    @FXML
    private Label mainLabel;

    @FXML
    private Button Savebutton;


    private ObservableList<Room> freeRooms = FXCollections.observableArrayList();

    public void initialize(URL location, ResourceBundle resources) {
        // List<String> data = new ArrayList<>();
        // data.add(startingDate.toString());
        // data.add(endingDate.toString());
        // List<?> results = DecodeOperation.decodeLogicOperation(OperationType.GET_FREE_ROOMS, null, data);
        // if (results != null && results.size() != 0) {
        //     for (Object object : results) {
        //         Room room = (Room) object;
        //         freeRooms.add(room);
        //     }
        // }
        // tableview.getItems().setAll(freeRooms);
    }

}
