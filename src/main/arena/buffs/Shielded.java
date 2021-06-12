package main.arena.buffs;

import main.arena.combatants.Combatant;
import processing.core.PApplet;

public class Shielded extends Buff {

    public Shielded(PApplet p) {
        super(p, 2);
    }

    @Override
    public void effect(Combatant combatant) {
        lifeTimer--;
        if (lifeTimer == 0) combatant.shielded = null;
    }

    @Override
    public void display() {

    }
}
