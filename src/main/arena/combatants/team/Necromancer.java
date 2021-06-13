package main.arena.combatants.team;

import main.arena.buffs.StatBoost;
import main.arena.combatants.Combatant;
import main.arena.combatants.abilities.DefensiveAbility;
import processing.core.PApplet;

import java.awt.*;

import static main.Main.sounds;
import static main.sound.SoundUtilities.playSoundRandomSpeed;

public class Necromancer extends Combatant implements DefensiveAbility {

    public Necromancer(PApplet p) {
        super(p, 70, 20, 10, 25, 1.5f, new Color(255, 255, 200));
        loadAnimations("necromancer");
        hurtSound = sounds.get("crunch");
        abilitySound = sounds.get("darkMagic");
        abilityTriggerFrame = 3;
    }

    @Override
    public void ability(Combatant other) {
        mp -= mpCost;
        if (mp < 0) mp = 0;
        playSoundRandomSpeed(P, abilitySound, 1);
        other.statBoost = new StatBoost(P, abilityStrength, other);
    }
}
