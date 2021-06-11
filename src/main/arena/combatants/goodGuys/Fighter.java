package main.arena.combatants.goodGuys;

import main.arena.combatants.Combatant;
import processing.core.PApplet;

public class Fighter extends Combatant {

    public Fighter(PApplet p, float x, float y) {
        super(p, x, y, 100, 25, 5, 10, 20);
    }

    @Override
    protected void secondaryAttackEffect(Combatant other) {
        //inflict bleeding
    }
}
