package gui.clients;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import base_classes.classes.Clients;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.util.Callback;
import logic.DecodeOperation;
import logic.OperationType;

public class Clients_HomeController implements Initializable {

    @FXML
    private TextField search_txt;

    @FXML
    private TableView<Clients> clients_table;

    @FXML
    private TableColumn<Clients, String> number_col;

    @FXML
    private TableColumn<Clients, String> name_col;

    @FXML
    private TableColumn<Clients, String> country_col;

    @FXML
    private TableColumn<Clients, String> birth_col;

    @FXML
    private TableColumn<Clients, String> passsport_number_col;

    @FXML
    private TableColumn<Clients, String> passport_date_col;

    @FXML
    private TableColumn<Clients, String> vaucher_col;

    private ObservableList<Clients> clientsList = FXCollections.observableArrayList();
    
    private ObservableList<Clients> activ = FXCollections.observableArrayList();

    private static final Logger LOGGER = LogManager.getLogger(Clients_HomeController.class);

    @FXML
    void searchChanged(KeyEvent event) {
        clients_table.getItems().clear();
        clientsList.clear();
        String search = search_txt.getText();
        LOGGER.debug("Starting search with data -> {}", search_txt);

        if (search.length() != 0 && !search.isBlank()) {
            for (Clients clients : activ) {
                String client_name = clients.getC_name().toLowerCase();
                if (client_name.indexOf(search.toLowerCase()) > -1){
                    clientsList.add(clients);
                }
            }

            LOGGER.debug("Result -> {}", clientsList);
            clients_table.getItems().setAll(clientsList);
        } else if (search.length() == 0){
            clients_table.getItems().setAll(activ);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LOGGER.debug("Starting initialize.");
        number_col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Clients, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(CellDataFeatures<Clients, String> param) {
                return new SimpleStringProperty(param.getValue().getC_id() + "");
            }
            
        });

        name_col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Clients,String>,ObservableValue<String>>(){

            @Override
            public ObservableValue<String> call(CellDataFeatures<Clients, String> param) {
                return new SimpleStringProperty(param.getValue().getC_name());
            }
            
        });

        country_col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Clients,String>,ObservableValue<String>>(){

            @Override
            public ObservableValue<String> call(CellDataFeatures<Clients, String> param) {
                return new SimpleStringProperty(param.getValue().getCountry().getCountry_name());
            }
            
        });

        birth_col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Clients,String>,ObservableValue<String>>(){

            @Override
            public ObservableValue<String> call(CellDataFeatures<Clients, String> param) {
                return new SimpleStringProperty(param.getValue().getC_bd().toString());
            }
            
        });

        passsport_number_col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Clients,String>,ObservableValue<String>>(){

            @Override
            public ObservableValue<String> call(CellDataFeatures<Clients, String> param) {
                return new SimpleStringProperty(param.getValue().getC_passport_number());
            }
            
        });
        passport_date_col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Clients,String>,ObservableValue<String>>(){

            @Override
            public ObservableValue<String> call(CellDataFeatures<Clients, String> param) {
                return new SimpleStringProperty(param.getValue().getC_passport_date().toString());
            }
            
        });
        vaucher_col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Clients,String>,ObservableValue<String>>(){

            @Override
            public ObservableValue<String> call(CellDataFeatures<Clients, String> param) {
                return new SimpleStringProperty(param.getValue().getVaucher());
            }
            
        });

        List<?> result = DecodeOperation.decodeLogicOperation(OperationType.GET_CLIENTS, null, null);
        if (result != null && result.size() != 0){
            for (Object object : result) {
                activ.add((Clients) object);
            }
            clients_table.getItems().setAll(activ);
        }

    }

}
