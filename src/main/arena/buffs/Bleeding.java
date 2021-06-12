package main.arena.buffs;

import main.arena.combatants.Combatant;
import processing.core.PApplet;

public class Bleeding extends Buff {

    public Bleeding(PApplet p, float strength) {
        super(p, 2, strength);
    }

    @Override
    public void effect(Combatant combatant) {
        lifeTimer--;
        combatant.hurt((int) strength);
        if (lifeTimer == 0) combatant.bleeding = null;
    }

    @Override
    public void display() {

    }
}
