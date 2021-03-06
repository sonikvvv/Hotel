package gui.hotel;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import base_classes.classes.Hotel;
import base_classes.classes.User;
import base_classes.classes.emuns.URE;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import logic.DecodeOperation;
import logic.OperationType;
import logic.operations.UserOperations;

public class Hotel_HomeCotroller implements Initializable {

    @FXML
    private Button add_hotel_bttn;
    
    @FXML
    private TableView<Hotel> hotel_table;

    @FXML
    private TableColumn<Hotel, String> hotel_name_col;

    private static ObservableList<Hotel> activ = FXCollections.observableArrayList();

    private static final Logger LOGGER = LogManager.getLogger(Hotel_HomeCotroller.class);

    private void load() {
        LOGGER.debug("Starting load method.");
        activ.clear();
        
        List<?> result = DecodeOperation.decodeLogicOperation(OperationType.GET_HOTEL, null, null);
        if (result != null && result.size() != 0) {
            for (Object object : result) {
                activ.add((Hotel) object);
            }
        }

        hotel_table.getItems().setAll(activ);
    }

    @FXML
    void addHotel(ActionEvent event) {
        try {
            LOGGER.info("User clicked add hotel button.");
            LOGGER.debug("Starting add new hotel.");
            URL location = Hotel_HomeCotroller.class.getResource("add_hotel.fxml");
            FXMLLoader loader = new FXMLLoader(location);
            Parent parent = loader.load();
            Stage st = new Stage();
            Scene sc;
            sc = new Scene(parent);
            st.getIcons().add(new Image(getClass().getResourceAsStream("../icons/logo3.png")));
            st.setTitle("Add hotel");
            st.setScene(sc);
            st.showAndWait();
            load();
            LOGGER.debug("Add hotel scene loaded succesfuly.");
        } catch (Exception e) {
            LOGGER.error("Loading exeption occured -> {}", e);
        }
    }

    @FXML
    void editHotel(MouseEvent event) {
        if (event.getClickCount() == 2) {
            LOGGER.debug("Starting edit hotel.");
            
            try {
                URL location = Hotel_HomeCotroller.class.getResource("add_hotel.fxml");
                FXMLLoader loader = new FXMLLoader(location);
                Parent parent = loader.load();
                Add_HotelController add_hotel = loader.getController();

                Hotel to_edit = hotel_table.getSelectionModel().getSelectedItem();
                if (to_edit != null) {
                    LOGGER.debug("Hotel for editing: {}", to_edit);
                    add_hotel.setHotel(to_edit);
                }

                Stage st = new Stage();
                Scene sc;
                sc = new Scene(parent);
                st.getIcons().add(new Image(getClass().getResourceAsStream("../icons/logo3.png")));
                st.setTitle("Edit hotel");
                st.setScene(sc);
                st.showAndWait();
                load();
                LOGGER.debug("Add hotel scene loaded succesfuly.");
            } catch (Exception e) {
                LOGGER.error("Loading exeption occured -> {}", e);
            }
        }
    }

    @FXML
    void keyPressed(KeyEvent event) {
        LOGGER.info("User pressed key -> {}", event.getCode());
        LOGGER.debug("Starting key pressed.");
        if (event.getCode() == KeyCode.DELETE) {

            Alert al = new Alert(AlertType.CONFIRMATION);
            al.setContentText("Delete this hotel?");
            Optional<ButtonType> result = al.showAndWait();
            Hotel to_delete;

            if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
                to_delete = hotel_table.getSelectionModel().getSelectedItem();
                if (to_delete != null) {
                    LOGGER.debug("Hotel for deleting: {}", to_delete);
                    hotel_table.getItems().remove(to_delete);
                    DecodeOperation.decodeLogicOperation(OperationType.DELETE, to_delete, null);
                }
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LOGGER.debug("Starting initialize.");
        User user_now = UserOperations.getUser_now().get(0);
        activ.clear();
        hotel_name_col.setCellValueFactory(new PropertyValueFactory<>("hotel_name"));

        if (user_now.getUser_role() == URE.RECEPTIONIST || user_now.getUser_role() == URE.MANAGER){
            add_hotel_bttn.setVisible(false);
        }

        load();
    }

}
