module com.example.interestcalculator {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.example.interestcalculator to javafx.fxml;
    exports com.example.interestcalculator;
}