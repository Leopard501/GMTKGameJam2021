package main.arena;

import main.arena.combatants.Combatant;
import main.arena.combatants.enemies.Android;
import main.arena.combatants.team.Fighter;
import processing.core.PApplet;
import processing.core.PVector;

import static main.Main.BOARD_SIZE;

public class Arena {

    public boolean enemiesTurn;

    private static final int TIME_BETWEEN_ACTIONS = 30;

    private final PApplet P;
    private final Slot[] TEAM_SLOTS;
    private final Slot[] ENEMY_SLOTS;

    private int selected;
    private int actionTimer;

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

        TEAM_SLOTS[0].setCombatant(new Fighter(P));
        TEAM_SLOTS[1].setCombatant(new Fighter(P));
        TEAM_SLOTS[2].setCombatant(new Fighter(P));

        ENEMY_SLOTS[0].setCombatant(new Android(P));
        ENEMY_SLOTS[1].setCombatant(new Android(P));
        ENEMY_SLOTS[2].setCombatant(new Android(P));

        selected = -1;
        advanceTurn();
    }

    public void main() {
        display();
        if (actionTimer >= TIME_BETWEEN_ACTIONS) {
            if (enemiesTurn) simEnemyTurn();
            else simTeamTurn();
        }
        actionTimer++;
    }

    private void simEnemyTurn() {
        actionTimer = 0;
        enemiesTurn = false;
    }

    private void simTeamTurn() {
        for (Slot slot : ENEMY_SLOTS) {
            if (slot.isClicked()) {
                actionTimer = 0;
                TEAM_SLOTS[selected].primaryAttack(slot);
                advanceTurn();
            }
        }
    }

    private void advanceTurn() {
        selected++;
        if (enemiesTurn) {
            if (selected >= ENEMY_SLOTS.length) {
                enemiesTurn = false;
                selected = 0;
            } else if (ENEMY_SLOTS[selected].empty()) advanceTurn();
        } else {
            if (selected >= TEAM_SLOTS.length) {
                enemiesTurn = true;
                selected = 0;
            } else if (TEAM_SLOTS[selected].empty()) advanceTurn();
        }
    }

    private void display() {
        for (Slot slot : TEAM_SLOTS) slot.display();
        for (Slot slot : ENEMY_SLOTS) slot.display();
        if (actionTimer >= TIME_BETWEEN_ACTIONS) {
            if (enemiesTurn) ENEMY_SLOTS[selected].selectionOverlay();
            else TEAM_SLOTS[selected].selectionOverlay();
        }
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
            if (!combatant.alive) setCombatant(null);
        }

        private void setCombatant(Combatant combatant) {
            this.combatant = combatant;
            if (combatant == null) return;
            combatant.setPosition(POSITION.x, POSITION.y);
        }

        private boolean isClicked() {
            if (combatant == null) return false;
            return combatant.isClicked();
        }

        private void primaryAttack(Slot other) {
            if (combatant == null) return;
            combatant.primaryAttack(other.combatant);
        }

        private void selectionOverlay() {
            if (combatant == null) return;
            combatant.selectionOverlay();
        }

        private boolean empty() {
            return combatant == null;
        }
    }
}
