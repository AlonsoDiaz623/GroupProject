package application;

import javafx.scene.control.Button;

public class RedPad extends SimonPad {

    public RedPad(Button button) {
        super(0, button, "-fx-background-color: red;");
    }

    @Override
    public String getBrightStyle() {
        return "-fx-background-color: pink;";
    }

    @Override
    public int getMidiNote() {
        return 69;
    }
}