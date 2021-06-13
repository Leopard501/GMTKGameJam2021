package main.arena.combatants.enemies;

import main.arena.buffs.Shielded;
import main.arena.combatants.Combatant;
import main.arena.combatants.abilities.DefensiveAbility;
import processing.core.PApplet;

import java.awt.*;

import static main.Main.sounds;
import static main.sound.SoundUtilities.playSoundRandomSpeed;

public class SmartSpeaker extends Combatant implements DefensiveAbility {

    public SmartSpeaker(PApplet p) {
        super(p, 80, 10, 5, 8, 0, new Color(0, 85, 255));
        hurtSound = sounds.get("metalDamage");
        abilitySound = sounds.get("ice");
        loadAnimations("smartSpeaker");
        attackTriggerFrame = 5;
        abilityTriggerFrame = 2;
    }

    @Override
    public void ability(Combatant other) {
        mp -= mpCost;
        if (mp < 0) mp = 0;
        playSoundRandomSpeed(P, abilitySound, 1);
        other.shielded = new Shielded(P, other);
    }
}
