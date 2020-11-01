module hotel {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    requires com.jfoenix;

    opens hotel to javafx.fxml;
    exports hotel;

    opens gui to javafx.fxml;
    exports gui;
}