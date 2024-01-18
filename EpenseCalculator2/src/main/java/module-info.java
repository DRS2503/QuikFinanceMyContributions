module com.example.epensecalculator2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.epensecalculator2 to javafx.fxml;
    exports com.example.epensecalculator2;
}