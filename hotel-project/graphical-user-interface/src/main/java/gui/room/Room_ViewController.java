package gui.room;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import base_classes.classes.ClientUsedServices;
import base_classes.classes.Clients;
import base_classes.classes.Room;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import logic.operations.RoomOperations;

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
        try
        {
        Stage stage = new Stage();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("../clients/AddCust.fxml")));
        stage.setScene(scene);
        stage.show();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        // TODO:da zaredi nova scena i fxml fail
        
    }

    @FXML
    void pay(MouseEvent event) {
        if (event.getClickCount() == 2 && activ.size() != 0) {
            Alert al = new Alert(AlertType.CONFIRMATION);
            al.setContentText("Pay additional services?");
            Optional<ButtonType> result = al.showAndWait();
            ClientUsedServices tmp = null;

            if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
                //System.out.println("OK");
                ads_table.getSelectionModel().getSelectedItem().setPaid(true);
                tmp = ads_table.getSelectionModel().getSelectedItem();
                ads_table.getItems().setAll(activ);
            }
            //TODO: update the object in db
        }        
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Room r = RoomOperations.getTemporal().get(0);
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
        }catch(Exception e){
            e.printStackTrace();
        }

    }

}
