package main.arena.combatants.team;

import main.arena.combatants.Combatant;
import main.arena.combatants.types.BuffAbility;
import processing.core.PApplet;

public class Healer extends Combatant implements BuffAbility {

    public Healer(PApplet p) {
        super(p, 80, 50, 10, 10, 25);
    }

    @Override
    public void buffAbility(Combatant other) {
        mp -= mpCost;
        if (mp < 0) mp = 0;
        other.heal(abilityDamage);
    }
}
