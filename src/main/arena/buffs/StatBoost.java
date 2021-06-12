package main.arena.buffs;

import main.Main;
import main.arena.combatants.Combatant;
import main.arena.particles.FloatParticle;
import main.arena.particles.GravityParticle;
import processing.core.PApplet;

import java.awt.*;

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
        if (P.random(0, 15) < 1) {
            Main.arena.particles.add(new FloatParticle(P, COMBATANT.position.x, COMBATANT.position.y, Color.RED));
        }
    }
}
