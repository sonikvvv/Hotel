package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    private static final Logger LOGGER = LogManager.getLogger(App.class);

    @Override
    public void start(Stage stage) {
        try {
            LOGGER.debug("Starting application with values _> {}.", stage);
            scene = new Scene(loadFXML("home_view1")); // login/login
            // scene = new Scene(loadFXML("room/add_room"));
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            LOGGER.error("Loading exeption occured -> {}", e);
        }
    }

    static void setRoot(String fxml) {
        try {
            LOGGER.debug("Starting set root with data -> {}.", fxml);
            scene.setRoot(loadFXML(fxml));
        } catch (Exception e) {
            LOGGER.error("Loading exeption occured -> {}", e);
        }
    }

    private static Parent loadFXML(String fxml) {
        try {
            LOGGER.debug("Starting load fxml with data -> {}.", fxml);
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
            return fxmlLoader.load();
        } catch (Exception e) {
            LOGGER.error("Loading exeption occured -> {}", e);
            return null;
        }
    }

    public static void main(String[] args) {
        LOGGER.debug("Starting main - launch.");
        launch();
    }



}