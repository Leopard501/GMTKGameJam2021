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

    public Level_1(PApplet p) {
        super(p);

        team = new Combatant[] {
          new Fighter(p)
        };

        waves = new Combatant[][]{{
            new T800(p),
            new Drone(p),
            new Router(p)
          }, {
          new Spider(p),
          new Slime(p),
          new Necromancer(p)
          }, {
          new Spider(p),
          new Slime(p),
          new Necromancer(p)
          }, {
          new Spider(p),
          new Slime(p),
          new Necromancer(p)
        }};
        for (Combatant[] combatants : waves) {
            for (Combatant combatant : combatants) {
                combatant.isEnemy = true;
            }
        }

        dialogues = new Dialogue[][]{{
            new Dialogue(P, "test A", getPositionFromSlot(0)),
          new Dialogue(P, "test B", getPositionFromSlot(0)),
          new Dialogue(P, "test C", getPositionFromSlot(3))
        }, {
          new Dialogue(P, "test D", getPositionFromSlot(1))
        }};
    }
}
