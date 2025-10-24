module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires javafx.graphics;
    requires com.zaxxer.hikari;

    opens org.example to javafx.fxml;
    exports org.example;
}
