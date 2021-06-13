package main.arena.levelStructure;

import main.arena.combatants.Combatant;
import main.arena.combatants.enemies.Android;
import main.arena.combatants.enemies.Drone;
import main.arena.combatants.enemies.Router;
import main.arena.combatants.team.Fighter;
import main.arena.combatants.team.Healer;
import main.arena.combatants.team.Slime;
import main.gui.guiObjects.Dialogue;
import processing.core.PApplet;

import static main.misc.Utilities.getPositionFromSlot;

public class Level_6 extends Level {

    /**
     * Introduce router
     */
    public Level_6(PApplet p) {
        super(p);

        team = new Combatant[]{
          new Fighter(p),
          new Healer(p),
          new Slime(p)
        };

        waves = new Combatant[][]{{
            new Android(p),
            null,
            new Android(p)
        }, {
            new Android(p),
            new Android(p),
            new Android(p)
        }, {
            new Android(p),
            new Drone(p),
            new Android(p)
        }, {
            new Android(p),
            new Android(p),
            new Router(p)
        }, {
            new Router(p),
            new Drone(p),
            new Android(p)
        }};
        for (Combatant[] combatants : waves) {
            for (Combatant combatant : combatants) {
                if (combatant == null) continue;
                combatant.isEnemy = true;
            }
        }

        dialogues = new Dialogue[][]{{
            new Dialogue(p, "GLORP", getPositionFromSlot(2)),
            new Dialogue(p, "Can you secretly speak too?", getPositionFromSlot(1)),
            new Dialogue(p, "GLERP", getPositionFromSlot(2)),
            new Dialogue(p, "Doesn't look like it.", getPositionFromSlot(0))
        }, {
            new Dialogue(p, "DO NOT RESIST", getPositionFromSlot(4))
        }, {
            new Dialogue(p, "WHIRRRR", getPositionFromSlot(4)),
            new Dialogue(p, "I hate those things...", getPositionFromSlot(1)),
            new Dialogue(p, "GURGLE", getPositionFromSlot(2))
        }, {
            new Dialogue(p, "What the heck is that?", getPositionFromSlot(0)),
            new Dialogue(p, "...", getPositionFromSlot(5))
        }};
    }
}
