package main.arena.buffs;

import main.Main;
import main.arena.combatants.Combatant;
import main.arena.particles.GravityParticle;
import processing.core.PApplet;

public class Bleeding extends Buff {

    public Bleeding(PApplet p, float strength, Combatant combatant) {
        super(p, 2, strength, combatant);
    }

    @Override
    public void effect() {
        lifeTimer--;
        COMBATANT.hurt((int) strength);
        if (lifeTimer == 0) COMBATANT.bleeding = null;
    }

    @Override
    public void display() {
        if (P.random(0, 15) < 1) {
            Main.arena.particles.add(new GravityParticle(P, COMBATANT.position.x, COMBATANT.position.y, COMBATANT.bloodColor));
        }
    }
}
