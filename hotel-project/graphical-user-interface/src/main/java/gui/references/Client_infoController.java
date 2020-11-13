package gui.references;

import java.net.URL;
import java.util.ResourceBundle;

import base_classes.classes.Clients;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
    private TableColumn<Clients, ?> food_col;

    @FXML
    private TableColumn<Clients, ?> checkOut_col;

    @FXML
    private TableColumn<Clients, ?> checkIn_col;

    @FXML
    private TableColumn<Clients, ?> vaucher_col;

    private ObservableList<Clients> activ = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub

    }

}
