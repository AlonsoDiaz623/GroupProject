/**
 * Date: 4/24/2026
 * 
 * For Simon says color memory game project in class CSC-331
 * 
 * PURPOSE: RedPad controls the red pad/button of our memory game 
 * which is 1 of 4 pads. It extends the complete simon pad and controls 
 * the sound and color of specifically the red pad.
 */
package application;

import javafx.scene.control.Button;

public class RedPad extends SimonPad {
    // Extends simonPad and controls red color pad which includes sound and color changes
    public RedPad(Button button) {
        //Uses the attributes from the simonPad super classes
        super(0, button, "-fx-background-color: red;");
    }

    @Override
    public String getBrightStyle() {
        //Changes color of red pad to lighter version when needed within the game
        //A lighter version of red is pink
        return "-fx-background-color: pink;";
    }

    @Override
    public int getMidiNote() {
        //Pairs a sound with the selection and/or indication of the redPad
        return 69;
    }
}