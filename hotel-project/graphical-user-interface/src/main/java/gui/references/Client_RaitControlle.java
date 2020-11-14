package gui.references;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import base_classes.classes.Clients;
import base_classes.classes.Country;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class Client_RaitControlle implements Initializable {

    @FXML
    private AnchorPane client_rait_pane;

    @FXML
    private TableView<Clients> client_rait_table;

    @FXML
    private TableColumn<Clients, Integer> number_col;

    @FXML
    private TableColumn<Clients, String> name_col;

    @FXML
    private TableColumn<Clients, ?> raiting_col;

    private ObservableList<Clients> activ = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Country b1 = new Country("BG");
        Country b2 = new Country("testivile");
        Clients cl = new Clients("ves", new java.sql.Date(1999, 04, 18), false, "1516171819", new Date(2013, 5, 10), "",
                b1, "", "");
        cl.getClient_country().setCountry_id(1);
        activ.add(cl);
        activ.add(new Clients("tes", new java.sql.Date(1999, 04, 19), false, "1516178819", new Date(2013, 5, 11), "",
                b2, "", ""));

        number_col.setCellValueFactory(new PropertyValueFactory<>("client_id"));
        name_col.setCellValueFactory(new PropertyValueFactory<>("client_name"));

        client_rait_table.getItems().setAll(activ);
    }

}
