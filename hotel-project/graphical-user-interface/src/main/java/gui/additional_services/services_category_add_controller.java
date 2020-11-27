package gui.additional_services;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

public class services_category_add_controller {

    @FXML
    private AnchorPane mainPanel;

    private AnchorPane otherpane;

    @FXML
    private TextField services_category_add_name;

    @FXML
    private Button services_category_add_savebutton;

    @FXML
    private ComboBox<String> services_category_add_negorpos;
    private ObservableList<String> choose = FXCollections.observableArrayList("Negative", "Positive");

    

    @FXML
    void textBoxKeyPressed(KeyEvent event) {
        String check = services_category_add_name.getText();
        if (check.length() > 0) {
            services_category_add_savebutton.setText("Save");
        } else {
            services_category_add_savebutton.setText("Skip");
        }

    }

    @FXML
    public void initialize() {
        services_category_add_negorpos.setItems(choose);
    }

    @FXML
    void saveOrSkip(ActionEvent event) throws IOException {

        if (services_category_add_savebutton.getText().equals("Save")) {
            String text = services_category_add_name.getText();
            String box = services_category_add_negorpos.getValue();
            if (box==null || box.length() == 0) {
                Alert al = new Alert(AlertType.WARNING, "Don't leave empty fields!");
                al.showAndWait();
            }

        }
        else
        {
            otherpane = FXMLLoader.load(getClass().getResource("services_add.FXML"));
            mainPanel.getChildren().setAll(otherpane);
        }
    }

    
    



}
