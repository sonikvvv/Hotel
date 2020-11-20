package gui.additional_services;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import base_classes.classes.AdditServices;
import base_classes.classes.ServiceCategory;
import base_classes.classes.emuns.ServiceType;
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

public class ADS_HomeController implements Initializable {

    @FXML
    private TableView<AdditServices> ads_table;

    @FXML
    private TableColumn<AdditServices, String> title_col;

    @FXML
    private TableColumn<AdditServices, String> category_col;

    @FXML
    private TableColumn<AdditServices, Number> price_col;

    private ObservableList<AdditServices> activ = FXCollections.observableArrayList();

    @FXML
    void add_btn(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        title_col.setCellValueFactory(new PropertyValueFactory<>("title"));
        price_col.setCellValueFactory(new PropertyValueFactory<>("price"));
        category_col.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<AdditServices, String>, ObservableValue<String>>() {

                    @Override
                    public ObservableValue<String> call(CellDataFeatures<AdditServices, String> param) {
                        return new SimpleStringProperty(param.getValue().getCategory().getCategory_title());
                    }
            
        });

        AdditServices as = new AdditServices("title", new ServiceCategory("category_title", ServiceType.PROSITIVE), 13);
        AdditServices as1 = new AdditServices("title", new ServiceCategory("category_title", ServiceType.PROSITIVE), 15);
        AdditServices as2 = new AdditServices("title", new ServiceCategory("category_title", ServiceType.PROSITIVE), 16);
        List<AdditServices> adsl = new ArrayList<>();
        adsl.add(as);
        adsl.add(as1);
        adsl.add(as2);

        activ.addAll(adsl);

        ads_table.getItems().setAll(activ);
    }

}
