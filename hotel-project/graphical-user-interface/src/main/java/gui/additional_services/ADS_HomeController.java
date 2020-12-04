package gui.additional_services;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import base_classes.classes.AdditServices;
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

    private static final Logger LOGGER = LogManager.getLogger(ADS_HomeController.class);

    @FXML
    void add_btn(ActionEvent event) {
        LOGGER.info("Add additional service button clicked.");
        LOGGER.debug("Starting add additional service.");
        try {
            Stage st = new Stage();
            Scene sc = new Scene(FXMLLoader.load(getClass().getResource("services_category_add.FXML")));
            st.setScene(sc);
            st.show();
            LOGGER.debug("Add service scene loaded succesfuly.");
        } catch (Exception e) {
            LOGGER.error("Loading exeption occured -> {}", e);
        }
    }

    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LOGGER.debug("Starting initialize.");
        
        title_col.setCellValueFactory(new PropertyValueFactory<>("title"));
        price_col.setCellValueFactory(new PropertyValueFactory<>("price"));
        category_col.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<AdditServices, String>, ObservableValue<String>>() {

                    @Override
                    public ObservableValue<String> call(CellDataFeatures<AdditServices, String> param) {
                        return new SimpleStringProperty(param.getValue().getCategory().getCategory_title());
                    }
            
        });

        List<?> adsl = DecodeOperation.decodeLogicOperation(OperationType.GET_ADS, null, null);
        if (adsl != null && adsl.size() != 0){
            for (Object o : adsl) {
                AdditServices tmp = (AdditServices) o;
                activ.add(tmp);
            }
        }

        ads_table.getItems().setAll(activ);
    }

}
