package main.arena.combatants.team;

import main.arena.buffs.Shielded;
import main.arena.combatants.Combatant;
import main.arena.combatants.abilities.DefensiveAbility;
import processing.core.PApplet;

import java.awt.*;

import static main.Main.sound;
import static main.Main.sounds;
import static main.sound.SoundUtilities.playSoundRandomSpeed;

public class Shielder extends Combatant implements DefensiveAbility {

    public Shielder(PApplet p) {
        super(p, 150, 15, 5, 10, 0, new Color(150, 0, 0));
        loadAnimations("shielder");
        hurtSound = sounds.get("squish");
        abilitySound = sounds.get("metalPlace");
        abilityTriggerFrame = 3;
    }

    @Override
    public void ability(Combatant other) {
        mp -= mpCost;
        if (mp < 0) mp = 0;
        playSoundRandomSpeed(P, abilitySound, 1);
        other.shielded = new Shielded(P, other);
    }
}
