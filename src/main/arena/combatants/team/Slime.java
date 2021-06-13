package main.arena.combatants.team;

import main.arena.combatants.Combatant;
import main.arena.combatants.abilities.SplashOffensiveAbility;
import processing.core.PApplet;

import java.awt.*;

import static java.lang.Math.round;
import static main.Main.sounds;
import static main.sound.SoundUtilities.playSoundRandomSpeed;

public class Slime extends Combatant implements SplashOffensiveAbility {

    public Slime(PApplet p) {
        super(p, 120, 20, 5, 15, 15, new Color(46, 201, 46));
        hurtSound = sounds.get("splash");
        abilitySound = sounds.get("slimeSquish");
        loadAnimations("slime");
        abilityTriggerFrame = 8;
    }

    @Override
    public void ability(Combatant[] others) {
        mp -= mpCost;
        if (mp < 0) mp = 0;
        int damage = attackDamage;
        float strength = abilityStrength;
        playSoundRandomSpeed(P, abilitySound, 1);
        if (statBoost != null) {
            damage = round(damage * statBoost.strength);
            strength *= statBoost.strength;
        }
        for (Combatant other : others) {
            if (other == null) continue;
            other.hurt((int) strength);
        }
    }
}
