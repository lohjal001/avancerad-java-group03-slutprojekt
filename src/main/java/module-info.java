module com.example.finalprojectavcjava {
    requires javafx.controls;
    requires javafx.fxml;
    requires minimal.json;
    requires cn.hutool;


    opens com.example.finalprojectavcjava to javafx.fxml;
    exports com.example.finalprojectavcjava;
}