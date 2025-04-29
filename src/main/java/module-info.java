module edu.umn.d.cs1622.cs1622finalproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens edu.umn.d.cs1622.cs1622finalproject to javafx.fxml;
    exports edu.umn.d.cs1622.cs1622finalproject;
}