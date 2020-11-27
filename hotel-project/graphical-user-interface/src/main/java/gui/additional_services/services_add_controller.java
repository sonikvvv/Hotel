package gui.additional_services;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class services_add_controller {

    private AnchorPane otherpane;

    @FXML
    private AnchorPane mainPane;

    @FXML
    private ComboBox<String> Service;
    private ComboBox<String> Category;
    private TextField Quantity;
    private TextField Price;
    private TextArea Note;



    @FXML
    void nextToadd(ActionEvent event) throws IOException 
    {
        otherpane = FXMLLoader.load(getClass().getResource("services_to_room.FXML"));
        mainPane.getChildren().setAll(otherpane);
    }



    
}
