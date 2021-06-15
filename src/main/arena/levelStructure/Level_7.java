package main.arena.levelStructure;

import main.arena.combatants.Combatant;
import main.arena.combatants.enemies.Android;
import main.arena.combatants.enemies.Drone;
import main.arena.combatants.enemies.PizzaBot;
import main.arena.combatants.enemies.Router;
import main.arena.combatants.team.Fighter;
import main.arena.combatants.team.Shielder;
import main.arena.combatants.team.Spider;
import main.gui.guiObjects.Dialogue;
import processing.core.PApplet;

import static main.misc.Utilities.getPositionFromSlot;

public class Level_7 extends Level {

    /**
     * Introduce pizzabot and playable spider
     */
    public Level_7(PApplet p) {
        super(p);

        team = new Combatant[] {
          new Fighter(p),
          new Spider(p),
          new Shielder(p)
        };

        waves = new Combatant[][] {{
            new Drone(p),
            new Router(p),
            new Drone(p)
        }, {
            new Android(p),
            new PizzaBot(p),
            new Router(p)
        }, {
            new PizzaBot(p),
            new Android(p),
            new PizzaBot(p)
        }};
        for (Combatant[] combatants : waves) {
            for (Combatant combatant : combatants) {
                if (combatant == null) continue;
                combatant.isEnemy = true;
            }
        }

        dialogues = new Dialogue[][] {{
            new Dialogue(p, "*shudder*", getPositionFromSlot(2)),
            new Dialogue(p, "Let's just get this over with.", getPositionFromSlot(2)),
            new Dialogue(p, "HISS", getPositionFromSlot(1))
        }, {
            new Dialogue(p, "Awww, its so cute!", getPositionFromSlot(2)),
            new Dialogue(p, ":)", getPositionFromSlot(4)),
            new Dialogue(p, "We still have to smash it.", getPositionFromSlot(0)),
            new Dialogue(p, ":(", getPositionFromSlot(4))
        }, {
            new Dialogue(p, "THE PIZZA BOTS ARE PROGRAMMED TO FEEL PAIN", getPositionFromSlot(4)),
            new Dialogue(p, "YOU ARE TERRIBLE PEOPLE", getPositionFromSlot(4)),
            new Dialogue(p, "But we're about to be rich!", getPositionFromSlot(0)),
            new Dialogue(p, ";~;", getPositionFromSlot(5)),
            new Dialogue(p, "HISS", getPositionFromSlot(1))
        }};
    }
}
