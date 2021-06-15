package main.arena.levelStructure;

import main.arena.combatants.Combatant;
import main.arena.combatants.enemies.Skeleton;
import main.arena.combatants.team.*;
import main.gui.guiObjects.Dialogue;
import processing.core.PApplet;

import static main.misc.Utilities.getPositionFromSlot;

public class Level_3 extends Level {

    /**
     * Tutorial level.
     * Introduces shielder, spider and necromancer.
     * Less easy.
     */
    public Level_3(PApplet p) {
        super(p);
        soundtrack = "dungeonCrawling";

        team = new Combatant[] {
          new Fighter(p),
          new Healer(p),
          new Shielder(p)
        };

        waves = new Combatant[][]{{
            new Skeleton(p),
            new Skeleton(p),
            new Skeleton(p)
        }, {
            null,
            new Spider(p)
        }, {
            new Skeleton(p),
            new Slime(p)
        }, {
            new Skeleton(p),
            new Necromancer(p),
            new Skeleton(p)
        }};
        for (Combatant[] combatants : waves) {
            for (Combatant combatant : combatants) {
                if (combatant == null) continue;
                combatant.isEnemy = true;
            }
        }

        dialogues = new Dialogue[][]{{
            new Dialogue(p, "Hi!", getPositionFromSlot(0)),
            new Dialogue(p, "Want to join our party?", getPositionFromSlot(0)),
            new Dialogue(p, "Uh, s-sure.", getPositionFromSlot(2)),
            new Dialogue(p, "GAAAAAHHH", getPositionFromSlot(4))
        }, {
            new Dialogue(p, "Cool shield and armor!", getPositionFromSlot(1)),
            new Dialogue(p, "You look very... uh...", getPositionFromSlot(0)),
            new Dialogue(p, "...well protected.", getPositionFromSlot(0)),
            new Dialogue(p, "Th-thanks.", getPositionFromSlot(2)),
            new Dialogue(p, "HISSSSS", getPositionFromSlot(4))
        }, {
            new Dialogue(p, "I can protect you t-too,", getPositionFromSlot(2)),
            new Dialogue(p, "if you want...", getPositionFromSlot(2)),
            new Dialogue(p, "My ability can make you", getPositionFromSlot(2)),
            new Dialogue(p, "temporarily invulnerable.", getPositionFromSlot(2)),
            new Dialogue(p, "...that could be useful.", getPositionFromSlot(1)),
            new Dialogue(p, "GLORP", getPositionFromSlot(4))
        }, {
            new Dialogue(p, "GRAHAHAHAHAAAAA", getPositionFromSlot(4)),
            new Dialogue(p, "ugh", getPositionFromSlot(2)),
            new Dialogue(p, "That guys creepy.", getPositionFromSlot(2)),
            new Dialogue(p, "AHAHAHAHAHHAAAAAA", getPositionFromSlot(4)),
            new Dialogue(p, "...rude...", getPositionFromSlot(4)),
            new Dialogue(p, "Wait what?", getPositionFromSlot(2))
        }};
    }
}
