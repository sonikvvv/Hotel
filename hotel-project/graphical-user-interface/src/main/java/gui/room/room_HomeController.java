package gui.room;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import base_classes.classes.Clients;
import base_classes.classes.Room;
import base_classes.classes.User;
import base_classes.classes.emuns.SE;
import base_classes.classes.emuns.URE;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import logic.DecodeOperation;
import logic.OperationType;
import logic.operations.RoomOperations;
import logic.operations.UserOperations;

public class Room_HomeController implements Initializable {

    @FXML
    private Button add_btn;

    @FXML
    private AnchorPane rooms_pane = new AnchorPane();

    @FXML
    private TableView<Room> room_table;

    @FXML
    private TableColumn<Room, String> status_col;

    @FXML
    private TableColumn<Room, Integer> number_col;

    @FXML
    private TableColumn<Room, String> client_name_col;

    @FXML
    private TableColumn<Room, String> room_type_col;

    private ObservableList<Room> activ = FXCollections.observableArrayList();

    private static final Logger LOGGER = LogManager.getLogger(Room_HomeController.class);

    @FXML
    void add_btn(ActionEvent event) {
        LOGGER.info("User clicked add room.");
        LOGGER.debug("Starting add room.");
        try {
            Stage st = new Stage();
            Scene sc = new Scene(FXMLLoader.load(getClass().getResource("add_room.fxml")));
            st.setScene(sc);
            st.show();
            LOGGER.debug("Room view scene loaded succesfuly.");
        } catch (Exception e) {
            LOGGER.error("Loading exeption occured -> {}", e);
        }
    }

    @FXML
    void keyPressed(KeyEvent event) {
        LOGGER.info("User pressed key -> {}", event.getCode());
        if (event.getCode() == KeyCode.F7) { //TODO: make dirty after check out
            Room clean = room_table.getSelectionModel().getSelectedItem();
            LOGGER.debug("Room to clean -> {}", clean);
            if (clean.getR_status() == SE.DIRTY) {
                int index = room_table.getItems().indexOf(clean);
                clean.setR_status(SE.FREE);
                room_table.getItems().set(index, clean);
                DecodeOperation.decodeLogicOperation(OperationType.SAVE_OR_UPDATE, clean, null);
            }
        }
    }

    @FXML
    void openRoomView(MouseEvent event) {
        LOGGER.info("User clicked on room.");
        LOGGER.debug("Starting open room view scene.");
        if (event.getClickCount() == 2 && activ.size() != 0){
            try {
                Room tmp = room_table.getSelectionModel().getSelectedItem();
                RoomOperations.addToTemporal((tmp != null) ? tmp : null);

                if (tmp != null) {
                    try {
                        Stage st = new Stage();
                        Scene sc = new Scene(FXMLLoader.load(getClass().getResource("room_view.fxml")));
                        st.setScene(sc);
                        st.show();
                        LOGGER.debug("Room view scene loaded succesfuly.");
                    } catch (Exception e) {
                        LOGGER.error("Loading exeption occured -> {}", e);
                    }
                }
            } catch (Exception e) {
                LOGGER.error("Loading exeption occured -> {}", e);
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LOGGER.debug("Starting initializing");
        User user_now = UserOperations.getUser_now().get(0);

        if (user_now.getUser_role() == URE.RECEPTIONIST) {
            add_btn.setVisible(false);
            add_btn.setDisable(true);
            LOGGER.debug("Hiding the add button from receptionists.");
        }
        
        number_col.setCellValueFactory(new PropertyValueFactory<>("r_number"));
        room_type_col.setCellValueFactory(new PropertyValueFactory<>("r_type"));
        status_col.setCellValueFactory(
            new Callback<TableColumn.CellDataFeatures<Room, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(CellDataFeatures<Room, String> param) {
                    return new SimpleStringProperty(param.getValue().getR_status().toString());
                }
        });

        client_name_col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Room,String>,ObservableValue<String>>(){

            @Override
            public ObservableValue<String> call(CellDataFeatures<Room, String> param) {
                List<Clients> clientL = param.getValue().getClients();
                if (clientL != null && clientL.size() != 0) {
                    String client_name = param.getValue().getClients().get(0).getC_name();
                    if (client_name.length() != 0)
                        return new SimpleStringProperty(client_name);
                }
                return new SimpleStringProperty();
            }
            
        });

        List<?> result = DecodeOperation.decodeLogicOperation(OperationType.GET_ROOMS, null, null);
        if(result != null && result.size() != 0) {
            for (Object object : result) {
                Room tmp = (Room) object;
                activ.add(tmp);
            }
        }

        room_table.getItems().setAll(activ);

    }

}
