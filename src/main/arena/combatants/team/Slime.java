package main.arena.combatants.team;

import main.arena.combatants.Combatant;
import main.arena.combatants.abilities.SplashOffensiveAbility;
import processing.core.PApplet;

import java.awt.*;

public class Slime extends Combatant implements SplashOffensiveAbility {

    public Slime(PApplet p) {
        super(p, 120, 20, 5, 15, 10, new Color(46, 201, 46));
    }

    @Override
    public void ability(Combatant[] others) {
        mp -= mpCost;
        if (mp < 0) mp = 0;
        for (Combatant other : others) {
            if (other == null) continue;
            other.hurt(abilityStrength);
        }
    }
}
