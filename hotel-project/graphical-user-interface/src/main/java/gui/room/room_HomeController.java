package gui.room;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import base_classes.classes.Raiting;
import base_classes.classes.Room;
import base_classes.classes.emuns.SE;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class room_HomeController implements Initializable {

    @FXML
    private DatePicker fromDate;

    @FXML
    private DatePicker toDate;

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

    
    /** 
     * @param event
     */
    @FXML
    void add_btn(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fromDate.setValue(LocalDate.now());
        toDate.setValue(LocalDate.now());
        number_col.setCellValueFactory(new PropertyValueFactory<>("r_number"));
        room_type_col.setCellValueFactory(new PropertyValueFactory<>("r_type"));
        status_col.setCellValueFactory(
            new Callback<TableColumn.CellDataFeatures<Room, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(CellDataFeatures<Room, String> param) {
                    return new SimpleStringProperty(param.getValue().getR_status().toString());
                }
        });
        
        LocalDate fromD = fromDate.getValue();
        LocalDate toD = toDate.getValue();

        List<String> data = new ArrayList<>();
        data.add(fromD.toString());
        data.add(toD.toString());

        Room r = new Room("r_number", "Double", 153, SE.DIRTY);
        r.addToRait(new Raiting(5));

        activ.add(r);
        room_table.getItems().setAll(activ);

    }

}
