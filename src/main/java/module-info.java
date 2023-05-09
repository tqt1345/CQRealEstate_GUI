module com.mycompany.cqrealestate_gui {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.mycompany.cqrealestate_gui to javafx.fxml;
    exports com.mycompany.cqrealestate_gui;
}
