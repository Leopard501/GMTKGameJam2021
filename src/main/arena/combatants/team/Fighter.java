package main.arena.combatants.team;

import main.arena.buffs.Bleeding;
import main.arena.combatants.Combatant;
import main.arena.combatants.abilities.DamageAbility;
import processing.core.PApplet;

public class Fighter extends Combatant implements DamageAbility {

    public Fighter(PApplet p) {
        super(p, 100, 10, 5, 20, 20);
    }

    @Override
    public void ability(Combatant other) {
        mp -= mpCost;
        if (mp < 0) mp = 0;
        other.hurt(abilityDamage);
        other.bleeding = new Bleeding(P);
    }
}
