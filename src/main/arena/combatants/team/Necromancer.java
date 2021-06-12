package main.arena.combatants.team;

import main.arena.buffs.StatBoost;
import main.arena.combatants.Combatant;
import main.arena.combatants.abilities.DefensiveAbility;
import processing.core.PApplet;

import java.awt.*;

public class Necromancer extends Combatant implements DefensiveAbility {

    public Necromancer(PApplet p) {
        super(p, 70, 60, 10, 15, 1.5f, new Color(255, 255, 200));
        loadAnimations("necromancer");
    }

    @Override
    public void ability(Combatant other) {
        mp -= mpCost;
        if (mp < 0) mp = 0;
        other.statBoost = new StatBoost(P, abilityStrength);
    }
}
