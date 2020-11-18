package gui.room;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

public class Room_ViewController {

    @FXML
    private Label room_l;

    @FXML
    private VBox clients_vbox;

    @FXML
    private Button client_btn;

    @FXML
    private TableView<?> ads_table;

    @FXML
    void ads_add(ActionEvent event) {
        //TODO: open new window to add service
    }

    @FXML
    void clients_add(ActionEvent event) {
        // TODO: open client add window
    }

    @FXML
    void fix_btn(ActionEvent event) {

    }

    @FXML
    void move_btn(ActionEvent event) {
        
    }

}
