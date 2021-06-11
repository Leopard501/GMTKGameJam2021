package main.arena.combatants.team;

import main.arena.combatants.Combatant;
import processing.core.PApplet;

public class Fighter extends Combatant {

    public Fighter(PApplet p) {
        super(p, 100, 10, 5, 20, 40);
    }

    @Override
    protected void abilityEffect(Combatant other) {
        other.hurt(secondaryDamage);
    }
}
