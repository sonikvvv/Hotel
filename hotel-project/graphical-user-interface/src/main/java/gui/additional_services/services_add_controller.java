package gui.additional_services;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import base_classes.classes.AdditServices;
import base_classes.classes.ClientUsedServices;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import logic.DecodeOperation;
import logic.OperationType;

public class services_add_controller implements Initializable {

    @FXML
    private ComboBox<String> service_cb;

    @FXML
    private ComboBox<String> category_cb;

    @FXML
    private TextField price_txt;

    @FXML
    private TextArea note_txt;

    @FXML
    private TextField quantity_txt;

    private ObservableList<AdditServices> activ = FXCollections.observableArrayList();

    private AdditServices aServices = null;

    private ClientUsedServices cus = null;

    private static final Logger LOGGER = LogManager.getLogger(services_add_controller.class);

    public ClientUsedServices getCus() {
        return cus;
    }

    @FXML
    void categoryChanged(MouseEvent event) {
        LOGGER.info("User chosed category.");
        LOGGER.debug("Starting categoryChanged.");
        String category = category_cb.getSelectionModel().getSelectedItem();
        List<String> services = new ArrayList<>();

        if(category != null && category.length() != 0){
            for (AdditServices additServices : activ) {
                services.add(additServices.getTitle());
            }
            service_cb.getItems().addAll(services);
            LOGGER.debug("Result -> {}", services.toString());
        }
    }

    @FXML
    void serviceChanged() {
        LOGGER.info("User chosed service.");
        LOGGER.debug("Starting serviceChanged.");
        String service = service_cb.getSelectionModel().getSelectedItem();

        if (service != null && service.length() != 0) {
            for (AdditServices additServices : activ) {
                if (additServices.getTitle().equalsIgnoreCase(service)) {
                    aServices = additServices;
                    break;
                }
            }

            LOGGER.debug("Result -> {}", aServices.toString());
            price_txt.setText(aServices.getPrice() + "");
        }
    }

    @FXML
    void save(ActionEvent event) {
        LOGGER.debug("User clicked save button.");
        LOGGER.debug("Starting save.");
        String quantity = quantity_txt.getText();
        String price = price_txt.getText();
        String note = note_txt.getText();

        if (aServices == null || quantity.length() == 0 || price.length() == 0) {
            Alert al = new Alert(AlertType.WARNING, "Don't leave empty fields!");
            al.showAndWait();
        }else if (!quantity.matches("([0-9]*)") || !price.matches("([0-9]*)\\.([0-9]*)")){
            Alert al = new Alert(AlertType.WARNING, "Fields quantity and price are for numbers only!");
            al.showAndWait();
        }
        else {
            cus = new ClientUsedServices(aServices, Integer.valueOf(quantity), note);
            Stage st = (Stage) quantity_txt.getScene().getWindow();
            st.close();
        }
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
        LOGGER.debug("Starting inititalize.");
        List<?> result = DecodeOperation.decodeLogicOperation(OperationType.GET_ADS, null, null);
        if (result != null && result.size() != 0) {
            for (Object object : result) {
                activ.add((AdditServices) object);
                category_cb.getItems().add(((AdditServices)object).getCategory().getCategory_title());
            }
        }
		
	}

}
