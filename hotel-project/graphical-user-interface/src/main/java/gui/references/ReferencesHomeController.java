package gui.references;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import logic.OperationType;

@SuppressWarnings("rawtypes")
public class ReferencesHomeController implements Initializable {

    @FXML
    private ComboBox<String> recep_choice;

    @FXML
    private DatePicker toDate;

    @FXML
    private DatePicker fromDate;

    @FXML
    private AnchorPane reference_pane;

    public void setItems() {
        
    }

    @FXML
    void comboBox(ActionEvent event) {
        setItems();
    }

    @FXML
    void RoomRaiting(ActionEvent event) {
        
    }

    @FXML
    void clientInfoBtn(ActionEvent event) {

        LocalDate fromD = fromDate.getValue();
        LocalDate toD = toDate.getValue();

        List<String> data = new ArrayList<>();
        data.add(fromD.toString());
        data.add(toD.toString());
        AnchorPane next = null;

        // List res =
        // DecodeLogicOperation.decodeLogicOperation(OperationType.CLIENT_INFO, null,
        // data);
        try {
            next = FXMLLoader.load(getClass().getResource("client_info.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        reference_pane.getChildren().setAll(next);

    }

    @FXML
    void clientRaiting(ActionEvent event) {

    }

    @FXML
    void createdReservations(ActionEvent event) {

    }

    @FXML
    void createdReservationsRecep(ActionEvent event) {

    }

    @FXML
    void usedServices(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        recep_choice.getItems().setAll("Test1", "Test2", "Test3");

    }

}

    