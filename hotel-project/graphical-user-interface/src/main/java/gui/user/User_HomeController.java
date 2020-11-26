package gui.user;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

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

    private ObservableList<User> activ = FXCollections.observableArrayList();

    @FXML
    void add_user(ActionEvent event) { 
        try {
            Stage st = new Stage();
            Scene sc;
            if (UserOperations.getUser_now().get(0).getUser_role() == URE.ADMIN ||
                UserOperations.getUser_now().get(0).getUser_role() == URE.OWNER) {
            sc = new Scene(FXMLLoader.load(getClass().getResource("add_to_hotel.fxml")));
            } else sc = new Scene(FXMLLoader.load(getClass().getResource("add_user.fxml")));
            st.setScene(sc);
            st.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void keyPressed(KeyEvent event) {
        User user_now = UserOperations.getUser_now().get(0);
        if (user_now.getUser_role() == URE.ADMIN) {
            if (event.getCode() == KeyCode.DELETE) {
                Alert al = new Alert(AlertType.CONFIRMATION);
                al.setContentText("Delete this user?");
                Optional<ButtonType> result = al.showAndWait();
                User to_delete;

                if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
                    to_delete = user_table.getSelectionModel().getSelectedItem();
                    if (to_delete != null) {
                        user_table.getItems().remove(to_delete);
                        DecodeOperation.decodeLogicOperation(OperationType.DELETE, to_delete, null);
                    }
                }
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        User user_now = UserOperations.getUser_now().get(0);

        if (user_now.getUser_role() == URE.RECEPTIONIST) {
            add_btn.setVisible(false);
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

        List<?> result = DecodeOperation.decodeLogicOperation(OperationType.GET_USERS, null, null);
        if (result != null && result.size() != 0){
            for (Object object : result) {
                User tmp = (User) object;
                activ.add(tmp);
            }
        }

        user_table.getItems().setAll(activ);
    }

}
