package main.arena.buffs;

import main.arena.combatants.Combatant;
import processing.core.PApplet;

public class Bleeding extends Buff {

    public Bleeding(PApplet p) {
        super(p, 2);
    }

    @Override
    public void effect(Combatant other) {
        other.hurt(10);
    }

    @Override
    public void display() {

    }
}
