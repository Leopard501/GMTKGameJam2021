package main.arena.combatants.enemies;

import main.arena.combatants.Combatant;
import main.arena.combatants.abilities.DefensiveAbility;
import processing.core.PApplet;

import java.awt.*;

public class HealBot extends Combatant implements DefensiveAbility {

    public HealBot(PApplet p) {
        super(p, 40, 10, 5, 5, 15, new Color(0, 125, 255));
    }

    @Override
    public void ability(Combatant other) {
        mp -= mpCost;
        if (mp < 0) mp = 0;
        other.heal(abilityStrength);
    }
}
