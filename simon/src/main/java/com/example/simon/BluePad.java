/**
 * Date: 4/24/2026
 * 
 * For Simon says color memory game project in class CSC-331
 * 
 * PURPOSE: BluePad controls the bluie pad/button of out memory game which is 1 of 4 
 * pads. It extends the complete simon pad and controls the sound and color
 * of specifically the blue pad.
 */
package application;

import javafx.scene.control.Button;

public class BluePad extends SimonPad {
    //Controls blue color pad specificaslly sound and color changes
    public BluePad(Button button) {
        //SimonPad is the super class and we need its attributes
        super(3, button, "-fx-background-color: blue;");
    }

    @Override
    public String getBrightStyle() {
        //Sets color to light blue when needed and helps in idication of color selection and or prompt
        return "-fx-background-color: lightblue;";
    }

    @Override
    public int getMidiNote() {
        //Pairs a sound with the slection or propmt of the color blue pad.
        return 76;
    }
}