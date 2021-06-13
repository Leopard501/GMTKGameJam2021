package main;

import main.arena.Arena;
import main.misc.InputHandler;
import main.sound.FadeSoundLoop;
import main.sound.SoundWithAlts;
import main.sound.StartStopSoundLoop;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import processing.sound.Sound;
import processing.sound.SoundFile;

import java.awt.*;
import java.util.HashMap;

import static java.lang.Character.toLowerCase;
import static main.misc.SpriteLoader.loadAnimations;
import static main.misc.SpriteLoader.loadSprites;
import static main.sound.SoundLoader.loadSounds;

public class Main extends PApplet {

    private static final Color BACKGROUND_COLOR = new Color(0, 10, 30);
    private static final boolean FULLSCREEN = true;
    private static final int FRAMERATE = 60;
    private static final String TITLE = "template";

    public static final PVector BOARD_SIZE = new PVector(250, 200);

    public static float globalVolume = 0.25f;

    private static float matrixScale;
    private static float matrixOffset;

    public static HashMap<String, PImage> sprites;
    public static HashMap<String, PImage[]> animations;

    public static Sound sound;
    public static HashMap<String, SoundFile> sounds;
    public static HashMap<String, StartStopSoundLoop> startStopSoundLoops;
    public static HashMap<String, FadeSoundLoop> fadeSoundLoops;
    public static HashMap<String, SoundWithAlts> soundsWithAlts;

    public static InputHandler inputHandler;
    public static InputHandler.KeyDS keysPressed;
    public static PVector matrixMousePosition;

    public static Arena arena;

    public static void main(String[] args) {
        PApplet.main("main.Main", args);
    }

    @Override
    public void settings() {
        size((int) BOARD_SIZE.x, (int) BOARD_SIZE.y);
        if (FULLSCREEN) {
            fullScreen();
            noSmooth();
        }
    }

    @Override
    public void setup() {
        frameRate(FRAMERATE);
        surface.setTitle(TITLE);
        rectMode(CENTER);
        imageMode(CENTER);
        ellipseMode(CENTER);

        setupSound();
        setupSprites();
        setupMisc();
        setupArena();
        setupFullscreen();
    }

    private void setupArena() {
        arena = new Arena(this);
    }

    private void setupMisc() {
        inputHandler = new InputHandler(this);
        keysPressed = new InputHandler.KeyDS();
    }

    private void setupFullscreen() {
        if (hasVerticalBars()) {
            matrixScale = height / BOARD_SIZE.y;
            matrixOffset = (width - (BOARD_SIZE.x * matrixScale)) / 2;
        } else {
            matrixScale = width / BOARD_SIZE.x;
            matrixOffset = (height - (BOARD_SIZE.y * matrixScale)) / 2;
        }
    }

    private void setupSound() {
        sound = new Sound(this);
        sounds = new HashMap<>();
        startStopSoundLoops = new HashMap<>();
        fadeSoundLoops = new HashMap<>();
        soundsWithAlts = new HashMap<>();
        loadSounds(this);
    }

    private void setupSprites() {
        sprites = new HashMap<>();
        animations = new HashMap<>();
        loadSprites(this);
        loadAnimations(this);
    }

    @Override
    public void draw() {
        background(BACKGROUND_COLOR.getRGB());
        drawSound();
        pushFullscreen();

        arena.main();

        inputHandler.reset();
        popFullscreen();
    }

    private void drawSound() {
        sound.volume(globalVolume);
        for (StartStopSoundLoop startStopSoundLoop : startStopSoundLoops.values()) startStopSoundLoop.continueLoop();
        for (FadeSoundLoop fadeSoundLoop : fadeSoundLoops.values()) fadeSoundLoop.main();
    }

    private void pushFullscreen() {
        pushMatrix();
        if (hasVerticalBars()) translate(matrixOffset, 0);
        else translate(0, matrixOffset);
        scale(matrixScale);
        if (hasVerticalBars()) {
            matrixMousePosition = new PVector((mouseX - matrixOffset) / matrixScale, mouseY / matrixScale);
        } else {
            matrixMousePosition = new PVector(mouseX / matrixScale, (mouseY - matrixOffset) / matrixScale);
        }
    }

    private void popFullscreen() {
        popMatrix();
        drawBlackBars();
    }

    private boolean hasVerticalBars() {
        float screenRatio = width / (float) height;
        float boardRatio = BOARD_SIZE.x / BOARD_SIZE.y;
        return boardRatio < screenRatio;
    }

    private void drawBlackBars() {
        fill(0);
        noStroke();
        rectMode(CORNER);
        if (hasVerticalBars()) {
            rect(0, 0, matrixOffset, height);
            rect(width - matrixOffset, 0, matrixOffset, height);
        } else {
            rect(0, 0, width, matrixOffset);
            rect(0, height - matrixOffset, width, matrixOffset);
        }
        rectMode(CENTER);
    }

    /**
     * Checks if a key is pressed.
     * Also includes overrides.
     */
    @Override
    public void keyPressed() {
        key = toLowerCase(key);
        if (keyCode == 8 || keyCode == 46) key = '*'; //delete or backspace
        if (keyCode == 9) key = '?'; //tab
        if (keyCode == 27) key = '|'; //esc
        if (keyCode == 37) key = '<'; //left
        if (keyCode == 38) key = '^'; //up
        if (keyCode == 39) key = '>'; //right
        if (keyCode == 40) key = '&'; //down
        inputHandler.key(true);
    }

    @Override
    public void keyReleased() {
        inputHandler.key(false);
    }

    @Override
    public void mousePressed() {
        inputHandler.mouse(true);
    }

    @Override
    public void mouseReleased() {
        inputHandler.mouse(false);
    }
}
