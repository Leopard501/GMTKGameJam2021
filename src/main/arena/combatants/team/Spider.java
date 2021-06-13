package main.arena.combatants.team;

import main.arena.buffs.Sticky;
import main.arena.combatants.Combatant;
import main.arena.combatants.abilities.OffensiveAbility;
import processing.core.PApplet;
import processing.sound.SoundFile;

import java.awt.*;

import static main.Main.sounds;

public class Spider extends Combatant implements OffensiveAbility {

    public Spider(PApplet p) {
        super(p, 80, 20, 5, 15, 10, new Color(123, 200, 0));
        hurtSound = sounds.get("crunchSquish");
        loadAnimations("spider");
        abilityTriggerFrame = 9;
    }

    @Override
    public void ability(Combatant other) {
        mp -= mpCost;
        if (mp < 0) mp = 0;
        other.sticky = new Sticky(P, other);
    }
}
