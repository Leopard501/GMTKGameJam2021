package main.sound;

import processing.core.PApplet;
import processing.sound.SoundFile;

import static main.misc.Utilities.incrementByTo;

public class FadeSoundLoop {

    private final int MIN_LENGTH;
    private final SoundFile SOUND_FILE;

    private int timer;
    private float volume;

    public float targetVolume;

    /**
     * A constantly playing loop that fades to audible then back to inaudible.
     * @param p the PApplet
     * @param name identifier
     * @param autoStopAfter how long it will run before automatically stopping, -1 to not auto stop
     */
    public FadeSoundLoop(PApplet p, String name, int autoStopAfter) {
        SOUND_FILE = new SoundFile(p, "sounds/loops/" + name + ".wav");
        MIN_LENGTH = autoStopAfter;
        SOUND_FILE.loop(1, 0.001f);
        targetVolume = 0.001f;
        //never goes to 0 because that throws errors for some reason :/
    }

    public void main() {
        volume = incrementByTo(volume, 0.01f, targetVolume);
        SOUND_FILE.amp(volume);
        if (timer > MIN_LENGTH && MIN_LENGTH != -1) {
            targetVolume = 0.01f;
        }
        timer++;
    }

    /**
     * @param targetVolume will slowly increment in volume to this
     */
    public void setTargetVolume(float targetVolume) {
        if (targetVolume <= 0) targetVolume = 0.001f;
        this.targetVolume = targetVolume;
        timer = 0;
    }
}
