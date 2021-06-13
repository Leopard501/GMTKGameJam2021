package main.arena.combatants.team;

import main.arena.buffs.Bleeding;
import main.arena.combatants.Combatant;
import main.arena.combatants.abilities.OffensiveAbility;
import processing.core.PApplet;

import java.awt.*;

import static main.Main.sounds;
import static java.lang.Math.round;
import static main.sound.SoundUtilities.playSoundRandomSpeed;

public class Fighter extends Combatant implements OffensiveAbility {

    public Fighter(PApplet p) {
        super(p, 100, 20, 5, 20, 15, new Color(200, 0, 0));
        loadAnimations("fighter");
        hurtSound = sounds.get("squish");
        abilitySound = sounds.get("whooshImpact");
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
        playSoundRandomSpeed(P, abilitySound, 1);
        other.hurt(damage);
        other.bleeding = new Bleeding(P, strength, other);
    }
}
