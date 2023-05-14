module com.main.alfabank {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.main.alfabank to javafx.fxml;
    exports com.main.alfabank;
}