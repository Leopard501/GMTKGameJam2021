package main.arena.combatants.enemies;

import main.arena.combatants.Combatant;
import main.arena.combatants.abilities.SplashOffensiveAbility;
import processing.core.PApplet;

import java.awt.*;

import static java.lang.Math.round;
import static main.Main.sounds;
import static main.sound.SoundUtilities.playSoundRandomSpeed;

public class Drone extends Combatant implements SplashOffensiveAbility {

    public Drone(PApplet p) {
        super(p, 80, 10, 5, 10, 10, new Color(255, 255, 0));
        hurtSound = sounds.get("metalDamage");
        abilitySound = sounds.get("explosion");
        loadAnimations("drone");
        abilityTriggerFrame = 1;
        attackTriggerFrame = 5;
    }

    @Override
    public void ability(Combatant[] others) {
        mp -= mpCost;
        if (mp < 0) mp = 0;
        float strength = abilityStrength;
        playSoundRandomSpeed(P, abilitySound, 1);
        if (statBoost != null) strength *= statBoost.strength;
        for (Combatant other : others) {
            if (other == null || !other.alive) continue;
            other.hurt((int) strength);
        }
    }
}
