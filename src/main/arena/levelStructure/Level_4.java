package main.arena.levelStructure;

import main.arena.combatants.Combatant;
import main.arena.combatants.enemies.Android;
import main.arena.combatants.enemies.Drone;
import main.arena.combatants.team.Fighter;
import main.arena.combatants.team.Healer;
import main.arena.combatants.team.Shielder;
import main.gui.guiObjects.Dialogue;
import processing.core.PApplet;

import static main.misc.Utilities.getPositionFromSlot;

public class Level_4 extends Level {

    public Level_4(PApplet p) {
        super(p);

        team = new Combatant[] {
          new Fighter(p),
          new Healer(p),
          new Shielder(p)
        };

        waves = new Combatant[][]{{
            null,
            new Android(p)
        }, {
            new Android(p),
            new Android(p)
        }, {
            new Android(p),
            new Android(p),
            new Android(p)
        }, {
            null,
            new Drone(p)
        }};
        for (Combatant[] combatants : waves) {
            for (Combatant combatant : combatants) {
                if (combatant == null) continue;
                combatant.isEnemy = true;
            }
        }

        dialogues = new Dialogue[][]{{
            new Dialogue(p, "What is that?", getPositionFromSlot(0)),
            new Dialogue(p, "GREETINGS ADVENTURERS", getPositionFromSlot(4)),
            new Dialogue(p, "THIS DUNGEON HAS BEEN BOUGHT", getPositionFromSlot(4)),
            new Dialogue(p, "BY SININSTERCORP(tm)", getPositionFromSlot(4)),
            new Dialogue(p, "ADVENTURING HAS BEEN AUTOMATED", getPositionFromSlot(4)),
            new Dialogue(p, "PLEASE EXIT THE PREMISES IMMEDIATELY", getPositionFromSlot(4)),
            new Dialogue(p, "No way!", getPositionFromSlot(1)),
            new Dialogue(p, "This is our dungeon!", getPositionFromSlot(1))
        }, {
            new Dialogue(p, "STOP RESISTING", getPositionFromSlot(3)),
            new Dialogue(p, "IF YOU CONTINUE WE WILL", getPositionFromSlot(3)),
            new Dialogue(p, "TO SEND REINFORCEMENTS", getPositionFromSlot(3)),
            new Dialogue(p, "Bring it on!", getPositionFromSlot(0))
        }, {
            new Dialogue(p, "STOP RESISTING", getPositionFromSlot(5)),
            new Dialogue(p, "RESISTANCE WILL BE MET WITH", getPositionFromSlot(5)),
            new Dialogue(p, "HORDES OF MOOKS", getPositionFromSlot(5))
        }, {
            new Dialogue(p, "WHIRRRRR", getPositionFromSlot(4)),
            new Dialogue(p, "Is that thing FLYING!?", getPositionFromSlot(2))
        }};
    }
}
