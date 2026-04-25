/**
 * Date: 4/24/2026
 * 
 * For Simon says color memory game project in class CSC-331
 * 
 * Purpose: 
 */
package application;

// Imports needed JavaFX classes and ArrayList for game logic.
import java.util.ArrayList;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class SimonController {
    //Handles all game behavior

    //FXML to java varibles
    @FXML private Button redButton;
    @FXML private Button greenButton;
    @FXML private Button yellowButton;
    @FXML private Button blueButton;
    @FXML private Button startButton;

    @FXML private Label scoreLabel;
    @FXML private Label highScoreLabel;

    //keeps track of the computers pattern and the players input pattern.
    private ArrayList<Integer> gamePattern = new ArrayList<>();
    private ArrayList<Integer> userPattern = new ArrayList<>();

    //Holds blue, yellow, green, and red pads
    private SimonPad[] pads;

    // These are vairbles fo score, highscore, and playerturn which keeps 
    // track of when the play is allowed to click.
    private boolean playerTurn = false;
    private int score = 0;
    private int highScore = 0;

    @FXML
    public void initialize() {
        //Assign the pad object to its button
        pads = new SimonPad[4];
        pads[0] = new RedPad(redButton);
        pads[1] = new GreenPad(greenButton);
        pads[2] = new YellowPad(yellowButton);
        pads[3] = new BluePad(blueButton);

        setColorButtonsDisabled(true); //Disables buttons so they cannot be clicked
        updateLabels(); //Updates scores
    }

    @FXML
    private void handleStart() {
        //Resets everything to what it should be at the start of a new game
        score = 0;
        gamePattern.clear();
        userPattern.clear();
        playerTurn = false;

        updateLabels();//Updates Scores
        addRandomStep();//Adds random color sequence

        //Displays the random color sequence pattern
        PauseTransition pause = new PauseTransition(Duration.seconds(0.4));
        pause.setOnFinished(e -> showPattern(0));
        pause.play();
    }

    @FXML
    private void handleRed() {
        //When red button is pressed the number associated with that pad is 
        //sent to the handler
        handlePlayerClick(0);
    }

    @FXML
    private void handleGreen() {
        //When green button is pressed the number associated with that pad is 
        //sent to the handler
        handlePlayerClick(1);
    }

    @FXML
    private void handleYellow() {
        //When yellow button is pressed the number associated with that pad is 
        //sent to the handler
        handlePlayerClick(2);
    }

    @FXML
    private void handleBlue() {
        //When blue button is pressed the number associated with that pad is 
        //sent to the handler
        handlePlayerClick(3);
    }

    private void addRandomStep() {
        //Adds a random color 0–3 to the game pattern.
        int nextNumber = (int) (Math.random() * 4);
        gamePattern.add(nextNumber);
    }

    private void handlePlayerClick(int number) {
        if (!playerTurn) {
            //If the player clicks when it is not their turn the action is ignored
            return;
        }

        //Keeps tack of cursor even (a click) and flashes the correct color
        userPattern.add(number);
        pads[number].flashAndPlay();

        //Index of most recent click
        int currentIndex = userPattern.size() - 1;

        // If the player makes a mistake end the game
        if (!userPattern.get(currentIndex).equals(gamePattern.get(currentIndex))) {
            playerTurn = false;
            setColorButtonsDisabled(true);
            highScoreLabel.setText("Game Over");
            return;
        }

        //Player finished the whole pattern correctly
        if (userPattern.size() == gamePattern.size()) {
            playerTurn = false;
            setColorButtonsDisabled(true);

            score++; //Score is increased

            if (score > highScore) {
                //Sets highscore
                highScore = score;
            }

            updateLabels();// Updates score

            //adds a new step and shows the next pattern
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
        //disables user input while showing the pattern
        setColorButtonsDisabled(true);
        playerTurn = false;

        if (index >= gamePattern.size()) {
            //Pattern finished and it is not shifting to user input so the player can now repeat it
            userPattern.clear();
            playerTurn = true;
            setColorButtonsDisabled(false);
            return;
        }
        //Flashes current steps
        int number = gamePattern.get(index);
        pads[number].flashAndPlay();

        //Shows net step in sequence after a delay
        PauseTransition pause = new PauseTransition(Duration.seconds(0.8));
        pause.setOnFinished(e -> showPattern(index + 1));
        pause.play();
    }

    private void updateLabels() {
        //This is updating the text of the score and highscore
        scoreLabel.setText("Score: " + score);
        highScoreLabel.setText("High Score: " + highScore);
    }

    private void setColorButtonsDisabled(boolean disabled) {
        //Disables and enables color pads/buttons as needed
        redButton.setDisable(disabled);
        greenButton.setDisable(disabled);
        yellowButton.setDisable(disabled);
        blueButton.setDisable(disabled);
    }
}