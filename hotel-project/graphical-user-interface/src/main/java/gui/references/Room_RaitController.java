package gui.references;

import java.net.URL;
import java.util.ResourceBundle;

import base_classes.classes.Room;
import base_classes.classes.emuns.SE;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

public class Room_RaitController implements Initializable {

    @FXML
    private AnchorPane room_rait_pane;

    @FXML
    private TableView<Room> room_rait_table;

    @FXML
    private TableColumn<Room, Integer> number_col;

    @FXML
    private TableColumn<Room, String> room_num_col;

    @FXML
    private TableColumn<Room, String> type_col;

    @FXML
    private TableColumn<Room, String> status_col;

    @FXML
    private TableColumn<Room, ?> raiting_col;

    private ObservableList<Room> activ = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Room r1 = new Room("103", "meh", SE.FREE);
        // r1.setR_id(1);
        // Room r2 = new Room("106", "MEH2", SE.OCCUPIED);
        // r2.setR_id(3);

        // activ.add(r1);
        // activ.add(r2);

        number_col.setCellValueFactory(new PropertyValueFactory<>("r_id"));
        room_num_col.setCellValueFactory(new PropertyValueFactory<>("r_number"));
        type_col.setCellValueFactory(new PropertyValueFactory<>("r_type"));
        status_col.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Room, String>, ObservableValue<String>>() {

                    @Override
                    public ObservableValue<String> call(CellDataFeatures<Room, String> param) {
                        return new SimpleStringProperty(param.getValue().getR_status().toString());
                    }
            
        });

        room_rait_table.getItems().addAll(activ);

    }


}
