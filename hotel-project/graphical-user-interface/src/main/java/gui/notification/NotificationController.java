package gui.notification;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import base_classes.classes.Reservation;
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
import javafx.util.Callback;

public class NotificationController implements Initializable {

    @FXML
    private TableView<Reservation> notification_table;

    @FXML
    private TableColumn<Reservation, String> notification_col;

    private static ObservableList<Reservation> activ = FXCollections.observableArrayList();

    public static ObservableList<Reservation> getActiv() {
        return activ;
    }

    public static void setActiv(List<Reservation> to_activ) {
        NotificationController.activ.setAll(to_activ);
    }

    @FXML
    void clearNotificationList(ActionEvent event) {
        notification_table.getItems().clear();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        notification_col.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Reservation, String>, ObservableValue<String>>() {

                    @Override
                    public ObservableValue<String> call(CellDataFeatures<Reservation, String> param) {
                        String before = "Reservation with number: ";
                        String after = " will checkout!";
                        return new SimpleStringProperty(before + param.getValue().getReservation_id() + after);
                    }
            
        });

        notification_table.setItems(activ);
    }
}
