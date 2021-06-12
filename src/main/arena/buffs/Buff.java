package main.arena.buffs;

import main.arena.combatants.Combatant;
import processing.core.PApplet;

public abstract class Buff {

    public int lifeTimer;
    public float strength;

    protected final PApplet P;

    public Buff(PApplet p, int lifeTimer, float strength) {
        P = p;
        this.lifeTimer = lifeTimer;
    }

    public abstract void effect(Combatant combatant);

    public abstract void display();
}
