package main.arena.combatants.enemies;

import main.arena.combatants.Combatant;
import main.arena.combatants.abilities.SplashOffensiveAbility;
import processing.core.PApplet;

import java.awt.*;

public class Drone extends Combatant implements SplashOffensiveAbility {

    public Drone(PApplet p) {
        super(p, 50, 10, 5, 10, 15, new Color(255, 255, 0));
    }

    @Override
    public void ability(Combatant[] others) {
        mp -= mpCost;
        if (mp < 0) mp = 0;
        for (Combatant other : others) {
            if (other == null) continue;
            other.hurt((int) abilityStrength);
        }
    }
}
