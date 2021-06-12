package main.arena.buffs;

import main.arena.combatants.Combatant;
import processing.core.PApplet;

public class Shielded extends Buff {

    public Shielded(PApplet p, Combatant combatant) {
        super(p, 2, 0, combatant);
    }

    @Override
    public void effect() {
        lifeTimer--;
        if (lifeTimer <= 0) COMBATANT.shielded = null;
    }

    @Override
    public void display() {

    }
}
