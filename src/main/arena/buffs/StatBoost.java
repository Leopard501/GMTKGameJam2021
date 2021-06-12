package main.arena.buffs;

import main.arena.combatants.Combatant;
import processing.core.PApplet;

public class StatBoost extends Buff {

    public StatBoost(PApplet p, float amount, Combatant combatant) {
        super(p, 3, amount, combatant);
    }

    @Override
    public void effect() {
        lifeTimer--;
        if (lifeTimer == 0) COMBATANT.shielded = null;
    }

    @Override
    public void display() {

    }
}
