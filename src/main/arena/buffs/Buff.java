package main.arena.buffs;

import main.arena.combatants.Combatant;
import processing.core.PApplet;

public abstract class Buff {

    public int lifeTimer;
    public float strength;

    protected final PApplet P;
    protected final Combatant COMBATANT;

    public Buff(PApplet p, int lifeTimer, float strength, Combatant combatant) {
        P = p;
        this.lifeTimer = lifeTimer;
        this.strength = strength;
        COMBATANT = combatant;
    }

    public abstract void effect();

    public abstract void display();
}
