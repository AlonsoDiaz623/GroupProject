package application;

import java.util.ArrayList;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class SimonController {

    @FXML private Button redButton;
    @FXML private Button greenButton;
    @FXML private Button yellowButton;
    @FXML private Button blueButton;
    @FXML private Button startButton;

    @FXML private Label scoreLabel;
    @FXML private Label highScoreLabel;

    private ArrayList<Integer> gamePattern = new ArrayList<>();
    private ArrayList<Integer> userPattern = new ArrayList<>();

    private SimonPad[] pads;

    private boolean playerTurn = false;
    private int score = 0;
    private int highScore = 0;

    @FXML
    public void initialize() {
        pads = new SimonPad[4];
        pads[0] = new RedPad(redButton);
        pads[1] = new GreenPad(greenButton);
        pads[2] = new YellowPad(yellowButton);
        pads[3] = new BluePad(blueButton);

        setColorButtonsDisabled(true);
        updateLabels();
    }

    @FXML
    private void handleStart() {
        score = 0;
        gamePattern.clear();
        userPattern.clear();
        playerTurn = false;

        updateLabels();
        addRandomStep();

        PauseTransition pause = new PauseTransition(Duration.seconds(0.4));
        pause.setOnFinished(e -> showPattern(0));
        pause.play();
    }

    @FXML
    private void handleRed() {
        handlePlayerClick(0);
    }

    @FXML
    private void handleGreen() {
        handlePlayerClick(1);
    }

    @FXML
    private void handleYellow() {
        handlePlayerClick(2);
    }

    @FXML
    private void handleBlue() {
        handlePlayerClick(3);
    }

    private void addRandomStep() {
        int nextNumber = (int) (Math.random() * 4);
        gamePattern.add(nextNumber);
    }

    private void handlePlayerClick(int number) {
        if (!playerTurn) {
            return;
        }

        userPattern.add(number);
        pads[number].flashAndPlay();

        int currentIndex = userPattern.size() - 1;

        if (!userPattern.get(currentIndex).equals(gamePattern.get(currentIndex))) {
            playerTurn = false;
            setColorButtonsDisabled(true);
            highScoreLabel.setText("Game Over");
            return;
        }

        if (userPattern.size() == gamePattern.size()) {
            playerTurn = false;
            setColorButtonsDisabled(true);

            score++;

            if (score > highScore) {
                highScore = score;
            }

            updateLabels();

            PauseTransition pause = new PauseTransition(Duration.seconds(1));
            pause.setOnFinished(e -> {
                userPattern.clear();
                addRandomStep();
                showPattern(0);
            });
            pause.play();
        }
    }

    private void showPattern(int index) {
        setColorButtonsDisabled(true);
        playerTurn = false;

        if (index >= gamePattern.size()) {
            userPattern.clear();
            playerTurn = true;
            setColorButtonsDisabled(false);
            return;
        }

        int number = gamePattern.get(index);
        pads[number].flashAndPlay();

        PauseTransition pause = new PauseTransition(Duration.seconds(0.8));
        pause.setOnFinished(e -> showPattern(index + 1));
        pause.play();
    }

    private void updateLabels() {
        scoreLabel.setText("Score: " + score);
        highScoreLabel.setText("High Score: " + highScore);
    }

    private void setColorButtonsDisabled(boolean disabled) {
        redButton.setDisable(disabled);
        greenButton.setDisable(disabled);
        yellowButton.setDisable(disabled);
        blueButton.setDisable(disabled);
    }
}