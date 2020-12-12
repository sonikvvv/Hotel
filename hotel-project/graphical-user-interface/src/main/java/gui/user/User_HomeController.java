package gui.user;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import base_classes.classes.Hotel;
import base_classes.classes.User;
import base_classes.classes.emuns.URE;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import logic.DecodeOperation;
import logic.OperationType;
import logic.operations.UserOperations;

public class User_HomeController implements Initializable {

    @FXML
    private Button add_btn;
    
    @FXML
    private TableView<User> user_table;

    @FXML
    private TableColumn<User, String> user_name_col;

    @FXML
    private TableColumn<User, String> role_col;

    @FXML
    private TableColumn<User, String> name_col;

    @FXML
    private TableColumn<User, String> email_col;

    @FXML
    private TableColumn<User, String> phone_n_col;

    @FXML
    private TableColumn<User, String> hotel_col;

    private ObservableList<User> activ = FXCollections.observableArrayList();

    private static final Logger LOGGER = LogManager.getLogger(User_HomeController.class);

    private void load() {
        LOGGER.debug("Starting load method.");
        activ.clear();

        List<?> result = DecodeOperation.decodeLogicOperation(OperationType.GET_USERS, null, null);
        if (result != null && result.size() != 0) {
            for (Object object : result) {
                User tmp = (User) object;
                activ.add(tmp);
            }
        }

        user_table.getItems().setAll(activ);
    }

    @FXML
    void add_user(ActionEvent event) {
        LOGGER.info("User clicked add user button.");
        LOGGER.debug("Starting add user.");
        try {
            URL location;
            if (UserOperations.getUser_now().get(0).getUser_role() == URE.ADMIN ||
                UserOperations.getUser_now().get(0).getUser_role() == URE.OWNER) {
                    location = getClass().getResource("add_user_to_hotel.fxml");
                    LOGGER.debug("Loading add user to hotel fxml.");
            } else {
                location = getClass().getResource("add_user.fxml");
                LOGGER.debug("Loading add user fxml.");
            }

            FXMLLoader loader = new FXMLLoader(location);
            Parent parent = loader.load();

            Stage st = new Stage();
            Scene sc;
            sc = new Scene(parent);
            st.setScene(sc);
            st.showAndWait();

            load();
            LOGGER.debug("Add user scene loaded succesfuly.");
        } catch (Exception e) {
            LOGGER.error("Loading exeption occured -> {}", e);
        }
    }

    @FXML
    void editUser(MouseEvent event) {
        if (event.getClickCount() == 2) {
            User user_now = UserOperations.getUser_now().get(0);
            LOGGER.debug("Starting edit user.");
            if (user_now.getUser_role() != URE.RECEPTIONIST) {
                try {
                    URL location;
                    boolean controller = false;
                    if (UserOperations.getUser_now().get(0).getUser_role() == URE.ADMIN
                            || UserOperations.getUser_now().get(0).getUser_role() == URE.OWNER) {
                        location = getClass().getResource("add_user_to_hotel.fxml");
                        controller = true;
                        LOGGER.debug("Loading add user to hotel fxml.");
                    } else {
                        location = getClass().getResource("add_user.fxml");
                        LOGGER.debug("Loading add user fxml.");
                    }

                    FXMLLoader loader = new FXMLLoader(location);
                    Parent parent = loader.load();

                    Add_UserController add_user;
                    Add_User_To_HotelController add_user_to_hotel;
                    User to_edit;

                    if (controller) {
                        add_user_to_hotel = loader.getController();
                        to_edit = user_table.getSelectionModel().getSelectedItem();
                        if (to_edit != null) {
                            LOGGER.info("Updating user: {}", to_edit);
                            add_user_to_hotel.setUser(to_edit);
                        }
                    } else {
                        add_user = loader.getController();
                        to_edit = user_table.getSelectionModel().getSelectedItem();
                        if (to_edit != null) {
                            LOGGER.info("Updating user: {}", to_edit);
                            add_user.setUser(to_edit);
                        }
                    }

                    Stage st = new Stage();
                    Scene sc;
                    sc = new Scene(parent);
                    st.setScene(sc);
                    st.showAndWait();

                    load();
                    LOGGER.debug("Add user scene loaded succesfuly.");
                } catch (Exception e) {
                    LOGGER.error("Loading exeption occured -> {}", e);
                }
            }
        }
    }

    @FXML
    void keyPressed(KeyEvent event) {
        LOGGER.info("User pressed key -> {}.", event.getCode());
        LOGGER.debug("Starting key pressed.");
        User user_now = UserOperations.getUser_now().get(0);
        
        if (event.getCode() == KeyCode.DELETE) {
            if (user_now.getUser_role() == URE.ADMIN) {
                Alert al = new Alert(AlertType.CONFIRMATION);
                al.setContentText("Delete this user?");
                Optional<ButtonType> result = al.showAndWait();
                User to_delete;

                if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
                    to_delete = user_table.getSelectionModel().getSelectedItem();
                    if (to_delete != null) {
                        LOGGER.info("Deleting user: {}", to_delete);
                        user_table.getItems().remove(to_delete);
                        DecodeOperation.decodeLogicOperation(OperationType.DELETE, to_delete, null);
                    }
                }
            }
        }        
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LOGGER.debug("Starting innitialize.");
        User user_now = UserOperations.getUser_now().get(0);

        if (user_now.getUser_role() == URE.RECEPTIONIST) {
            add_btn.setVisible(false);
            LOGGER.debug("Hiding the add button from receptionists.");
        }

        user_name_col.setCellValueFactory(new PropertyValueFactory<>("user_name"));
        role_col.setCellValueFactory(
            new Callback<TableColumn.CellDataFeatures<User, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(CellDataFeatures<User, String> param) {
                    return new SimpleStringProperty(param.getValue().getUser_role().toString());
                }
        });
        email_col.setCellValueFactory(new PropertyValueFactory<>("email"));
        name_col.setCellValueFactory(new PropertyValueFactory<>("name"));
        phone_n_col.setCellValueFactory(new PropertyValueFactory<>("phone"));

        hotel_col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<User,String>,ObservableValue<String>>(){

            @Override
            public ObservableValue<String> call(CellDataFeatures<User, String> param) {
                String hotels = "";
                int count = 0;
                for (Hotel hotel : param.getValue().getHotel()) {
                    if (param.getValue().getHotel().size() == 1 || count == 0){
                        hotels += hotel.getHotel_name();
                    }
                    else 
                        hotels += "," + hotel.getHotel_name();
                    count++;
                }
                return new SimpleStringProperty(hotels);
            }
            
        });

        load();
    }

}
