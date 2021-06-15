package main.gui;

import main.Main;
import main.gui.guiObjects.buttons.MenuButton;
import main.sound.FadeSoundLoop;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

import java.awt.*;

import static main.Main.*;
import static main.misc.Utilities.shadowedText;
import static processing.core.PConstants.CENTER;
import static processing.core.PConstants.CORNER;

public class MainMenu {

    private static final int INCREASE_DARK = 5;

    private final PApplet P;
    private final PImage BACKGROUND;

    private boolean gettingDark;
    private int darkAmount;

    private MenuButton quitButton;
    private MenuButton playButton;

    /**
     * nothing, exiting, playing
     */
    private int exitState;

    public MainMenu(PApplet p) {
        P = p;

        darkAmount = 254;
        BACKGROUND = sprites.get("arenaBG");

        quitButton = new MenuButton(p, BOARD_SIZE.x / 2, (BOARD_SIZE.y / 2) + 50);
        playButton = new MenuButton(p, BOARD_SIZE.x / 2, (BOARD_SIZE.y / 2) + 25);
    }

    public void main() {
        update();
        display();
    }

    private void update() {
        quitButton.hover();
        if (quitButton.isPressed()) {
            for (FadeSoundLoop fadeSoundLoop : fadeSoundLoops.values()) fadeSoundLoop.setTargetVolume(0);
            gettingDark = true;
            exitState = 1;
        }
        playButton.hover();
        if (playButton.isPressed()) {
            gettingDark = true;
            exitState = 2;
        }

        if (darkAmount >= 255) {
            switch (exitState) {
                case 1:
                    P.exit();
                    break;
                case 2:
                    Main.inMainMenu = false;
                    break;
            }
        }
    }

    private void display() {
        P.imageMode(CORNER);
        P.image(BACKGROUND, 0, 0);
        P.imageMode(CENTER);

        shadowedText(P, "Adventurers & Monsters", new PVector(BOARD_SIZE.x / 2, (BOARD_SIZE.y / 2) - 50),
          new Color(200, 200, 200), new Color(87, 87, 87), 12, CENTER);
        shadowedText(P, "vs.", new PVector(BOARD_SIZE.x / 2, (BOARD_SIZE.y / 2) - 40),
          new Color(200, 200, 200), new Color(87, 87, 87), 10, CENTER);
        shadowedText(P, "the Machines", new PVector(BOARD_SIZE.x / 2, (BOARD_SIZE.y / 2) - 25),
          new Color(200, 200, 200), new Color(87, 87, 87), 12, CENTER);

        P.fill(20);
        P.textSize(10);
        P.textAlign(CENTER);
        playButton.main();
        P.text("Start", BOARD_SIZE.x / 2, (BOARD_SIZE.y / 2) + 28);
        quitButton.main();
        P.text("Quit", BOARD_SIZE.x / 2, (BOARD_SIZE.y / 2) + 53);

        if (gettingDark) {
            darkAmount += INCREASE_DARK;
            if (darkAmount >= 255) gettingDark = false;
        } else if (darkAmount > 0) darkAmount -= INCREASE_DARK;
        P.rectMode(CORNER);
        P.fill(0, darkAmount);
        P.noStroke();
        P.rect(0, 0, BOARD_SIZE.x + 1, BOARD_SIZE.y + 1);
        P.rectMode(CENTER);
    }
}
