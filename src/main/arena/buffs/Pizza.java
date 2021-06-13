package main.arena.buffs;

import main.arena.combatants.Combatant;
import processing.core.PApplet;
import processing.core.PImage;

import static main.Main.sprites;

public class Pizza extends Buff {

    private final PImage SPRITE;

    public Pizza(PApplet p, Combatant combatant) {
        super(p, 1, 0, combatant);
        SPRITE = sprites.get("pizzaBF");
    }

    @Override
    public void effect() {
        lifeTimer--;
        if (lifeTimer == 0) COMBATANT.pizza = null;
    }

    @Override
    public void display() {
        P.image(SPRITE, COMBATANT.position.x, COMBATANT.position.y);
    }
}
