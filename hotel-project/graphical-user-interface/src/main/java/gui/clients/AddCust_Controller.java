package gui.clients;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import base_classes.classes.Clients;
import base_classes.classes.Country;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import logic.DecodeOperation;
import logic.OperationType;

public class AddCust_Controller implements Initializable {

    @FXML
    private ComboBox<String> addcust_sex;

    private ObservableList<String> polove = FXCollections.observableArrayList("Male", "Female");

    @FXML
    private TextField addcust_name;

    @FXML
    private TextField addcust_country;

    @FXML
    private TextField addcust_passportnumber;

    @FXML
    private TextField addcust_carnumber;

    @FXML
    private TextArea addcust_note;

    @FXML
    private DatePicker addcust_passportdate;

    @FXML
    private DatePicker addcust_dateofbirth;

    private Clients client = null;

    public void setClient(Clients client) {
        this.client = client;

        addcust_name.setText(client.getC_name());
        addcust_country.setText(client.getCountry().getCountry_name());
        addcust_passportnumber.setText(client.getC_passport_number());
        addcust_carnumber.setText(client.getC_car_number());
        addcust_note.setText(client.getC_note());
        addcust_passportdate.setValue(client.getC_passport_date());
        addcust_dateofbirth.setValue(client.getC_bd());
        addcust_sex.getSelectionModel().select(client.getC_sex() == "M"? polove.get(0):polove.get(1));
    }

    public Clients getClient() {
        return client;
    }

    @FXML
    public void AddCustomer(ActionEvent event) {
        LocalDate DateOfBirth = addcust_dateofbirth.getValue();
        LocalDate PassportDate = addcust_passportdate.getValue();
        boolean sex;

        if (addcust_sex.getValue() == "Male") {
            sex = true;
        } else {
            sex = false;
        }

        String Name = addcust_name.getText();
        String Country = addcust_country.getText();
        String PassportNumber = addcust_passportnumber.getText();
        String CarNumber = addcust_carnumber.getText();
        String Note = addcust_note.getText();

        if (Name.length() == 0 || Country.length() == 0 || PassportNumber.length() == 0 || CarNumber.length() == 0
                || DateOfBirth == null || PassportDate == null || addcust_sex.getValue().length() == 0) {

            Alert al = new Alert(AlertType.WARNING, "Don't leave empty fields!");
            al.showAndWait();
        } else {
            if (client == null) {
                client = new Clients(Name, DateOfBirth, sex, PassportNumber, PassportDate, CarNumber,
                        new Country(Country), Note);
                DecodeOperation.decodeLogicOperation(OperationType.SAVE, client, null);
            } else {
                client.setC_name(Name);
                client.setC_bd(DateOfBirth);
                client.setC_sex(sex);
                client.setC_passport_number(PassportNumber);
                client.setC_passport_date(PassportDate);
                client.setC_car_number(CarNumber);
                client.setC_note(Note);
                if (!Country.equalsIgnoreCase(client.getCountry().getCountry_name()))
                    client.setCountry(new Country(Country));
                
                DecodeOperation.decodeLogicOperation(OperationType.UPDATE, client, null);
            }
        }

        Stage st = (Stage) addcust_carnumber.getScene().getWindow();
        st.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addcust_sex.setItems(polove);
        if (client != null){
            addcust_name.setText(client.getC_name());
            addcust_country.setText(client.getCountry().getCountry_name());
            addcust_passportnumber.setText(client.getC_passport_number());
            addcust_carnumber.setText(client.getC_car_number());
            addcust_note.setText(client.getC_note());
            addcust_passportdate.setValue(client.getC_passport_date());
            addcust_dateofbirth.setValue(client.getC_bd());
            addcust_sex.getSelectionModel().select(client.getC_sex() == "M" ? polove.get(0) : polove.get(1));
        }
    }

}
