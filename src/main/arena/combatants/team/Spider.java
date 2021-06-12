package main.arena.combatants.team;

import main.arena.buffs.Sticky;
import main.arena.combatants.Combatant;
import main.arena.combatants.abilities.DamageAbility;
import processing.core.PApplet;

public class Spider extends Combatant implements DamageAbility {

    public Spider(PApplet p) {
        super(p, 80, 20, 5, 15, 0);
    }

    @Override
    public void ability(Combatant other) {
        other.sticky = new Sticky(P);
    }
}
