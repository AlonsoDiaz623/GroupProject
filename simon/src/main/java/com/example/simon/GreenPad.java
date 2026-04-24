/**
 * Date: 4/24/2026
 * 
 * For Simon says color memory game project in class CSC-331
 * 
 * PURPOSE: GreenPad controls the green pad/button of out memory game which is 1 of 4 
 * pads. It extends the complete simon pad and controls the sound and color
 * of specifically the green pad.
 */

package application;

import javafx.scene.control.Button;

public class GreenPad extends SimonPad {
    // Extends simonPad and controls green color pad specificaslly sound and color changes
    public GreenPad(Button button) {
        //Uses the attributes from the simonPad super classes
        super(1, button, "-fx-background-color: green;");
    }

    @Override
    public String getBrightStyle() {
        //Changes color of green pad to lighter version when needed within the game
        return "-fx-background-color: lightgreen;";
    }

    @Override
    public int getMidiNote() {
        //Pairs a sound with the slection and/or indication of the greenPad
        return 64;
    }
}