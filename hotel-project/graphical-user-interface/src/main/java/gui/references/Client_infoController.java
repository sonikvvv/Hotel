package gui.references;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import base_classes.classes.Clients;
import base_classes.classes.Country;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;

public class Client_infoController implements Initializable {

    @FXML
    private TableView<Clients> clients_table;

    @FXML
    private TableColumn<Clients, Integer> number_col;

    @FXML
    private TableColumn<Clients, String> name_col;

    @FXML
    private TableColumn<Clients, String> country_col;

    @FXML
    private TableColumn<Clients, Date> birth_col;

    @FXML
    private TableColumn<Clients, String> passsport_number_col;

    @FXML
    private TableColumn<Clients, Date> passport_date_col;

    @FXML
    private TableColumn<Clients, String> vaucher_col;

    private ObservableList<Clients> activ = FXCollections.observableArrayList();

    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Country b1 = new Country("BG");
        Country b2 = new Country("testivile");
        Clients cl = new Clients("ves", new java.sql.Date(1999, 04, 18), false, "1516171819", new Date(2013, 5, 10), "", b1, "",
                "");
        cl.getClient_country().setCountry_id(1);
        activ.add(cl);
        activ.add(new Clients("tes", new java.sql.Date(1999, 04, 19), false, "1516178819", new Date(2013, 5, 11), "", b2, "", ""));
        
        number_col.setCellValueFactory(new PropertyValueFactory < Clients, Integer > ("client_id"));
        name_col.setCellValueFactory(new PropertyValueFactory<Clients, String>("client_name"));
        birth_col.setCellValueFactory(new PropertyValueFactory<Clients, Date>("client_birth_date"));
        passsport_number_col.setCellValueFactory(new PropertyValueFactory<Clients, String>("client_passport_number"));
        passport_date_col.setCellValueFactory(new PropertyValueFactory<Clients, Date>("client_passport_date"));
        vaucher_col.setCellValueFactory(new PropertyValueFactory<Clients, String>("vaucher"));
        country_col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Clients, String>,ObservableValue<String>>(){

            @Override
            public ObservableValue<String> call(CellDataFeatures<Clients, String> param) {
                return new SimpleStringProperty( param.getValue().getCoutryName());
            }
            
        });

        clients_table.getItems().addAll(activ);
    }

}
