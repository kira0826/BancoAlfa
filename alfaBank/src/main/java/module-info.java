module com.main.alfabank {

    /**
     * Esta seccion me permite establecer el scope de acceso al javafx.
     */
    requires javafx.controls;
    requires javafx.fxml;

    opens com.main.alfabank to javafx.fxml;
    opens com.main.alfabank.model.controllers to javafx.fxml;
    opens com.main.alfabank.model.domain to javafx.fxml;

    exports com.main.alfabank.model.domain;
    exports com.main.alfabank;
    exports com.main.alfabank.model.controllers;
}