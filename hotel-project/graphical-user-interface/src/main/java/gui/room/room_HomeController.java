package gui.room;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import base_classes.classes.Room;
import base_classes.classes.emuns.SE;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

public class room_HomeController implements Initializable {

    @FXML
    private DatePicker fromDate;

    @FXML
    private DatePicker toDate;

    @FXML
    private VBox vbox_room;

    @FXML
    private Circle occupied_crl;

    @FXML
    private Circle out_of_order_crl;

    @FXML
    private Circle free_crl;

    @FXML
    private Circle dirty_crl;

    @FXML
    private Circle check_out_crl;

    @FXML
    void add_btn(ActionEvent event) {
        // TODO: get user role
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Room> roomList = new ArrayList<>();
        roomList.add(new Room("r_number", "r_type", 203, SE.DIRTY));
        roomList.add(new Room("r_number1", "r_type1", 49, SE.DIRTY));
        roomList.add(new Room("r_number2", "r_type2", 89, SE.DIRTY));
        roomList.add(new Room("r_number3", "r_type3", 15, SE.DIRTY));
        
        for (Room room : roomList) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            Pane p = null;
            try {
                p = fxmlLoader.load(getClass().getResource("room_obj.fxml").openStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            Label l = (Label) p.lookup("#number_label");
            if(l != null) l.setText(room.getR_number());
            Label l1 = (Label) p.lookup("#type_l");
            if(l1 != null)l1.setText(room.getR_type());
            Label l2 = (Label) p.lookup("#client_name_l");
            if(l2 != null)l2.setText("value");
            
            vbox_room.getChildren().add(p);
        }
    }

}
