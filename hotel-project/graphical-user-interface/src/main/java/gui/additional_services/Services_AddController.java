package gui.additional_services;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import base_classes.classes.AdditServices;
import base_classes.classes.ClientUsedServices;
import base_classes.classes.ServiceCategory;
import base_classes.classes.User;
import base_classes.classes.emuns.URE;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import logic.DecodeOperation;
import logic.OperationType;
import logic.operations.UserOperations;

public class Services_AddController implements Initializable {

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

    @FXML
    private TextField service_name;

    private ObservableList<AdditServices> activ = FXCollections.observableArrayList();

    private List<ServiceCategory> categories = new ArrayList<>();

    private AdditServices aServices = null;

    private ClientUsedServices cus = null;

    private static final Logger LOGGER = LogManager.getLogger(Services_AddController.class);

    public static boolean flag = false;

    public ClientUsedServices getCus() {
        return cus;
    }

    @FXML
    void categoryChanged() {
        LOGGER.info("User chosed category.");
        LOGGER.debug("Starting categoryChanged.");
        String category = category_cb.getSelectionModel().getSelectedItem();
        List<String> services = new ArrayList<>();

        service_cb.getItems().clear();
        services.clear();
        if(category != null && category.length() != 0){
            for (AdditServices additServices : activ) {
                if (additServices.getCategory().getCategory_title().equalsIgnoreCase(category)) {
                    services.add(additServices.getTitle());
                }
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
        String category = category_cb.getSelectionModel().getSelectedItem();
        String name = service_name.getText();

        if (flag == true) {
            if (aServices == null || quantity.length() == 0 || price.length() == 0) {
                Alert al = new Alert(AlertType.WARNING, "Don't leave empty fields!");
                al.showAndWait();
            } else if (!quantity.matches("([0-9]*)") || !price.matches("([0-9]*)\\.([0-9]*)")) {
                Alert al = new Alert(AlertType.WARNING, "Fields quantity and price are for numbers only!");
                al.showAndWait();
            } else {
                cus = new ClientUsedServices(aServices, Integer.valueOf(quantity), note);
                Stage st = (Stage) quantity_txt.getScene().getWindow();
                st.close();
            }
        } else {
            if ((category != null && category.length() == 0) || name.length() == 0 || price.length() == 0) {
                Alert al = new Alert(AlertType.WARNING, "Don't leave empty fields!");
                al.showAndWait();
            } else if (price.matches("([0-9]*)\\,|\\.([0-9]*)")) {
                Alert al = new Alert(AlertType.WARNING, "Field price is for numbers only!");
                al.showAndWait();
            } else {
                ServiceCategory sc = null;
                for (ServiceCategory sCategory : categories) {
                    if (sCategory.getCategory_title().equals(category)) {
                        sc = sCategory;
                        break;
                    }
                }

                AdditServices ads = new AdditServices();
                ads.setTitle(name);
                ads.setCategory(sc);
                if (price.contains(",")) {
                    price = price.replace(",", ".");
                }
                ads.setPrice(Double.valueOf(price));

                User user_now = UserOperations.getUser_now().get(0);

                if (user_now.getUser_role() != URE.ADMIN && user_now.getUser_role() != URE.OWNER)
                    ads.setHotel(user_now.getHotel().get(0));

                LOGGER.debug("Saving new service -> {}", ads);
                DecodeOperation.decodeLogicOperation(OperationType.SAVE, ads, null);
                Stage st = (Stage) quantity_txt.getScene().getWindow();
                st.close();
            }
        }
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
        LOGGER.debug("Starting inititalize.");
        category_cb.getItems().clear();
        activ.clear();
        List<?> result = DecodeOperation.decodeLogicOperation(OperationType.GET_ADS, null, null);
        if (result != null && result.size() != 0) {
            for (Object object : result) {
                activ.add((AdditServices) object);
            }
        }
        
        result = DecodeOperation.decodeLogicOperation(OperationType.GET_SERVICE_CATEGORY, null, null);
        for (Object object : result) {
            categories.add((ServiceCategory) object);
            category_cb.getItems().add(((ServiceCategory) object).getCategory_title());
        }

        if (flag == true) {
            service_name.toBack();
            service_name.setVisible(false);
            LOGGER.debug("Hiding service name text field.");
        } else {
            service_cb.toBack();
            service_cb.setVisible(false);
            quantity_txt.setVisible(false);
            note_txt.setVisible(false);
            LOGGER.debug("Hiding service comboBoxq, quantity and note text fields.");
        }		
	}
}
