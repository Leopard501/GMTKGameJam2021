package main.arena.levelStructure;

import main.arena.combatants.Combatant;
import main.arena.combatants.enemies.*;
import main.arena.combatants.team.*;
import main.gui.guiObjects.Dialogue;
import processing.core.PApplet;
import processing.core.PVector;

import static main.Main.BOARD_SIZE;
import static main.misc.Utilities.getPositionFromSlot;
import static processing.core.PApplet.binary;

public class Level_1 extends Level {

    /**
     * Tutorial level.
     * Introduces skeletons, fighter and generally how to play the game.
     * Very easy.
     */
    public Level_1(PApplet p) {
        super(p);
        soundtrack = "dungeonCrawling";

        team = new Combatant[] {
          null,
          new Fighter(p),
          null
        };

        waves = new Combatant[][]{{
            null,
            new Skeleton(p)
        }, {
            new Skeleton(p),
            new Skeleton(p)
        }, {
            new Skeleton(p),
            new Skeleton(p),
            new Skeleton(p)
        }};
        for (Combatant[] combatants : waves) {
            for (Combatant combatant : combatants) {
                if (combatant == null) continue;
                combatant.isEnemy = true;
            }
        }

        dialogues = new Dialogue[][]{{
            new Dialogue(p, "Another long day of dungeon crawling...", getPositionFromSlot(1)),
            new Dialogue(p, "[Left click on skeleton to attack]", getPositionFromSlot(1)),
            new Dialogue(p, "GRRR", getPositionFromSlot(4))
        }, {
            new Dialogue(p, "I hope I get some good loot...", getPositionFromSlot(1)),
            new Dialogue(p, "UURRRGGG", getPositionFromSlot(4))
        }, {
            new Dialogue(p, "Uh oh, theres a lot of them!", getPositionFromSlot(1)),
            new Dialogue(p, "[Right click on skeleton to use ability]", getPositionFromSlot(1)),
            new Dialogue(p, "GRRAAAA", getPositionFromSlot(5)),
        }};
    }
}
