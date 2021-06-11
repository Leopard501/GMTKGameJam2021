package main.arena.combatants.team;

import main.arena.combatants.Combatant;
import processing.core.PApplet;

public class Healer extends Combatant {

    public Healer(PApplet p) {
        super(p, 80, 20, 10, 10, 25);
    }

    @Override
    protected void abilityEffect(Combatant other) {
        other.heal(abilityDamage);
    }
}
