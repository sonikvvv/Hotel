package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
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
            scene = new Scene(loadFXML("login/login"));
            stage.setScene(scene);
            stage.setTitle("Login");
            stage.getIcons().add(new Image(getClass().getResourceAsStream("./icons/logo3.png")));
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