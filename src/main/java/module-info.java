module com.example.proyectopooparte2 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires java.desktop;
    requires transitive org.apache.commons.text;
    requires com.google.gson;

    // FXML
    opens com.example.proyectopooparte2 to javafx.fxml;
    opens com.example.proyectopooparte2.controllers to javafx.fxml;

    // Gson
    opens com.example.proyectopooparte2.model.Game to com.google.gson;
    opens com.example.proyectopooparte2.model.Game.Questions to com.google.gson;

    exports com.example.proyectopooparte2;
    exports com.example.proyectopooparte2.controllers;
}
