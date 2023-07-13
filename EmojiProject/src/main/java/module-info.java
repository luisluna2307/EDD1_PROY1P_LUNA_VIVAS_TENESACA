module controladores {
    requires javafx.swing;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens controladores to javafx.fxml;
    exports controladores;
}
