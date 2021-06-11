package main.arena;

import main.arena.combatants.Combatant;
import main.arena.combatants.enemies.Android;
import main.arena.combatants.team.Fighter;
import processing.core.PApplet;
import processing.core.PVector;

import static main.Main.BOARD_SIZE;

public class Arena {

    public boolean enemiesTurn;

    private final PApplet P;
    private final Slot[] TEAM_SLOTS;
    private final Slot[] ENEMY_SLOTS;

    private Slot selected;

    /**
     * This will hold the players and gui and stuff
     */
    public Arena(PApplet p) {
        P = p;

        TEAM_SLOTS = new Slot[] {
          new Slot(new PVector(100, 100)),
          new Slot(new PVector(150, 200)),
          new Slot(new PVector(110, 300))
        }; ENEMY_SLOTS = new Slot[] {
          new Slot(new PVector(BOARD_SIZE.x - 100, 100)),
          new Slot(new PVector(BOARD_SIZE.x - 150, 200)),
          new Slot(new PVector(BOARD_SIZE.x - 110, 300))
        };

        TEAM_SLOTS[1].setCombatant(new Fighter(P));

        ENEMY_SLOTS[1].setCombatant(new Android(P));
    }

    public void main() {
        display();
        for (Slot slot : TEAM_SLOTS) System.out.println(slot.isClicked());
    }

    private void display() {
        for (Slot slot : TEAM_SLOTS) slot.display();
        for (Slot slot : ENEMY_SLOTS) slot.display();
    }

    private static class Slot {

        private final PVector POSITION;
        private Combatant combatant;

        private Slot(PVector position) {
            POSITION = position;
        }

        private void display() {
            if (combatant == null) return;
            combatant.display();
        }

        private void setCombatant(Combatant combatant) {
            this.combatant = combatant;
            combatant.setPosition(POSITION.x, POSITION.y);
        }

        private boolean isClicked() {
            if (combatant == null) return false;
            return combatant.isClicked();
        }
    }
}
