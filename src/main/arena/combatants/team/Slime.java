package main.arena.combatants.team;

import main.arena.combatants.Combatant;
import main.arena.combatants.abilities.SplashAbility;
import processing.core.PApplet;

public class Slime extends Combatant implements SplashAbility {

    public Slime(PApplet p) {
        super(p, 120, 20, 8, 15, 10);
    }

    @Override
    public void ability(Combatant[] others) {
        mp -= mpCost;
        if (mp < 0) mp = 0;
        for (Combatant other : others) other.hurt(abilityDamage);
    }
}
