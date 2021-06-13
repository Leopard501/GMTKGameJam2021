package main.arena.levelStructure;

import main.arena.combatants.Combatant;
import main.arena.combatants.enemies.Skeleton;
import main.arena.combatants.team.Fighter;
import main.arena.combatants.team.Healer;
import main.arena.combatants.team.Slime;
import main.gui.guiObjects.Dialogue;
import processing.core.PApplet;

import static main.misc.Utilities.getPositionFromSlot;

public class Level_2 extends Level {

    /**
     * Tutorial level.
     * Introduces healer and slime.
     * Quite easy.
     */
    public Level_2(PApplet p) {
        super(p);

        team = new Combatant[] {
          new Fighter(p),
          new Healer(p),
          null
        };

        waves = new Combatant[][]{{
            new Skeleton(p),
            new Skeleton(p)
        }, {
            new Skeleton(p),
            null,
            new Skeleton(p)
        }, {
            new Skeleton(p),
            new Skeleton(p),
            new Skeleton(p)
        }, {
            null,
            new Slime(p)
        }};
        for (Combatant[] combatants : waves) {
            for (Combatant combatant : combatants) {
                if (combatant == null) continue;
                combatant.isEnemy = true;
            }
        }

        dialogues = new Dialogue[][]{{
            new Dialogue(p, "Who're you?", getPositionFromSlot(0)),
            new Dialogue(p, "A fellow adventurer,", getPositionFromSlot(1)),
            new Dialogue(p, "raiding this dungeon like you.", getPositionFromSlot(1)),
            new Dialogue(p, "Want to work together?", getPositionFromSlot(0)),
            new Dialogue(p, "We can split the loot!", getPositionFromSlot(0)),
            new Dialogue(p, "Sure!", getPositionFromSlot(1)),
            new Dialogue(p, "UUGGGRRRR", getPositionFromSlot(4))
        }, {
            new Dialogue(p, "What's with the staff and robes?", getPositionFromSlot(0)),
            new Dialogue(p, "I've spec'd into healing!", getPositionFromSlot(1)),
            new Dialogue(p, "That means I can heal you or myself", getPositionFromSlot(1)),
            new Dialogue(p, "as my special ability.", getPositionFromSlot(1)),
            new Dialogue(p, "Cool!", getPositionFromSlot(0)),
            new Dialogue(p, "GRRAAAAGG", getPositionFromSlot(5))
        }, {
        }, {
            new Dialogue(p, "GLORP", getPositionFromSlot(4))
        }};
    }
}
