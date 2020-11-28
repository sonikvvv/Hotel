package gui.references;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import base_classes.classes.User;
import base_classes.classes.emuns.URE;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.util.Callback;
import logic.DecodeOperation;
import logic.OperationType;
import logic.operations.RoomBusyness;
import logic.operations.UserOperations;

public class Receptionist_ReserenceController implements Initializable {

    @FXML
    private DatePicker fromDate;

    @FXML
    private DatePicker toDate;

    @FXML
    private TableView<RoomBusyness> recept_ref_table;
    private ObservableList<RoomBusyness> activ = FXCollections.observableArrayList();
    private static int cols = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fromDate.setValue(LocalDate.now());
        toDate.setValue(LocalDate.now());

        recept_ref_table.getColumns().clear();

        List<String> data = new ArrayList<>();
        data.add(LocalDate.of(2020, 11, 5).toString());
        data.add(toDate.getValue().toString());

        UserOperations.setUser_now(new User("name", "password", URE.RECEPTIONIST));

        List<String> roomTypes = new ArrayList<>();

        List<?> roomTypes_result = DecodeOperation.decodeLogicOperation(OperationType.ROOM_TYPES, null, null);
        if (roomTypes_result != null && roomTypes_result.size() != 0) {
            for (Object object : roomTypes_result) {
                roomTypes.add((String) object);
            }
        }

        TableColumn<RoomBusyness, String> date_col = new TableColumn<>("Date");
        date_col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<RoomBusyness,String>,ObservableValue<String>>(){

            @Override
            public ObservableValue<String> call(CellDataFeatures<RoomBusyness, String> param) {
                return new SimpleStringProperty(param.getValue().getDate().toString());
            }
            
        });

        recept_ref_table.getColumns().add(date_col);

        
        for (String string : roomTypes) {
            TableColumn<RoomBusyness, Number> col = new TableColumn<>(string);
            col.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<RoomBusyness, Number>, ObservableValue<Number>>() {
                    //static final String name = string;
                    @Override
                    public ObservableValue<Number> call(CellDataFeatures<RoomBusyness, Number> param) {
                        final int number = recept_ref_table.getColumns().indexOf(col);
                        return new SimpleIntegerProperty(param.getValue().getRoom_busynes()[0]);
                    }
                
            });
            recept_ref_table.getColumns().add(col);
            cols += 1;
        }

        List<?> result = DecodeOperation.decodeLogicOperation(OperationType.RECEPTIONIST_REFERENCE, null, data);
        if (result != null && result.size() != 0) {
            for (Object object : result) {
                activ.add((RoomBusyness) object);
            }
        }

        recept_ref_table.getItems().setAll(activ);

    }



}
