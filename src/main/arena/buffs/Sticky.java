package main.arena.buffs;

import main.arena.combatants.Combatant;
import processing.core.PApplet;

public class Sticky extends Buff {

    public Sticky(PApplet p) {
        super(p, 1);
    }

    @Override
    public void effect(Combatant combatant) {
        lifeTimer--;
        if (lifeTimer == 0) combatant.sticky = null;
    }

    @Override
    public void display() {

    }
}
