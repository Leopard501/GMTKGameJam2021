package main.arena.levelStructure;

import main.arena.combatants.Combatant;
import main.arena.combatants.enemies.*;
import main.arena.combatants.team.Necromancer;
import main.arena.combatants.team.Slime;
import main.arena.combatants.team.Spider;
import main.gui.guiObjects.Dialogue;
import processing.core.PApplet;

import static main.misc.Utilities.getPositionFromSlot;

public class Level_9 extends Level {

    /**
     * FINAL BATTLE!
     * Introduces T800s
     * The monsters finally get their revenge...
     */
    public Level_9(PApplet p) {
        super(p);

        team = new Combatant[] {
          new Necromancer(p),
          new Spider(p),
          new Slime(p)
        };

        waves = new Combatant[][] {{
            new Router(p),
            new Drone(p),
            new PizzaBot(p)
        }, {
            new SmartSpeaker(p),
            new SmartSpeaker(p),
            new SmartSpeaker(p)
        }, {
            new Router(p),
            new T800(p),
            new Android(p)
        }, {
            new T800(p),
            new T800(p),
            new T800(p)
        }};
        for (Combatant[] combatants : waves) {
            for (Combatant combatant : combatants) {
                if (combatant == null) continue;
                combatant.isEnemy = true;
            }
        }

        dialogues = new Dialogue[][] {{
            new Dialogue(p, "To battle my friends!", getPositionFromSlot(0)),
            new Dialogue(p, "Lets end the mechanical menace!", getPositionFromSlot(0)),
            new Dialogue(p, "GLORP", getPositionFromSlot(2)),
            new Dialogue(p, "HISS", getPositionFromSlot(1)),
            new Dialogue(p, ":O", getPositionFromSlot(5))
        }, {
            new Dialogue(p, "Theres more of them!!", getPositionFromSlot(0)),
            new Dialogue(p, "//\\\\//\\/\\/\\\\/\\", getPositionFromSlot(4)),
            new Dialogue(p, "\\/\\\\////\\/\\\\/\\", getPositionFromSlot(3)),
            new Dialogue(p, "*rolls around on the floor*", getPositionFromSlot(5))
        }, {
            new Dialogue(p, "YOU WILL FAIL ON YOUR QUEST", getPositionFromSlot(4)),
            new Dialogue(p, "WE ARE LEGION", getPositionFromSlot(4)),
            new Dialogue(p, "WE ARE ENDLESS", getPositionFromSlot(4)),
            new Dialogue(p, "BEEBEEBEEBEEB", getPositionFromSlot(3)),
            new Dialogue(p, "STOP RESISTING", getPositionFromSlot(5)),
            new Dialogue(p, "But I'm undead...", getPositionFromSlot(0)),
            new Dialogue(p, "I have an eternity to fight!", getPositionFromSlot(0)),
            new Dialogue(p, "HISS", getPositionFromSlot(1)),
            new Dialogue(p, "GLERBLE", getPositionFromSlot(2))
        }, {
            new Dialogue(p, "Oh look,", getPositionFromSlot(0)),
            new Dialogue(p, "there's only three left.", getPositionFromSlot(0)),
            new Dialogue(p, "ERRR... UH...", getPositionFromSlot(4)),
            new Dialogue(p, "WE WILL STILL WIN", getPositionFromSlot(5)),
            new Dialogue(p, "THERES MORE OF US...", getPositionFromSlot(3)),
            new Dialogue(p, "UH...", getPositionFromSlot(3)),
            new Dialogue(p, "IN ANOTHER DUNGEON!", getPositionFromSlot(4)),
            new Dialogue(p, "YEAH!", getPositionFromSlot(3)),
            new Dialogue(p, "THEY WILL DESTROY YOU", getPositionFromSlot(5)),
            new Dialogue(p, "C'mon,", getPositionFromSlot(0)),
            new Dialogue(p, "lets end this.", getPositionFromSlot(0)),
            new Dialogue(p, "GLERP", getPositionFromSlot(2)),
            new Dialogue(p, "HISS", getPositionFromSlot(1))
        }};
    }
}
