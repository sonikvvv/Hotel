package gui.references;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import logic.OperationType;

@SuppressWarnings("rawtypes")
public class ReferencesHomeController {

    @FXML
    private ComboBox<String> recep_choice;

    @FXML
    private TableView<?> table;

    @FXML
    private TableColumn<?, ?> column;

    @FXML
    private DatePicker toDate;

    @FXML
    private DatePicker fromDate;

    public void setItems() {
        recep_choice.getItems().setAll("Test1", "Test2", "Test3");
    }

    @FXML
    void comboBox(ActionEvent event) {
        setItems();
    }

    @FXML
    void RoomRaiting(ActionEvent event) {
        //ObservableList<String> s = FXCollections.observableArrayList("Test1", "Test2", "Test3");
        //TODO: get receptionist names and set them here
        recep_choice.getItems().setAll("Test1", "Test2", "Test3");
    }

    @FXML
    void clientInfoBtn(ActionEvent event) {
        
        LocalDate fromD = fromDate.getValue();
        LocalDate toD = toDate.getValue();
        
        List<String> data = new ArrayList<>();
        data.add(fromD.toString());
        data.add(toD.toString());

        //List res = DecodeLogicOperation.decodeLogicOperation(OperationType.CLIENT_INFO, null, data);
        
        //TODO: parse and visialize
        
            
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

}

    