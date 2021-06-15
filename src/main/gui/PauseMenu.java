package main.gui;

import main.Main;
import main.arena.Arena;
import main.gui.guiObjects.buttons.MenuButton;
import main.sound.FadeSoundLoop;
import processing.core.PApplet;
import processing.core.PVector;

import java.awt.*;

import static main.Main.*;
import static main.misc.Utilities.shadowedText;
import static processing.core.PConstants.CENTER;
import static processing.core.PConstants.CORNER;

public class PauseMenu {

    public boolean locked;

    private static final int INCREASE_DARK = 5;

    private final PApplet P;

    private boolean gettingDark;
    private int darkAmount;
    private int exitState;
    private MenuButton quitButton;
    private MenuButton playButton;
    private MenuButton mainMenuButton;

    public PauseMenu(PApplet p) {
        P = p;

        quitButton = new MenuButton(p, BOARD_SIZE.x / 2, (BOARD_SIZE.y / 2) + 50);
        playButton = new MenuButton(p, BOARD_SIZE.x / 2, (BOARD_SIZE.y / 2));
        mainMenuButton = new MenuButton(p, BOARD_SIZE.x / 2, (BOARD_SIZE.y / 2) + 25);
    }

    public void main() {
        update();
        display();
        locked = false;
    }

    private void update() {
        quitButton.hover();
        if (quitButton.isPressed()) {
            gettingDark = true;
            exitState = 1;
            for (FadeSoundLoop fadeSoundLoop : fadeSoundLoops.values()) fadeSoundLoop.setTargetVolume(0);
        }
        mainMenuButton.hover();
        if (mainMenuButton.isPressed()) {
            gettingDark = true;
            exitState = 2;
            for (FadeSoundLoop fadeSoundLoop : fadeSoundLoops.values()) fadeSoundLoop.setTargetVolume(0);
        }
        playButton.hover();
        if (playButton.isPressed() && !locked) arena.paused = false;

        if (darkAmount >= 255) {
            switch (exitState) {
                case 1:
                    P.exit();
                    break;
                case 2:
                    Main.arena = new Arena(P);
                    Main.inMainMenu = true;
                    break;
            }
        }
    }

    private void display() {
        P.rectMode(CORNER);
        P.fill(0, 100);
        P.noStroke();
        P.rect(0, 0, BOARD_SIZE.x + 1, BOARD_SIZE.y + 1);

        shadowedText(P, "Paused", new PVector(BOARD_SIZE.x / 2, (BOARD_SIZE.y / 2) - 50),
          new Color(200, 200, 200), new Color(87, 87, 87), 12, CENTER);

        P.fill(20);
        P.textSize(10);
        P.textAlign(CENTER);
        playButton.main();
        P.text("Resume", BOARD_SIZE.x / 2, (BOARD_SIZE.y / 2) + 3);
        mainMenuButton.main();
        P.text("Main Menu", BOARD_SIZE.x / 2, (BOARD_SIZE.y / 2) + 28);
        quitButton.main();
        P.text("Quit", BOARD_SIZE.x / 2, (BOARD_SIZE.y / 2) + 53);

        if (gettingDark) {
            darkAmount += INCREASE_DARK;
            if (darkAmount >= 255) gettingDark = false;
        } else if (darkAmount > 0) darkAmount -= INCREASE_DARK;
        P.fill(0, darkAmount);
        P.noStroke();
        P.rect(0, 0, BOARD_SIZE.x + 1, BOARD_SIZE.y + 1);
        P.rectMode(CENTER);
    }
}
