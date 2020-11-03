module hotel {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    requires com.jfoenix;
    requires org.hibernate.commons.annotations;
    requires org.hibernate.orm.core;

    opens hotel to javafx.fxml;
    exports hotel;

    opens gui to javafx.fxml;
    exports gui;
}