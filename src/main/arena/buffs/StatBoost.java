package main.arena.buffs;

import main.arena.combatants.Combatant;
import processing.core.PApplet;

public class StatBoost extends Buff {

    public StatBoost(PApplet p, float amount) {
        super(p, 3, amount);
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
