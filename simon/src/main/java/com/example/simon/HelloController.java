/**
 * Date: 4/24/2026
 * 
 * For Simon says color memory game project in class CSC-331
 * 
 * PURPOSE: HelloController connects the FXML file to the application logic
 */
package com.example.simon;

import javafx.fxml.FXML; 
import javafx.scene.control.Label;

public class HelloController {
    // Looks for user action
    // allows the controller to modify the text displayed

    @FXML
    private Label welcomeText; //Use label as a refrence to update text

    @FXML
    protected void onHelloButtonClick() {
        //This runs when hello buttone is clicked and displays a welcome message
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}