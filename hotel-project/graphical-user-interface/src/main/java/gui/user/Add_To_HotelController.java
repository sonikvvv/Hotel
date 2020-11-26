package gui.user;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import base_classes.classes.Hotel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import logic.DecodeOperation;
import logic.OperationType;

public class Add_To_HotelController implements Initializable {

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
    private ListView<String> hotel_list;

    private ObservableList<Hotel> activ = FXCollections.observableArrayList();

    private List<String> selectedHotels = new ArrayList<>();

    @FXML
    void save(ActionEvent event) {
        String username = username_txt.getText();
        String pass = pass_txt.getText();
        String name = names_txt.getText();
        String phone = phone_txt.getText();
        String email = email_txt.getText();

        if (selectedHotels.size() == 0 || selectedHotels == null) {
            Alert al = new Alert(AlertType.WARNING, "Select at leat 1 hotel!");
            al.showAndWait();
        }

        if (username.length() == 0 || pass.length()  == 0 || name.length() == 0 || phone.length() == 0 || email.length() == 0) {
            Alert al = new Alert(AlertType.WARNING, "Don't leave empty fields!");
            al.showAndWait();
        }else {
            List<String> data = new ArrayList<>();
            data.add(username);
            data.add(pass);
            data.add(name);
            data.add(phone);
            data.add(email);
            String hotels = "";
            for (String string : selectedHotels) {
                hotels += string + ",";
            }
            // TODO: save user
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<?> result = DecodeOperation.decodeLogicOperation(OperationType.GET_HOTEL, null, null);
        for (Object object : result) {
            Hotel tmp = (Hotel) object;
            activ.add(tmp);
        }

        for (Hotel hotel : activ) {
            hotel_list.getItems().add(hotel.getHotel_name());
        }

        hotel_list.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        hotel_list.setOnMouseClicked(new EventHandler<Event>(){
            @Override
            public void handle(Event event) {
                ObservableList<String> selectedItems = hotel_list.getSelectionModel().getSelectedItems();
                selectedHotels.clear();
                for (String s : selectedItems) {
                    selectedHotels.add(s);
                }
            }
        });

    }

}