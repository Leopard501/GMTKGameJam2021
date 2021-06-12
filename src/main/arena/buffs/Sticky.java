package main.arena.buffs;

import main.arena.combatants.Combatant;
import processing.core.PApplet;

public class Sticky extends Buff {

    public Sticky(PApplet p, Combatant combatant) {
        super(p, 2, 0, combatant);
    }

    @Override
    public void effect() {
        lifeTimer--;
        if (lifeTimer == 0) COMBATANT.sticky = null;
    }

    @Override
    public void display() {

    }
}
