package gui.references;

import java.net.URL;
import java.util.ResourceBundle;

import base_classes.classes.AdditServices;
import base_classes.classes.ClientUsedServices;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
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

public class Used_ServicesController implements Initializable {

    @FXML
    private AnchorPane service_pane;

    @FXML
    private TableView<ClientUsedServices> services_table;

    @FXML
    private TableColumn<ClientUsedServices, Number> number_col;

    @FXML
    private TableColumn<ClientUsedServices, String> type_col;

    @FXML
    private TableColumn<ClientUsedServices, String> name_col;

    @FXML
    private TableColumn<ClientUsedServices, Integer> quantity_col;

    @FXML
    private TableColumn<ClientUsedServices, Number> single_price_col;

    private ObservableList<ClientUsedServices> activ = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        AdditServices as1 = new AdditServices("safe 14D", "safe", 10);
        as1.setA_serv_id(1);
        AdditServices as2 = new AdditServices("safe 15D", "safe", 15);
        as2.setA_serv_id(2);

        ClientUsedServices cus1 = new ClientUsedServices(as1, 2, "note");
        ClientUsedServices cus2 = new ClientUsedServices(as2, 3, "note");
        ClientUsedServices cus3 = new ClientUsedServices(as1, 6, "note");

        activ.add(cus1);
        activ.add(cus2);
        activ.add(cus3);

        quantity_col.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        number_col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ClientUsedServices, Number>, ObservableValue<Number>>() {

            @Override
            public ObservableValue<Number> call(CellDataFeatures<ClientUsedServices, Number> param) {
                return new SimpleIntegerProperty(param.getValue().getAddit_service().getA_serv_id());
            }
            
        });
        
        single_price_col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ClientUsedServices,Number>, ObservableValue<Number>>() {

            @Override
            public ObservableValue<Number> call(CellDataFeatures<ClientUsedServices,Number> param) {
                return new SimpleDoubleProperty(param.getValue().getAddit_service().getA_serv_price());
            }
            
        });

        type_col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ClientUsedServices,String>,ObservableValue<String>>(){

            @Override
            public ObservableValue<String> call(CellDataFeatures<ClientUsedServices, String> param) {
                return new SimpleStringProperty(param.getValue().getAddit_service().getA_serv_type());
            }
            
        });
        
        name_col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ClientUsedServices,String>,ObservableValue<String>>(){

            @Override
            public ObservableValue<String> call(CellDataFeatures<ClientUsedServices, String> param) {
                return new SimpleStringProperty(param.getValue().getAddit_service().getA_serv_title());
            }
            
        });

        services_table.getItems().addAll(activ);

    }


}
