package main.arena.buffs;

import main.arena.combatants.Combatant;
import processing.core.PApplet;
import processing.core.PImage;

import static main.Main.sprites;

public class Sticky extends Buff {

    private final PImage SPRITE;

    public Sticky(PApplet p, Combatant combatant) {
        super(p, 2, 0, combatant);
        SPRITE = sprites.get("stickyBF");
    }

    @Override
    public void effect() {
        lifeTimer--;
        if (lifeTimer == 0) COMBATANT.sticky = null;
    }

    @Override
    public void display() {
        P.image(SPRITE, COMBATANT.position.x, COMBATANT.position.y);
    }
}
