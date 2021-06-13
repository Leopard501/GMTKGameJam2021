package main.arena.combatants.enemies;

import main.arena.combatants.Combatant;
import main.arena.combatants.abilities.DefensiveAbility;
import processing.core.PApplet;

import java.awt.*;

import static java.lang.Math.round;
import static main.Main.sounds;
import static main.sound.SoundUtilities.playSoundRandomSpeed;

public class Router extends Combatant implements DefensiveAbility {

    public Router(PApplet p) {
        super(p, 60, 10, 5, 5, 25, new Color(255, 255, 0));
        hurtSound = sounds.get("metalDamage");
        abilitySound = sounds.get("badMagic");
        loadAnimations("router");
        attackTriggerFrame = 4;
    }

    @Override
    public void ability(Combatant other) {
        mp -= mpCost;
        if (mp < 0) mp = 0;
        int damage = attackDamage;
        float strength = abilityStrength;
        playSoundRandomSpeed(P, abilitySound, 1);
        if (statBoost != null) {
            damage = round(damage * statBoost.strength);
            strength *= statBoost.strength;
        }
        other.heal((int) strength);
    }
}
