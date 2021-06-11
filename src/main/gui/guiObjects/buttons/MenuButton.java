package main.gui.guiObjects.buttons;

import processing.core.PApplet;
import processing.core.PVector;

import static main.Main.*;
import static main.sound.SoundUtilities.playSound;
import static processing.core.PConstants.LEFT;

public class MenuButton extends Button {

    private boolean pressed;

    public MenuButton(PApplet p, float x, float y) {
        super(p, x, y);
        position = new PVector(x, y);
        size = new PVector(200, 42);
        spriteIdle = animations.get("genericButtonBT")[0];
        spritePressed = animations.get("genericButtonBT")[1];
        spriteHover = animations.get("genericButtonBT")[2];
        sprite = spriteIdle;
    }

    /**
     * If mouse over, push in.
     * Works if paused or dead.
     */
    @Override
    public void hover(){
        if (p.mouseX < position.x+size.x/2 && p.mouseX > position.x-size.x/2 && p.mouseY < position.y+size.y/2 &&
          p.mouseY > position.y-size.y/2) {
            sprite = spriteHover;
            if (inputHandler.leftMousePressedPulse) playSound(clickIn, 1, 1);
            if (p.mousePressed && p.mouseButton == LEFT) sprite = spritePressed;
            if (holdable && p.mousePressed && p.mouseButton == LEFT) action();
            else if (inputHandler.leftMouseReleasedPulse) {
                playSound(clickOut, 1, 1);
                action();
                sprite = spritePressed;
            }
        } else sprite = spriteIdle;
    }

    /**
     * This makes sense for some reason, but I forgot why
     */
    @Override
    public void action() {
        pressed = true;
    }

    /**
     * @return whether the mouse was recently pressed, resets pressed
     */
    public boolean isPressed() {
        boolean wasPressed = pressed;
        pressed = false;
        return wasPressed;
    }
}
