package main.arena.combatants.enemies;

import main.arena.buffs.Bleeding;
import main.arena.combatants.Combatant;
import main.arena.combatants.abilities.OffensiveAbility;
import processing.core.PApplet;

import java.awt.*;

public class MetalDude extends Combatant implements OffensiveAbility {

    public MetalDude(PApplet p) {
        super(p, 100, 10, 5, 20, 10, new Color(29, 23, 0));
    }

    @Override
    public void ability(Combatant other) {
        mp -= mpCost;
        if (mp < 0) mp = 0;
        other.hurt(attackDamage);
        other.bleeding = new Bleeding(P, abilityStrength);
    }
}
