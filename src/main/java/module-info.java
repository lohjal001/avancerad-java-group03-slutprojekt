module com.example.finalprojectavcjava {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.finalprojectavcjava to javafx.fxml;
    exports com.example.finalprojectavcjava;
}