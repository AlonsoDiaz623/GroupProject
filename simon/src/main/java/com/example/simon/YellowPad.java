/**
 * Date: 4/24/2026
 * 
 * For Simon says color memory game project in class CSC-331
 * 
 * PURPOSE: YellowPad controls the yellow pad/button of our memory game 
 * which is 1 of 4 pads. It extends the complete simon pad and controls the 
 * sound and color of specifically the yellow pad.
 */
package application;

import javafx.scene.control.Button;

public class YellowPad extends SimonPad {
// Extends simonPad and controls yellow color pad which includes sound and color changes
    public YellowPad(Button button) {
        //Uses the attributes from the simonPad super classes
        super(2, button, "-fx-background-color: yellow;");
    }

    @Override
    public String getBrightStyle() {
        //Changes color of yellow pad to lighter version when needed within the game
        return "-fx-background-color: lightyellow;";
    }

    @Override
    public int getMidiNote() {
        //Pairs a sound with the selection and/or indication of the yellowPad
        return 73;
    }
}