package gui.reservations;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

public class Add_To_Room implements Initializable {

    @FXML
    private ComboBox<String> toom_type_cb;

    @FXML
    private ComboBox<String> toom_number_cb;

    @FXML
    void save(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub

    }

}
