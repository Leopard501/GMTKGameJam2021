package main.arena.combatants.team;

import main.arena.buffs.Bleeding;
import main.arena.combatants.Combatant;
import main.arena.combatants.abilities.OffensiveAbility;
import processing.core.PApplet;

import java.awt.*;

import static java.lang.Math.round;

public class Fighter extends Combatant implements OffensiveAbility {

    public Fighter(PApplet p) {
        super(p, 100, 20, 5, 20, 15, new Color(150, 0, 0));
        loadAnimations("fighter");
    }

    @Override
    public void ability(Combatant other) {
        mp -= mpCost;
        if (mp < 0) mp = 0;
        int damage = attackDamage;
        float strength = abilityStrength;
        if (statBoost != null) {
            damage = round(damage * statBoost.strength);
            strength *= statBoost.strength;
        }
        other.hurt(damage);
        other.bleeding = new Bleeding(P, strength, other);
    }
}
