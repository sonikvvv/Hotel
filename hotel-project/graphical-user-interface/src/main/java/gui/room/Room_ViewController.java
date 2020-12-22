package gui.room;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import base_classes.classes.ClientUsedServices;
import base_classes.classes.Clients;
import base_classes.classes.Room;
import base_classes.classes.User;
import base_classes.classes.emuns.URE;
import gui.additional_services.Services_AddController;
import gui.clients.AddCust_Controller;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
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
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import logic.DecodeOperation;
import logic.OperationType;
import logic.operations.RoomOperations;
import logic.operations.UserOperations;

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
    private TableColumn<ClientUsedServices, Number> total_col;

    @FXML
    private TableColumn<ClientUsedServices, Number> quantity_col;

    @FXML
    private TableColumn<ClientUsedServices, String> note_col;

    private ObservableList<ClientUsedServices> activ = FXCollections.observableArrayList();

    private static final Logger LOGGER = LogManager.getLogger(Room_ViewController.class);

    private List<Clients> clList = null;
    private String client_name = "";
    private Room r = null;

    private void load() {
        try {
            LOGGER.debug("Starting load.");
            r = RoomOperations.getTemporal().get(0);
            String css = getClass().getResource("../style.css").toExternalForm();
            room_l.setText(r.getR_number());
            clList = r.getClients();
            List<Button> bList = new ArrayList<>();

            LOGGER.debug("Clients to add in the vbox -> {}", clList.size());

            clients_vbox.getChildren().clear();
            for (Clients clients : clList) {
                Button tmp = new Button(clients.getC_name());
                tmp.getStyleClass().add("controls");
                tmp.getStylesheets().add(css);
                tmp.setPrefWidth(352);
                tmp.setPrefHeight(50);

                tmp.setOnMouseClicked(e -> {
                    LOGGER.info("User clicked client button.");
                    LOGGER.debug("Starting load client't additional services.");
                    ads_table.getItems().clear();
                    activ.clear();
                    if (e.getClickCount() == 1) {
                        client_name = ((Button) e.getSource()).getText();
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

                        price_col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ClientUsedServices,Number>,ObservableValue<Number>>(){

                            @Override
                            public ObservableValue<Number> call(CellDataFeatures<ClientUsedServices, Number> param) {
                                return new SimpleDoubleProperty(param.getValue().getAddit_service().getPrice());
                            }
                            
                        });
                        date_col.setCellValueFactory(new PropertyValueFactory<>("purchase_date"));
                        quantity_col.setCellValueFactory(new PropertyValueFactory<>("quantity"));
                        note_col.setCellValueFactory(new PropertyValueFactory<>("note"));
                        total_col.setCellValueFactory(new PropertyValueFactory<>("total"));

                        activ.addAll(clients.getCuds());
                        ads_table.getItems().addAll(activ);
                    } else if(e.getClickCount() == 2) {
                        try {
                            URL location = this.getClass().getResource("../clients/AddCust.fxml");
                            FXMLLoader loader = new FXMLLoader(location);
                            Clients to_edit = null;

                            Button bttn = (Button)e.getSource();
                            for (Clients cl : clList) {
                                if (cl.getC_name().equalsIgnoreCase(bttn.getText())){
                                    to_edit = cl;
                                }
                            }

                            Parent parent = loader.load();
                            AddCust_Controller cust = loader.getController();
                            
                            if (to_edit != null) {
                                LOGGER.info("Updating user: {}", to_edit);
                                cust.setClient(to_edit);
                            }

                            Stage st = new Stage();
                            Scene sc;
                            sc = new Scene(parent);
                            st.setTitle("Edit client");
                            st.getIcons().add(new Image(getClass().getResourceAsStream("../icons/logo3.png")));
                            st.setScene(sc);
                            st.showAndWait();

                            load();
                            LOGGER.debug("Room view scene loaded succesfuly.");
                        } catch (Exception ex) {
                            LOGGER.error("Loading exeption occured -> {}", ex);
                        }
                    }

                });
                tmp.setOnKeyPressed(e -> {
                    LOGGER.info("User pressed key -> {}", e.getCode());
                    LOGGER.debug("Starting check out user");
                    if (e.getCode() == KeyCode.DELETE) {
                        Button to_delete = (Button) e.getSource();
                        List<String> data = new ArrayList<>();
                        data.add(r.getR_id() + "");
                        data.add(to_delete.getText());

                        clients_vbox.getChildren().remove(to_delete);
                        if (clients_vbox.getChildren().size() == 0) {
                            data.add("flag");
                        }

                        ads_table.getItems().clear();
                        LOGGER.debug("Client for check out -> {}", to_delete);
                        DecodeOperation.decodeLogicOperation(OperationType.CHECKOUT, null, data);
                    }
                });
                bList.add(tmp);
            }

            clients_vbox.getChildren().addAll(bList);
        } catch (Exception e) {
            LOGGER.error("Loading exeption occured -> {}", e);
        }
    }

    @FXML
    void ads_add(ActionEvent event) {
        try {
            LOGGER.info("User clicked add service button.");
            LOGGER.debug("Starting add service to room.");
            Services_AddController.flag = true;
            URL location = this.getClass().getResource("../additional_services/services_add.fxml");
            FXMLLoader loader = new FXMLLoader(location);
            Parent parent = loader.load();

            Clients client = null;
            for (Clients cl : clList) {
                if (cl.getC_name().equalsIgnoreCase(client_name)) {
                    client = cl;
                }
            }

            if (client != null && client.getC_name().length() != 0) {
                Services_AddController add_service = loader.getController();

                Stage st = new Stage();
                Scene sc;
                sc = new Scene(parent);
                st.setTitle("Add service");
                st.getIcons().add(new Image(getClass().getResourceAsStream("../icons/logo3.png")));
                st.setScene(sc);
                st.showAndWait();

                ClientUsedServices cus = add_service.getCus();
                if (cus != null) {
                    client.addToUsedServices(cus);
                    
                    LOGGER.debug("Added service to client's list -> {}", cus);
                    DecodeOperation.decodeLogicOperation(OperationType.UPDATE, client, null);
                }
            }
            else {
                Alert al = new Alert(AlertType.WARNING, "You need to select a client in the room first!");
                al.showAndWait();
            }

            load();
            LOGGER.debug("Add service scene loaded succesfuly.");
            Services_AddController.flag = false;
        } catch (Exception e) {
            LOGGER.error("Loading exeption occured -> {}", e);
        }
    }

    @FXML
    void clients_add(ActionEvent event) {
        try {
            LOGGER.info("User clicked add client button.");
            LOGGER.debug("Starting add client to room.");
            URL location = this.getClass().getResource("../clients/AddCust.fxml");
            FXMLLoader loader = new FXMLLoader(location);
            Parent parent = loader.load();

            AddCust_Controller cust = loader.getController();

            Stage st = new Stage();
            Scene sc;
            sc = new Scene(parent);
            st.setTitle("Add client");
            st.getIcons().add(new Image(getClass().getResourceAsStream("../icons/logo3.png")));
            st.setScene(sc);
            st.showAndWait();

            Clients client = cust.getClient();

            if (client != null && client.getC_name().length() != 0) {
                r.addToClients(client);
                DecodeOperation.decodeLogicOperation(OperationType.UPDATE, r, null);
            }

            load();
            LOGGER.debug("Add client scene loaded succesfuly.");
        } catch (Exception e) {
            LOGGER.error("Loading exeption occured -> {}", e);
        }
    }

    @FXML //for table view
    void keyPressed(KeyEvent event) {
        LOGGER.info("User pressed key -> {}", event.getCode());
        LOGGER.debug("Starting key pressed.");
        User user_now = UserOperations.getUser_now().get(0);
        if(user_now.getUser_role() == URE.MANAGER) {
            if (event.getCode() == KeyCode.DELETE){
                Alert al = new Alert(AlertType.CONFIRMATION);
                al.setContentText("Delete this service?");
                Optional<ButtonType> result = al.showAndWait();
                ClientUsedServices to_delete;

                if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
                    to_delete = ads_table.getSelectionModel().getSelectedItem();
                    if (to_delete != null) {
                        LOGGER.debug("Additional service to delete -> {}", to_delete);
                        ads_table.getItems().remove(to_delete);
                        DecodeOperation.decodeLogicOperation(OperationType.DELETE, to_delete, null);
                        load();
                    }
                }
            }
        }
    }

    @FXML
    void pay(MouseEvent event) {
        LOGGER.info("User clicked on additional service.");
        LOGGER.debug("Starting pay additional service.");
        if (event.getClickCount() == 2 && activ.size() != 0) {
            Alert al = new Alert(AlertType.CONFIRMATION);
            al.setContentText("Pay additional services?");
            Optional<ButtonType> result = al.showAndWait();
            ClientUsedServices tmp = null;

            if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
                ads_table.getSelectionModel().getSelectedItem().setPaid(true);
                tmp = ads_table.getSelectionModel().getSelectedItem();
                ads_table.getItems().setAll(activ);
                LOGGER.debug("Payed additional service to save -> {}", tmp);
                DecodeOperation.decodeLogicOperation(OperationType.UPDATE, tmp, null);
            }

        }        
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        load();
    }

}
