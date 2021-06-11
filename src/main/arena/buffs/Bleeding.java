package main.arena.buffs;

import main.arena.combatants.Combatant;
import processing.core.PApplet;

public class Bleeding extends Buff {

    public Bleeding(PApplet p) {
        super(p, 2);
    }

    @Override
    public void effect(Combatant combatant) {
        lifeTimer--;
        combatant.hurt(10);
        if (lifeTimer == 0) combatant.bleeding = null;
    }

    @Override
    public void display() {

    }
}
