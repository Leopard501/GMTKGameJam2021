package main.arena.combatants.enemies;

import main.arena.combatants.Combatant;
import main.arena.combatants.abilities.DefensiveAbility;
import processing.core.PApplet;

import java.awt.*;

import static java.lang.Math.round;

public class Router extends Combatant implements DefensiveAbility {

    public Router(PApplet p) {
        super(p, 60, 10, 5, 5, 15, new Color(255, 255, 0));
        loadAnimations("router");
        attackTriggerFrame = 4;
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
        other.heal((int) strength);
    }
}
