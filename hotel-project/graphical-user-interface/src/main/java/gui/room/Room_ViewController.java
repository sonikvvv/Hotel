package gui.room;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import base_classes.classes.AdditServices;
import base_classes.classes.ClientUsedServices;
import base_classes.classes.Clients;
import base_classes.classes.Country;
import base_classes.classes.Room;
import base_classes.classes.ServiceCategory;
import base_classes.classes.emuns.SE;
import base_classes.classes.emuns.ServiceType;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

public class Room_ViewController implements Initializable {

    @FXML
    private Label room_l;

    @FXML
    private VBox clients_vbox;

    @FXML
    private TableView<ClientUsedServices> ads_table;

    @FXML
    private TableColumn<ClientUsedServices, String> paid_col;

    @FXML
    private TableColumn<ClientUsedServices, String> service_name_col;

    @FXML
    private TableColumn<ClientUsedServices, LocalDate> date_col;

    @FXML
    private TableColumn<ClientUsedServices, Number> price_col;

    @FXML
    private TableColumn<ClientUsedServices, Number> quantity_col;

    @FXML
    private TableColumn<ClientUsedServices, String> note_col;

    private ObservableList<ClientUsedServices> activ = FXCollections.observableArrayList();

    @FXML
    void ads_add(ActionEvent event) {
        // TODO:
    }

    @FXML
    void clients_add(ActionEvent event) {
        // TODO:
    }

    @FXML
    void pay(MouseEvent event) {
        Alert al = new Alert(AlertType.CONFIRMATION);
        al.setHeaderText("Information Alert");
        String s = "This is an example of JavaFX 8 Dialogs... ";
        al.setContentText(s);
        Optional<ButtonType> result = al.showAndWait();
        ClientUsedServices tmp = null;

        if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
            System.out.println("OK");
            ads_table.getSelectionModel().getSelectedItem().setPaid(true);
            tmp = ads_table.getSelectionModel().getSelectedItem();
            ads_table.getItems().setAll(activ);
        }

        System.out.println(tmp);

        //TODO: update the object in db
        
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        AdditServices as = new AdditServices("title", new ServiceCategory("category_title", ServiceType.PROSITIVE), 13);
        AdditServices as1 = new AdditServices("title", new ServiceCategory("category_title", ServiceType.PROSITIVE),
                15);
        AdditServices as2 = new AdditServices("title", new ServiceCategory("category_title", ServiceType.PROSITIVE),
                16);

        ClientUsedServices cus = new ClientUsedServices(as, 1, "note");
        ClientUsedServices cus1 = new ClientUsedServices(as1, 1, "note");
        ClientUsedServices cus2 = new ClientUsedServices(as2, 1, "note");

        Clients c = new Clients("Amina Jakubowski", LocalDate.now(), false, "30506", LocalDate.of(2020, 11, 10),
                "car_number", new Country("Testivile"), "client_note", "vaucher", 1);
        Clients c1 = new Clients("Annamarie Haag", LocalDate.now(), true, "67547", LocalDate.of(2020, 11, 15),
                "car_number", new Country("Falkland Islands (Malvinas)"), "client_note", "vaucher", 2);

        c.addToUsedServices(cus);
        c.addToUsedServices(cus1);
        c1.addToUsedServices(cus);
        c1.addToUsedServices(cus2);

        Room r = new Room("r_number", "r_type", 300, SE.OCCUPIED);
        r.addToClients(c);
        r.addToClients(c1);
        
        String css = getClass().getResource("../style.css").toExternalForm();
        room_l.setText(r.getR_number());
        List<Clients> clList = r.getClients();
        List<Button> bList = new ArrayList<>();
        clients_vbox.getChildren().clear();
        for (Clients clients : clList) {
            Button tmp = new Button(clients.getC_name());
            tmp.getStyleClass().add("bttn");
            tmp.getStylesheets().add(css);
            tmp.setPrefWidth(352);
            tmp.setPrefHeight(50);
            
            tmp.setOnMouseClicked(e -> {
                ads_table.getItems().clear();
                activ.clear();
                if (e.getClickCount() == 1) {
                    paid_col.setCellValueFactory(
                        new Callback<TableColumn.CellDataFeatures<ClientUsedServices, String>, ObservableValue<String>>() {
                            @Override
                            public ObservableValue<String> call(
                                    CellDataFeatures<ClientUsedServices, String> param) {
                                return new SimpleStringProperty(
                                        (param.getValue().getPaid() == true) ? "YES" : "NO");
                            }

                        });
                    service_name_col.setCellValueFactory(
                        new Callback<TableColumn.CellDataFeatures<ClientUsedServices, String>, ObservableValue<String>>() {
                            @Override
                            public ObservableValue<String> call(
                                    CellDataFeatures<ClientUsedServices, String> param) {
                                return new SimpleStringProperty(param.getValue().getAddit_service().getTitle());
                            }

                        });
                    date_col.setCellValueFactory(new PropertyValueFactory<>("purchase_date"));
                    quantity_col.setCellValueFactory(new PropertyValueFactory<>("quantity"));
                    note_col.setCellValueFactory(new PropertyValueFactory<>("note"));
                    price_col.setCellValueFactory(new PropertyValueFactory<>("total"));

                    activ.addAll(clients.getCuds());
                    ads_table.getItems().addAll(activ);
                } else {
                    // TODO: load client info page
                }

            });
            bList.add(tmp);
        }

        clients_vbox.getChildren().addAll(bList);

    }

}
