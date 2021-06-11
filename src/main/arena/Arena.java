package main.arena;

import main.arena.combatants.Combatant;
import main.arena.combatants.goodGuys.Fighter;
import processing.core.PApplet;
import processing.core.PVector;

import static main.Main.BOARD_SIZE;

public class Arena {

    private final PApplet P;
    private final Slot[] GOOD_SLOTS;
    private final Slot[] BAD_SLOTS;

    /**
     * This will hold the players and gui and stuff
     */
    public Arena(PApplet p) {
        P = p;

        GOOD_SLOTS = new Slot[] {
          new Slot(new PVector(100, 100)),
          new Slot(new PVector(150, 200)),
          new Slot(new PVector(110, 300))
        }; BAD_SLOTS = new Slot[] {
          new Slot(new PVector(BOARD_SIZE.x - 100, 100)),
          new Slot(new PVector(BOARD_SIZE.x - 150, 200)),
          new Slot(new PVector(BOARD_SIZE.x - 110, 300))
        };
    }

    public void display() {
        for (Slot slot : GOOD_SLOTS) slot.display();
        for (Slot slot : BAD_SLOTS) slot.display();
    }

    private class Slot {

        private PVector position;
        private Combatant combatant;

        private Slot(PVector position) {
            this.position = position;
        }

        private void display() {
            if (combatant == null) return;
            combatant.display();
        }
    }
}
