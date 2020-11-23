package gui.room;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import base_classes.classes.Room;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import logic.DecodeOperation;
import logic.OperationType;
import logic.operations.RoomOperations;

public class room_HomeController implements Initializable {

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

    @FXML
    void add_btn(ActionEvent event) {

    }

    @FXML
    void openRoomView(MouseEvent event) {
        if (event.getClickCount() == 2 && activ.size() != 0){
            try {
                Room tmp = room_table.getSelectionModel().getSelectedItem();
                RoomOperations.addToTemporal((tmp != null)? tmp : null);
                
                if (tmp != null) { 
                    try{
                        Stage st = new Stage();
                        Scene sc = new Scene(FXMLLoader.load(getClass().getResource("room_view.fxml")));
                        st.setScene(sc);
                        st.show();
                    }
                    catch(Exception e){ 
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {        
        number_col.setCellValueFactory(new PropertyValueFactory<>("r_number"));
        room_type_col.setCellValueFactory(new PropertyValueFactory<>("r_type"));
        status_col.setCellValueFactory(
            new Callback<TableColumn.CellDataFeatures<Room, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(CellDataFeatures<Room, String> param) {
                    return new SimpleStringProperty(param.getValue().getR_status().toString());
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
