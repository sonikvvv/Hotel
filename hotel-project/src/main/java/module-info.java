module hotel {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;

    opens hotel to javafx.fxml;
    exports hotel;
}