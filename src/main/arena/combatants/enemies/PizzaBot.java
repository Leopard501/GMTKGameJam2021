package main.arena.combatants.enemies;

import main.arena.buffs.Sticky;
import main.arena.combatants.Combatant;
import main.arena.combatants.abilities.OffensiveAbility;
import processing.core.PApplet;

import java.awt.*;

public class PizzaBot extends Combatant implements OffensiveAbility {

    public PizzaBot(PApplet p) {
        super(p, 40, 10, 5, 8, 0, new Color(0, 125, 255));
    }

    @Override
    public void ability(Combatant other) {
        other.sticky = new Sticky(P, other);
    }
}
