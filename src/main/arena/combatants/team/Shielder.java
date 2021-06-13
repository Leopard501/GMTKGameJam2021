package main.arena.combatants.team;

import main.arena.buffs.Shielded;
import main.arena.combatants.Combatant;
import main.arena.combatants.abilities.DefensiveAbility;
import processing.core.PApplet;

import java.awt.*;

import static main.Main.sounds;

public class Shielder extends Combatant implements DefensiveAbility {

    public Shielder(PApplet p) {
        super(p, 150, 15, 5, 10, 0, new Color(150, 0, 0));
        loadAnimations("shielder");
        hurtSound = sounds.get("squish");
        abilityTriggerFrame = 3;
    }

    @Override
    public void ability(Combatant other) {
        mp -= mpCost;
        if (mp < 0) mp = 0;
        other.shielded = new Shielded(P, other);
    }
}
