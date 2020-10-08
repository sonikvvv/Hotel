package Hotel;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Calendar;

public class Controller
{
@FXML
private AnchorPane mainPane;
    public void switchToReservations(ActionEvent e) throws IOException {
        AnchorPane switchPane = FXMLLoader.load(getClass().getResource("Reservations.FXML"));
        mainPane.getChildren().setAll(switchPane);

    }
}
