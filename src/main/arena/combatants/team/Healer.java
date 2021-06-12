package main.arena.combatants.team;

import main.arena.combatants.Combatant;
import main.arena.combatants.abilities.DefensiveAbility;
import processing.core.PApplet;

import java.awt.*;

public class Healer extends Combatant implements DefensiveAbility {

    public Healer(PApplet p) {
        super(p, 80, 50, 10, 10, 25, new Color(150, 0, 0));
    }

    @Override
    public void ability(Combatant other) {
        mp -= mpCost;
        if (mp < 0) mp = 0;
        other.heal(abilityDamage);
    }
}
