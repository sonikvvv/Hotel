package hotel;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import hotel.base_classes.User;
import hotel.base_classes.UserRoles;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"));
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();

        UserRoles ur = new UserRoles("admin");
        ur.setRole_id(1);
        User u = new User("ves", "test", ur);
        u.setUser_id(2);

        Configuration con = new Configuration().configure().addAnnotatedClass(User.class);

        // ServiceRegistry sr = new serviceRegis

        SessionFactory sf = con.buildSessionFactory();

        Session ses = sf.openSession();

        Transaction tx = ses.beginTransaction();

        ses.save(u);

        tx.commit();
    }

}