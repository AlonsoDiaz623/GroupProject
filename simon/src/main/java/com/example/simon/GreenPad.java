package application;

import javafx.scene.control.Button;

public class GreenPad extends SimonPad {

    public GreenPad(Button button) {
        super(1, button, "-fx-background-color: green;");
    }

    @Override
    public String getBrightStyle() {
        return "-fx-background-color: lightgreen;";
    }

    @Override
    public int getMidiNote() {
        return 64;
    }
}