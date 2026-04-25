/**
 * Date: 4/24/2026
 * 
 * For Simon says color memory game project in class CSC-331
 * 
 * Purpose: Model info declares the JavaFX and dependencies 
 * the Simon game needs and exposes the application package so JavaFX.
 */
module com.example.simon {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    exports application;
    opens application to javafx.fxml;
}