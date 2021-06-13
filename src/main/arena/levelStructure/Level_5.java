package main.arena.levelStructure;

import main.arena.combatants.Combatant;
import main.arena.combatants.team.*;
import main.gui.guiObjects.Dialogue;
import processing.core.PApplet;

import static main.misc.Utilities.getPositionFromSlot;

public class Level_5 extends Level {

    /**
     * Cutscene.
     */
    public Level_5(PApplet p) {
        super(p);
        isCutscene = true;

        team = new Combatant[]{
          new Fighter(p),
          new Healer(p),
          new Shielder(p)
        };

        waves = new Combatant[][]{{
          new Slime(p),
          new Necromancer(p),
          new Spider(p)
        }};
        for (Combatant[] combatants : waves) {
            for (Combatant combatant : combatants) {
                if (combatant == null) continue;
                combatant.isEnemy = true;
            }
        }

        dialogues = new Dialogue[][]{{
           new Dialogue(p, "GRAHAHAHAHA", getPositionFromSlot(4)),
           new Dialogue(p, "Oh no, not this guy again...", getPositionFromSlot(2)),
           new Dialogue(p, "GRAHAHA- KHA-", getPositionFromSlot(4)),
           new Dialogue(p, "*cough* *cough*", getPositionFromSlot(4)),
           new Dialogue(p, "Uh, this is awkward...", getPositionFromSlot(4)),
           new Dialogue(p, "!?", getPositionFromSlot(1)),
           new Dialogue(p, "GLORP", getPositionFromSlot(3)),
           new Dialogue(p, "As you may have noticed...", getPositionFromSlot(4)),
           new Dialogue(p, "...this dungeon has been overrun by robots.", getPositionFromSlot(4)),
           new Dialogue(p, "We've noticed.", getPositionFromSlot(0)),
           new Dialogue(p, "...and we'd like your help getting rid of them.", getPositionFromSlot(4)),
           new Dialogue(p, "WHAT!?", getPositionFromSlot(1)),
           new Dialogue(p, "HISS", getPositionFromSlot(5)),
           new Dialogue(p, "We own and manage this dungeon", getPositionFromSlot(4)),
           new Dialogue(p, "and we don't want robots running around", getPositionFromSlot(4)),
           new Dialogue(p, "pretending to own the place.", getPositionFromSlot(4)),
           new Dialogue(p, "And they smell weird.", getPositionFromSlot(4)),
           new Dialogue(p, "...like new plastic.", getPositionFromSlot(4)),
           new Dialogue(p, "So whatd'ya say?", getPositionFromSlot(4)),
           new Dialogue(p, "Whats in it for us?", getPositionFromSlot(0)),
           new Dialogue(p, "Well for one you wont have to", getPositionFromSlot(4)),
           new Dialogue(p, "fight through robots to raid the dungeon.", getPositionFromSlot(4)),
           new Dialogue(p, "...also we'll give you lots of loot.", getPositionFromSlot(4)),
           new Dialogue(p, "Deal.", getPositionFromSlot(0))
        }};
    }
}
