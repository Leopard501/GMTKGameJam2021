package main.arena.combatants.enemies;

import main.arena.combatants.Combatant;
import processing.core.PApplet;

public class Android extends Combatant {

    public Android(PApplet p) {
        super(p, 50, 10, 2, 10, 20);
    }

    @Override
    protected void secondaryAttackEffect(Combatant other) {

    }
}
