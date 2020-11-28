package gui.references;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleIntegerProperty;
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
import javafx.util.Callback;
import logic.DecodeOperation;
import logic.OperationType;
import logic.operations.RoomBusyness;

public class Receptionist_ReserenceController implements Initializable {

    @FXML
    private DatePicker fromDate;

    @FXML
    private DatePicker toDate;

    @FXML
    private TableView<RoomBusyness> recept_ref_table;
    private ObservableList<RoomBusyness> activ = FXCollections.observableArrayList();
    private List<String> roomTypes = new ArrayList<>();
    private final String style = "column-header-background";

    @FXML
    void search(ActionEvent event) {
        recept_ref_table.getItems().clear();
        activ.clear();

        List<String> data = new ArrayList<>();
        data.add(fromDate.getValue().toString());
        data.add(toDate.getValue().toString());

        List<?> result = DecodeOperation.decodeLogicOperation(OperationType.RECEPTIONIST_REFERENCE, null, data);
        if (result != null && result.size() != 0) {
            for (Object object : result) {
                activ.add((RoomBusyness) object);
            }
        }

        recept_ref_table.getItems().setAll(activ);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fromDate.setValue(LocalDate.now());
        toDate.setValue(LocalDate.now());

        recept_ref_table.getColumns().clear();
        recept_ref_table.getItems().clear();

        List<String> data = new ArrayList<>();
        data.add(fromDate.getValue().toString());
        data.add(toDate.getValue().toString());

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

        date_col.getStyleClass().add(style);
        recept_ref_table.getColumns().add(date_col);
        
        for (String string : roomTypes) {
            TableColumn<RoomBusyness, Number> col = new TableColumn<>(string);
            col.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<RoomBusyness, Number>, ObservableValue<Number>>() {
                    //static final String name = string;
                    @Override
                    public ObservableValue<Number> call(CellDataFeatures<RoomBusyness, Number> param) {
                        String name_col = col.getText();
                        int col_n = roomTypes.indexOf(name_col);
                        return new SimpleIntegerProperty(param.getValue().getRoom_busynes()[col_n]);
                    }
                
            });
            col.getStyleClass().add(style);
            recept_ref_table.getColumns().add(col);

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
