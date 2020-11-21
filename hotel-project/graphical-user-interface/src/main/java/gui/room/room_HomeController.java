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
        // AdditServices as = new AdditServices("title", new ServiceCategory("category_title", ServiceType.PROSITIVE), 13);
        // AdditServices as1 = new AdditServices("title", new ServiceCategory("category_title", ServiceType.PROSITIVE), 15);
        // AdditServices as2 = new AdditServices("title", new ServiceCategory("category_title", ServiceType.PROSITIVE), 16);

        // ClientUsedServices cus = new ClientUsedServices(as, 1, "note");
        // ClientUsedServices cus1 = new ClientUsedServices(as1, 1, "note");
        // ClientUsedServices cus2 = new ClientUsedServices(as2, 1, "note");

        // Clients c = new Clients("Amina Jakubowski", LocalDate.now(), false, "30506", LocalDate.of(2020, 11, 10), "car_number",
        //         new Country("Testivile"), "client_note", "vaucher", 1);
        // Clients c1 = new Clients("Annamarie Haag", LocalDate.now(), true, "67547", LocalDate.of(2020, 11, 15), "car_number",
        //         new Country("Falkland Islands (Malvinas)"), "client_note", "vaucher", 2);

        // c.addToUsedServices(cus);
        // c.addToUsedServices(cus1);
        // c1.addToUsedServices(cus);
        // c1.addToUsedServices(cus2);

        // Room r = new Room("r_number", "r_type", 300, SE.OCCUPIED);
        // r.addToClients(c);
        // r.addToClients(c1);

        if (event.getClickCount() == 1){
            System.out.println(room_table.getSelectionModel().getSelectedItem()); //Send to DecodeOperation to save it
        
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
