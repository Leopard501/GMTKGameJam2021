package main.arena.combatants.team;

import main.arena.combatants.Combatant;
import main.arena.combatants.abilities.DefensiveAbility;
import processing.core.PApplet;

import java.awt.*;

import static java.lang.Math.round;

public class Healer extends Combatant implements DefensiveAbility {

    public Healer(PApplet p) {
        super(p, 80, 50, 10, 15, 40, new Color(150, 0, 0));
        loadAnimations("healer");
        abilityTriggerFrame = 3;
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
