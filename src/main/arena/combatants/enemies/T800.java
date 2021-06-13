package main.arena.combatants.enemies;

import main.arena.buffs.Bleeding;
import main.arena.combatants.Combatant;
import main.arena.combatants.abilities.OffensiveAbility;
import processing.core.PApplet;

import java.awt.*;

import static java.lang.Math.round;
import static main.Main.sounds;

public class T800 extends Combatant implements OffensiveAbility {

    public T800(PApplet p) {
        super(p, 100, 5, 5, 20, 20, new Color(29, 23, 0));
        hurtSound = sounds.get("metalBreak");
        loadAnimations("t800");
        abilityTriggerFrame = 17;
        attackTriggerFrame = 18;
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
