/**
 * Date: 4/24/2026
 * 
 * For Simon says color memory game project in class CSC-331
 * 
 * PURPOSE: SimonPad is where the color flashes and sounds are created and happen
 */
package application;

//JavaFX classes for button flashing and timing
import javafx.animation.PauseTransition;
import javafx.scene.control.Button;
import javafx.util.Duration;

//Imports the stuff needed to make the sounds
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Synthesizer;

public abstract class SimonPad {
    //Defines SimonPad

    //stores the pad's ID, its button, and its default style
    protected int number;
    protected Button button;
    protected String normalStyle;

    //synthesizer and channel for all pads
    private static Synthesizer synth;
    private static MidiChannel channel;
    private static boolean soundReady = false;

    public SimonPad(int number, Button button, String normalStyle) {
        //Saves the pad bumber, button, and style and ensures sound system is initialized
        this.number = number;
        this.button = button;
        this.normalStyle = normalStyle;
        initializeSound();
    }

    public int getNumber() {
        //Gets the pad number
        return number;
    }

    public void flashAndPlay() {
        //Plays sound and flashes correct color
        button.setStyle(getBrightStyle());
        playTone(getMidiNote(), 300);

        //After a delay it restores everything back to normal
        PauseTransition pause = new PauseTransition(Duration.seconds(0.35));
        pause.setOnFinished(e -> button.setStyle(normalStyle));
        pause.play();
    }

    private static void initializeSound() {
        //Initialize the sound when button is clicked/flashed
        if (soundReady) {
            //Prevents reinitializing sound more than once
            return;
        }

        try {
            //Gets and opens the sound channel
            synth = MidiSystem.getSynthesizer();
            synth.open();
            channel = synth.getChannels()[0];

            // 56 = trumpet, 80 = square lead, 81 = saw lead
            channel.programChange(81);

            soundReady = true;
        } catch (Exception e) {
            //If the sound cannot initialize this is the catch response
            System.out.println("Sound could not initialize.");
        }
    }

    private void playTone(int midiNote, int milliseconds) {
        //Plays the tone associated with each color pad
        if (!soundReady || channel == null) {
            //Skip playing the sound if the sound isn't ready
            return;
        }

        new Thread(() -> {
            try {
                //Plays sound
                channel.noteOn(midiNote, 110);
                Thread.sleep(milliseconds);
                channel.noteOff(midiNote);
            } catch (Exception e) {
                //If tone cannot play
                System.out.println("Tone could not play.");
            }
        }).start();
    }

    public abstract String getBrightStyle(); //style for the lit-up version

    public abstract int getMidiNote(); //note assigned to specified pad
}