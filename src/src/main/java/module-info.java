module org.bsk {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens org.bsk to javafx.fxml;
    exports org.bsk;
}