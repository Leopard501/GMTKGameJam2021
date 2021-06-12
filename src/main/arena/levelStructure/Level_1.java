package main.arena.levelStructure;

import main.arena.combatants.Combatant;
import main.arena.combatants.enemies.*;
import main.arena.combatants.team.*;
import main.gui.guiObjects.Dialogue;
import processing.core.PApplet;
import processing.core.PVector;

import static main.Main.BOARD_SIZE;
import static processing.core.PApplet.binary;

public class Level_1 extends Level {

    public Level_1(PApplet p) {
        super(p);

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
          dialogueFromSlot(0, "test A"),
          dialogueFromSlot(3, "test B")
        }, {
            dialogueFromSlot(1, "test C")
        }};
    }

    private Dialogue dialogueFromSlot(int slot, String text) {
        PVector position;
        switch (slot) {
            case 0:
                position = new PVector(BOARD_SIZE.x / 6, BOARD_SIZE.y / 4);
                break;
            case 1:
                position = new PVector(BOARD_SIZE.x / 3, BOARD_SIZE.y / 2);
                break;
            case 2:
                position = new PVector(BOARD_SIZE.x / 6, 3 * (BOARD_SIZE.y / 4));
                break;
            case 3:
                position = new PVector(BOARD_SIZE.x - (BOARD_SIZE.x / 6), BOARD_SIZE.y / 4);
                break;
            case 4:
                position = new PVector(BOARD_SIZE.x - (BOARD_SIZE.x / 3), BOARD_SIZE.y / 2);
                break;
            case 5:
                position = new PVector(BOARD_SIZE.x - (BOARD_SIZE.x / 6), 3 * (BOARD_SIZE.y / 4));
                break;
            default:
                position = new PVector(BOARD_SIZE.x / 2, BOARD_SIZE.y / 2);
                break;
        }
        return new Dialogue(P, text, position);
    }

}
