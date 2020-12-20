package gui.additional_services;

import java.net.URL;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import base_classes.classes.ServiceCategory;
import base_classes.classes.emuns.ServiceType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import logic.DecodeOperation;
import logic.OperationType;

public class Add_CategoryController implements Initializable {

    @FXML
    private AnchorPane mainPanel;

    private AnchorPane otherpane;

    @FXML
    private TextField category_name;

    @FXML
    private Button save_bttn;

    @FXML
    private ComboBox<String> category_type;

    private ObservableList<String> choose = FXCollections.observableArrayList("Negative", "Positive");

    private static final Logger LOGGER = LogManager.getLogger(Add_CategoryController.class);

    @FXML
    void textBoxKeyPressed(KeyEvent event) {
        LOGGER.info("User typed int service category text field -> {}", event.getCharacter());
        LOGGER.debug("Starting textBoxKeyPressed.");
        String check = category_name.getText();
        if (check.length() > 0) {
            save_bttn.setText("Save");
        } else {
            save_bttn.setText("Skip");
        }
    }

    @FXML
    void saveOrSkip(ActionEvent event){
        LOGGER.info("User clicked save or skip button.");
        LOGGER.debug("Starting save or skip.");
        if (save_bttn.getText().equals("Save")) {
            String text = category_name.getText();
            String box = category_type.getValue();
            if (box == null || box.length() == 0 || text == null || text.length() == 0) {
                Alert al = new Alert(AlertType.WARNING, "Don't leave empty fields!");
                al.showAndWait();
            } else {
                ServiceCategory sc = new ServiceCategory();
                sc.setCategory_title(text);

                if (box.compareToIgnoreCase(ServiceType.NEGATIVE.toString()) == 0) {
                    sc.setType(ServiceType.NEGATIVE);
                } else
                    sc.setType(ServiceType.PROSITIVE);

                DecodeOperation.decodeLogicOperation(OperationType.SAVE, sc, null);

                try {
                    otherpane = FXMLLoader.load(getClass().getResource("services_add.FXML"));
                    mainPanel.getChildren().setAll(otherpane);
                } catch (Exception e) {
                    LOGGER.error("Loading exeption occured -> {}", e);
                }
            }
        } else {
            try {
                otherpane = FXMLLoader.load(getClass().getResource("services_add.FXML"));
                mainPanel.getChildren().setAll(otherpane);
            } catch (Exception e) {
                LOGGER.error("Loading exeption occured -> {}", e);
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LOGGER.debug("Starting initialize.");
        category_type.setItems(choose);
    }

}
