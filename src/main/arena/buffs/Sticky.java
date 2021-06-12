package main.arena.buffs;

import main.arena.combatants.Combatant;
import processing.core.PApplet;
import processing.core.PImage;

import static main.Main.sprites;

public class Sticky extends Buff {

    private PImage sprite;

    public Sticky(PApplet p, Combatant combatant) {
        super(p, 2, 0, combatant);
        sprite = sprites.get("stickyBF");
    }

    @Override
    public void effect() {
        lifeTimer--;
        if (lifeTimer == 0) COMBATANT.sticky = null;
    }

    @Override
    public void display() {
        P.image(sprite, COMBATANT.position.x, COMBATANT.position.y);
    }
}
