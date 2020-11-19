package gui.user;

import java.net.URL;
import java.util.ResourceBundle;

import base_classes.classes.User;
import base_classes.classes.emuns.URE;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

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
    void add_btn(ActionEvent event) {
        // TODO: get role
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

        User u = new User("Loraine75", "autiTYkKRO1S2g2", URE.OWNER);
        User u1 = new User("Hans.Lakin83", "jwr_6HCmF6EdMjr", URE.OWNER);
        User u2 = new User("Dejon_Casper34", "ECWnGq01UOj_5Mm", URE.OWNER);
        User u3 = new User("Nadia.Goldner", "jMcKKo_JBPGdWPD", URE.MANAGER);
        
        activ.add(u);
        activ.add(u1);
        activ.add(u2);
        activ.add(u3);

        user_table.getItems().setAll(activ);
    }

}
