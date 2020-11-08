package gui.references;

import java.time.LocalDate;
import java.util.Date;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

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

    private Date getDate(LocalDate date){
        return java.sql.Date.valueOf(date);
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
        Date fromD = getDate(fromDate.getValue());
        //Date toD = getDate(toDate.getValue());
        System.out.println(fromD);
        
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

    