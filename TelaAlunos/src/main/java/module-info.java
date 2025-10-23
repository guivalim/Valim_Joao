module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires javafx.graphics;

    opens org.example to javafx.fxml;
    exports org.example;
}
