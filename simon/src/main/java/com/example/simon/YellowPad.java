package application;

import javafx.scene.control.Button;

public class YellowPad extends SimonPad {

    public YellowPad(Button button) {
        super(2, button, "-fx-background-color: yellow;");
    }

    @Override
    public String getBrightStyle() {
        return "-fx-background-color: lightyellow;";
    }

    @Override
    public int getMidiNote() {
        return 73;
    }
}