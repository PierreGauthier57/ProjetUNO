module com.example.projet_uno {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.projet_uno to javafx.fxml;
    exports com.example.projet_uno;
}