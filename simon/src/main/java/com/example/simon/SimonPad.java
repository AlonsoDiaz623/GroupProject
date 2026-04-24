package application;

import javafx.animation.PauseTransition;
import javafx.scene.control.Button;
import javafx.util.Duration;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Synthesizer;

public abstract class SimonPad {
    protected int number;
    protected Button button;
    protected String normalStyle;

    private static Synthesizer synth;
    private static MidiChannel channel;
    private static boolean soundReady = false;

    public SimonPad(int number, Button button, String normalStyle) {
        this.number = number;
        this.button = button;
        this.normalStyle = normalStyle;
        initializeSound();
    }

    public int getNumber() {
        return number;
    }

    public void flashAndPlay() {
        button.setStyle(getBrightStyle());
        playTone(getMidiNote(), 300);

        PauseTransition pause = new PauseTransition(Duration.seconds(0.35));
        pause.setOnFinished(e -> button.setStyle(normalStyle));
        pause.play();
    }

    private static void initializeSound() {
        if (soundReady) {
            return;
        }

        try {
            synth = MidiSystem.getSynthesizer();
            synth.open();
            channel = synth.getChannels()[0];

            // 56 = trumpet, 80 = square lead, 81 = saw lead
            channel.programChange(81);

            soundReady = true;
        } catch (Exception e) {
            System.out.println("Sound could not initialize.");
        }
    }

    private void playTone(int midiNote, int milliseconds) {
        if (!soundReady || channel == null) {
            return;
        }

        new Thread(() -> {
            try {
                channel.noteOn(midiNote, 110);
                Thread.sleep(milliseconds);
                channel.noteOff(midiNote);
            } catch (Exception e) {
                System.out.println("Tone could not play.");
            }
        }).start();
    }

    public abstract String getBrightStyle();

    public abstract int getMidiNote();
}