package gui.user;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import base_classes.classes.Hotel;
import base_classes.classes.User;
import base_classes.classes.emuns.URE;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import logic.DecodeOperation;
import logic.OperationType;
import logic.operations.UserOperations;

public class Add_User_To_HotelController implements Initializable {

    @FXML
    private TextField names_txt;

    @FXML
    private TextField phone_txt;

    @FXML
    private TextField username_txt;

    @FXML
    private TextField email_txt;

    @FXML
    private PasswordField pass_txt;

    @FXML
    private TableView<Hotel> hotel_table = new TableView<>();

    @FXML
    private TableColumn<Hotel, String> hotel_name_col;

    private ObservableList<Hotel> activ = FXCollections.observableArrayList();

    private List<Hotel> selectedHotels = new ArrayList<>();

    private static final Logger LOGGER = LogManager.getLogger(Add_User_To_HotelController.class);

    private User user = null;

    public void setUser(User user) {
        this.user = user;
        names_txt.setText(user.getName());
        phone_txt.setText(user.getPhone());
        username_txt.setText(user.getUser_name());
        email_txt.setText(user.getEmail());
        pass_txt.setText(user.getUser_password());

        hotel_table.getSelectionModel().getSelectedItems().clear();
        for (Hotel hotel : user.getHotel()) {
            hotel_table.getSelectionModel().select(hotel);
        }
    }

    @FXML
    void save(ActionEvent event) {
        LOGGER.debug("Starting save usser to hotel.");
        User user_now = UserOperations.getUser_now().get(0);
        String username = username_txt.getText();
        String pass = pass_txt.getText();
        String name = names_txt.getText();
        String phone = phone_txt.getText();
        String email = email_txt.getText();

        if (selectedHotels == null || selectedHotels.size() == 0) {
            Alert al = new Alert(AlertType.WARNING, "Select at least 1 hotel!");
            al.showAndWait();
        } else if (user_now.getUser_role() == URE.OWNER) {
            if (selectedHotels == null || selectedHotels.size() == 0) {
                if (selectedHotels.size() > 1) {
                    Alert al = new Alert(AlertType.ERROR, "Selected more than 1 hotel!");
                    al.showAndWait();
                }
            }
        } else if (username.length() == 0 || pass.length()  == 0 || name.length() == 0 || phone.length() == 0 || email.length() == 0) {
            Alert al = new Alert(AlertType.WARNING, "Don't leave empty fields!");
            al.showAndWait();
        } else {
            List<String> data = new ArrayList<>();
            data.add(username);
            data.add(pass);
            data.add(name);
            data.add(phone);
            data.add(email);
            String hotels = "";
            for (Hotel hotel : selectedHotels) {
                hotels += hotel.getHotel_name() + ",";
            }
            data.add(hotels);
            
            if (user != null){
                user.setName(name);
                user.setUser_name(username);
                user.setPhone(phone);
                user.setEmail(email);
                user.setUser_password(pass);
                user.setHotel(hotel_table.getSelectionModel().getSelectedItems());
                DecodeOperation.decodeLogicOperation(OperationType.SAVE_OR_UPDATE, user, null);
            }
            else 
                DecodeOperation.decodeLogicOperation(OperationType.ADD_TO_USERS, null, data);
            Stage st = (Stage) hotel_table.getScene().getWindow();
            st.close();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LOGGER.debug("Starting initialize.");

        List<?> result = DecodeOperation.decodeLogicOperation(OperationType.GET_HOTEL, null, null);
        for (Object object : result) {
            Hotel tmp = (Hotel) object;
            activ.add(tmp);
        }

        hotel_name_col.setCellValueFactory(new PropertyValueFactory<>("hotel_name"));

        hotel_table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        hotel_table.setOnMouseClicked(new EventHandler<Event>(){
            @Override
            public void handle(Event event) {
                ObservableList<Hotel> selectedItems = hotel_table.getSelectionModel().getSelectedItems();
                selectedHotels.clear();
                for (Hotel h : selectedItems) {
                    selectedHotels.add(h);
                }
            }
        });

        hotel_table.getItems().addAll(activ);
    }

}
