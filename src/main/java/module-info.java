module org.bsk {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.bsk to javafx.fxml;
    exports org.bsk;
}