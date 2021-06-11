package main.arena.buffs;

import main.arena.combatants.Combatant;
import processing.core.PApplet;

public abstract class Buff {

    public int lifeTimer;

    protected final PApplet P;

    public Buff(PApplet p, int lifeTimer) {
        P = p;
        this.lifeTimer = lifeTimer;
    }

    public abstract void effect(Combatant other);

    public abstract void display();
}
