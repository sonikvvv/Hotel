package gui.clients;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import base_classes.classes.Clients;
import base_classes.classes.Country;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import logic.DecodeOperation;
import logic.OperationType;

public class AddCust_Controller{


    @FXML
    private ComboBox<String> addcust_sex;
    private ObservableList<String> polove = FXCollections.observableArrayList("Male", "Female");


@FXML
public void initialize() 
{
    addcust_sex.setItems(polove);
}


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



    
public void AddCustomer()
{
    LocalDate DateOfBirth = addcust_dateofbirth.getValue();
    LocalDate PassportDate = addcust_passportdate.getValue();
    boolean sex;

    if(addcust_sex.getValue() == "Male")
    {
        sex = true;
    }
    else
    {
        sex = false;     
    }


        String Name = addcust_name.getText();
        String Country = addcust_country.getText();
        String PassportNumber = addcust_passportnumber.getText();
        String CarNumber = addcust_carnumber.getText();
        String Note = addcust_note.getText();

        if (Name.length() == 0 || Country.length()  == 0  || PassportNumber.length() == 0  || CarNumber.length() == 0  || DateOfBirth == null || PassportDate == null || addcust_sex.getValue().length() == 0) 
        {
            Alert al = new Alert(AlertType.WARNING, "Don't leave empty fields!");
            al.showAndWait();
        }else 
        {
            List<String> t = new ArrayList<>();

            Clients klient = new Clients(Name, DateOfBirth, sex, PassportNumber, PassportDate, CarNumber, new Country(Country),Note);
            //TODO Save object
            List res = DecodeOperation.decodeLogicOperation(OperationType.SAVE_OR_UPDATE, klient, null);
        }

   

}




}
