package main.arena.combatants.enemies;

import main.arena.buffs.Pizza;
import main.arena.buffs.Sticky;
import main.arena.combatants.Combatant;
import main.arena.combatants.abilities.OffensiveAbility;
import processing.core.PApplet;

import java.awt.*;

import static main.Main.sounds;

public class PizzaBot extends Combatant implements OffensiveAbility {

    public PizzaBot(PApplet p) {
        super(p, 60, 10, 5, 8, 0, new Color(255, 185, 38));
        loadAnimations("pizzaBot");
        hurtSound = sounds.get("woodDamage");
        attackTriggerFrame = 5;
        abilityTriggerFrame = 1;
    }

    @Override
    public void ability(Combatant other) {
        mp -= mpCost;
        if (mp < 0) mp = 0;
        other.pizza = new Pizza(P, other);
    }
}
