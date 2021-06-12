package main.arena.combatants.enemies;

import main.arena.buffs.Shielded;
import main.arena.combatants.Combatant;
import main.arena.combatants.abilities.DefensiveAbility;
import processing.core.PApplet;

import java.awt.*;

public class ShieldBot extends Combatant implements DefensiveAbility {

    public ShieldBot(PApplet p) {
        super(p, 40, 15, 5, 8, 0, new Color(0, 125, 255));
    }

    @Override
    public void ability(Combatant other) {
        mp -= mpCost;
        if (mp < 0) mp = 0;
        other.shielded = new Shielded(P, other);
    }
}
