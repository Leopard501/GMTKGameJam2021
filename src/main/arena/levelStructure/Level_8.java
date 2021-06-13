package main.arena.levelStructure;

import main.arena.combatants.Combatant;
import main.arena.combatants.enemies.*;
import main.arena.combatants.team.Healer;
import main.arena.combatants.team.Necromancer;
import main.arena.combatants.team.Shielder;
import main.gui.guiObjects.Dialogue;
import processing.core.PApplet;

import static main.misc.Utilities.getPositionFromSlot;

public class Level_8 extends Level {

    /**
     * Introduces smartSpeaker and playable necromancer
     */
    public Level_8(PApplet p) {
        super(p);

        team = new Combatant[] {
          new Necromancer(p),
          new Healer(p),
          new Shielder(p)
        };

        waves = new Combatant[][] {{
            new Drone(p),
            new PizzaBot(p),
            new Android(p)
        }, {
            new Android(p),
            new Router(p),
            new Drone(p)
        }, {
            new PizzaBot(p),
            new Router(p),
            new Drone(p)
        }, {
            new Android(p),
            new SmartSpeaker(p),
            new Android(p)
        }, {
            new SmartSpeaker(p),
            new PizzaBot(p),
            new Router(p)
        }};
        for (Combatant[] combatants : waves) {
            for (Combatant combatant : combatants) {
                if (combatant == null) continue;
                combatant.isEnemy = true;
            }
        }

        dialogues = new Dialogue[][] {{
            new Dialogue(p, "\"Ugh, that guys creepy\"", getPositionFromSlot(0)),
            new Dialogue(p, "Hmm?", getPositionFromSlot(2)),
            new Dialogue(p, "Oh, nothing", getPositionFromSlot(0)),
            new Dialogue(p, "...your moms creepy...", getPositionFromSlot(0)),
            new Dialogue(p, "XD", getPositionFromSlot(4))
        }, {
            new Dialogue(p, "S-sorry about that...", getPositionFromSlot(2)),
            new Dialogue(p, "I shouldn't have said that.", getPositionFromSlot(2)),
            new Dialogue(p, "Hey, I get it,", getPositionFromSlot(0)),
            new Dialogue(p, "being undead n'all.", getPositionFromSlot(0)),
            new Dialogue(p, "Hey, look at you guys making up!", getPositionFromSlot(1)),
            new Dialogue(p, "...", getPositionFromSlot(0))
        }, {
            new Dialogue(p, ">:(", getPositionFromSlot(3)),
            new Dialogue(p, "BOOP", getPositionFromSlot(4))
        }, {
            new Dialogue(p, "A... glowing cylinder?", getPositionFromSlot(1)),
            new Dialogue(p, "*falls over aggressively*", getPositionFromSlot(4)),
            new Dialogue(p, "Humans make the oddest things.", getPositionFromSlot(0))
        }, {
            new Dialogue(p, "...", getPositionFromSlot(0)),
            new Dialogue(p, "...thanks for helping us.", getPositionFromSlot(0)),
            new Dialogue(p, "It's uh, really no problem.", getPositionFromSlot(2)),
            new Dialogue(p, "No really, you don't know", getPositionFromSlot(0)),
            new Dialogue(p, "how much this means to us.", getPositionFromSlot(0)),
            new Dialogue(p, ":')", getPositionFromSlot(4)),
            new Dialogue(p, "Shut up.", getPositionFromSlot(0))
        }};
    }
}
