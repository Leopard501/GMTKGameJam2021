package main.arena.levelStructure;

import main.arena.combatants.Combatant;
import main.arena.combatants.team.*;
import main.gui.guiObjects.Dialogue;
import processing.core.PApplet;

import static main.misc.Utilities.getPositionFromSlot;

public class Level_10 extends Level {

    /**
     * Cutscene!
     * The monsters and adventures reflect on their journey and resolve to become friends.
     */
    public Level_10(PApplet p) {
        super(p);
        isCutscene = true;

        team = new Combatant[] {
          new Fighter(p),
          new Healer(p),
          new Shielder(p)
        };

        waves = new Combatant[][] {{
            new Spider(p),
            new Necromancer(p),
            new Slime(p)
        }};
        for (Combatant[] combatants : waves) {
            for (Combatant combatant : combatants) {
                if (combatant == null) continue;
                combatant.isEnemy = true;
            }
        }

        dialogues = new Dialogue[][] {{
            new Dialogue(p, "Phew", getPositionFromSlot(4)),
            new Dialogue(p, "That was a hard fight.", getPositionFromSlot(4)),
            new Dialogue(p, "HISS", getPositionFromSlot(3)),
            new Dialogue(p, "I bet,", getPositionFromSlot(2)),
            new Dialogue(p, "some of those robots were...", getPositionFromSlot(2)),
            new Dialogue(p, "...p-pretty freaky.", getPositionFromSlot(2)),
            new Dialogue(p, "What if one of you had died!?", getPositionFromSlot(1)),
            new Dialogue(p, "Oh don't worry,", getPositionFromSlot(4)),
            new Dialogue(p, "all monsters of the same type...", getPositionFromSlot(4)),
            new Dialogue(p, "...share a hivemind.", getPositionFromSlot(4)),
            new Dialogue(p, "We can't die unless we're all killed.", getPositionFromSlot(4)),
            new Dialogue(p, "...huh", getPositionFromSlot(1)),
            new Dialogue(p, "That's an odd detail to leave...", getPositionFromSlot(0)),
            new Dialogue(p, "...to the last minute.", getPositionFromSlot(0)),
            new Dialogue(p, "Wait you didn't know that?", getPositionFromSlot(4)),
            new Dialogue(p, "You thought you were killing...", getPositionFromSlot(4)),
            new Dialogue(p, "...individual monsters with...", getPositionFromSlot(4)),
            new Dialogue(p, "...individual lives this whole time?", getPositionFromSlot(4)),
            new Dialogue(p, "That is...worrying...", getPositionFromSlot(4)),
            new Dialogue(p, "SLORP", getPositionFromSlot(5)),
            new Dialogue(p, "Thank you for your help,", getPositionFromSlot(4)),
            new Dialogue(p, "we couldn't have done it without you.", getPositionFromSlot(4)),
            new Dialogue(p, "It was no problem!", getPositionFromSlot(1)),
            new Dialogue(p, "Now where's our money!", getPositionFromSlot(0)),
            new Dialogue(p, "Oh don't worry,", getPositionFromSlot(4)),
            new Dialogue(p, "a few chest are on their way.", getPositionFromSlot(4)),
            new Dialogue(p, "$!!", getPositionFromSlot(0)),
            new Dialogue(p, "Umm...", getPositionFromSlot(2)),
            new Dialogue(p, "...it was n-nice...", getPositionFromSlot(2)),
            new Dialogue(p, "...getting to know you.", getPositionFromSlot(2)),
            new Dialogue(p, "Uh...", getPositionFromSlot(4)),
            new Dialogue(p, "yeah.", getPositionFromSlot(4)),
            new Dialogue(p, "I would be happy to fight you...", getPositionFromSlot(4)),
            new Dialogue(p, "...on your next expedition.", getPositionFromSlot(4)),
            new Dialogue(p, "HISSSS", getPositionFromSlot(3)),
            new Dialogue(p, "Hey,", getPositionFromSlot(0)),
            new Dialogue(p, "I'm just happy to get paid.", getPositionFromSlot(0))
        }};
    }
}
