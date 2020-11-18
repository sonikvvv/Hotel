package gui.reservations;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class Reservations_HomeController implements Initializable {

    @FXML
    private DatePicker fromDate;

    @FXML
    private DatePicker toDate;

    @FXML
    private VBox vbox_pane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Pane n = null;
        try {
            n = FXMLLoader.load(getClass().getResource("reserv_obj.fxml"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        

        vbox_pane.getChildren().add(n);

    }

}
