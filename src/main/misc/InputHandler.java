package main.misc;

import processing.core.PApplet;

import static main.Main.keysPressed;
import static processing.core.PConstants.LEFT;
import static processing.core.PConstants.RIGHT;

public class InputHandler {

    private final PApplet P;

    public boolean rightMouseReleasedPulse;
    public boolean leftMouseReleasedPulse;
    private boolean rightMousePressed;
    private boolean leftMousePressed;
    public boolean rightMousePressedPulse;
    public boolean leftMousePressedPulse;

    /**
     * Handles input from keyboard and mouse.
     * @param p the PApplet
     */
    public InputHandler(PApplet p) {
        this.P = p;
    }

    /**
     * Handles input from the mouse.
     * @param b mouse pressed
     */
    public void mouse(boolean b) {
        if (b) {
            if (P.mouseButton == RIGHT) {
                if (!rightMousePressed) rightMousePressedPulse = true;
                rightMousePressed = true;
            }
            if (P.mouseButton == LEFT) {
                if (!leftMousePressed) leftMousePressedPulse = true;
                leftMousePressed = true;
            }
        } else {
            if (rightMousePressed) {
                rightMouseReleasedPulse = true;
                rightMousePressed = false;
            }
            if (leftMousePressed) {
                leftMouseReleasedPulse = true;
                leftMousePressed = false;
            }
        }
    }

    /**
     * Handles input from the keyboard.
     * @param b any key pressed
     */
    public void key(boolean b) {
        for (KeyDS.KeyDSItem item : keysPressed.items) {
            if (item.key == P.key) {
                item.pressed = b;
                item.pressedPulse = b;
                item.releasedPulse = !b;
            }
        }
    }

    public void reset() {
        rightMouseReleasedPulse = false;
        leftMouseReleasedPulse = false;
        rightMousePressedPulse = false;
        leftMousePressedPulse = false;
        for (KeyDS.KeyDSItem key : keysPressed.items) {
            key.pressedPulse = false;
            key.releasedPulse = false;
        }
    }

    public static class KeyDS {

        public KeyDSItem[] items;

        /**
         * Contains all the keys from the keyboard
         */
        public KeyDS() {
            items = new KeyDSItem[0];
        }

        public void add(char key) {
            KeyDSItem[] newItems = new KeyDSItem[items.length + 1];
            System.arraycopy(items, 0, newItems, 0, items.length);
            newItems[items.length] = new KeyDSItem(key);
            items = newItems;
        }

        public boolean getPressed(char key) {
            boolean r = false;
            for (KeyDSItem item : items) if (item.key == key) r = item.pressed;
            return r;
        }

        public boolean getPressedPulse(char key) {
            boolean r = false;
            for (KeyDSItem item : items) if (item.key == key) r = item.pressedPulse;
            return r;
        }

        public boolean getReleasedPulse(char key) {
            boolean r = false;
            for (KeyDSItem item : items) if (item.key == key) r = item.releasedPulse;
            return r;
        }

        static class KeyDSItem {

            char key;
            boolean pressed;
            boolean pressedPulse;
            boolean releasedPulse;

            KeyDSItem(char key) {
                this.key = key;
                pressed = false;
            }
        }
    }
}
