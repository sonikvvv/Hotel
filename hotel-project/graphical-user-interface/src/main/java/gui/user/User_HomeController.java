package gui.user;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import base_classes.classes.User;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import logic.DecodeOperation;
import logic.OperationType;

public class User_HomeController implements Initializable {

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
    void add_btn(ActionEvent event) { // TODO: get role from decode operation
        try {
            Stage st = new Stage();
            Scene sc = new Scene(FXMLLoader.load(getClass().getResource("add_user.fxml")));
            st.setScene(sc);
            st.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
