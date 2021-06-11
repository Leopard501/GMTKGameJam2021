package main.arena;

import com.sun.istack.internal.NotNull;
import main.arena.combatants.Combatant;
import main.arena.combatants.enemies.Android;
import main.arena.combatants.team.Fighter;
import main.arena.combatants.team.Healer;
import main.arena.combatants.types.BuffAbility;
import main.arena.combatants.types.DamageAbility;
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
          new Slot(new PVector(125, 100)),
          new Slot(new PVector(175, 225)),
          new Slot(new PVector(145, 350))
        }; ENEMY_SLOTS = new Slot[] {
          new Slot(new PVector(BOARD_SIZE.x - 145, 100)),
          new Slot(new PVector(BOARD_SIZE.x - 175, 225)),
          new Slot(new PVector(BOARD_SIZE.x - 125, 350))
        };

        TEAM_SLOTS[0].setCombatant(new Fighter(P));
        TEAM_SLOTS[1].setCombatant(new Healer(P));
        TEAM_SLOTS[2].setCombatant(new Fighter(P));

        ENEMY_SLOTS[0].setCombatant(new Android(P));
        ENEMY_SLOTS[1].setCombatant(new Android(P));
        ENEMY_SLOTS[2].setCombatant(new Android(P));
        for (Slot slot: ENEMY_SLOTS) slot.combatant.isEnemy = true;
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
        if (ENEMY_SLOTS[selected].empty() || noTeam()) {
            advanceTurn();
            return;
        }
        Slot target = TEAM_SLOTS[(int) P.random(TEAM_SLOTS.length)];
        while (target.empty()) {
            target = TEAM_SLOTS[(int) P.random(TEAM_SLOTS.length)];
        }
        ENEMY_SLOTS[selected].attack(target);
        actionTimer = 0;
        advanceTurn();
    }

    private boolean noTeam() {
        for (Slot slot : TEAM_SLOTS) {
            if (!slot.empty()) return false;
        }
        return true;
    }

    private void simTeamTurn() {
        if (TEAM_SLOTS[selected].empty()) advanceTurn();
        for (Slot slot : ENEMY_SLOTS) {
            //none, left, right
            int actionState = slot.actionState();
            if (actionState == 1) {
                actionTimer = 0;
                TEAM_SLOTS[selected].attack(slot);
                advanceTurn();
            } else if (actionState == 2) {
                if (TEAM_SLOTS[selected].ability(slot)) {
                    actionTimer = 0;
                    advanceTurn();
                }
            }
        } for (Slot slot : TEAM_SLOTS) {
            //none, left, right
            int actionState = slot.actionState();
            if (actionState == 2) {
                if (TEAM_SLOTS[selected].ability(slot)) {
                    actionTimer = 0;
                    advanceTurn();
                }
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

        private int actionState() {
            if (combatant == null) return 0;
            return combatant.actionState();
        }

        private void attack(Slot other) {
            if (combatant == null) return;
            combatant.attack(other.combatant);
        }

        private static boolean onOpposingTeams(Combatant a, Combatant b) {
            return a.isEnemy != b.isEnemy;
        }

        /**
         * @return true if used ability, false if couldn't
         */
        @NotNull
        private boolean ability(Slot other) {
            if (combatant == null) return false;
            if (combatant.mp < combatant.mpCost) return false;
            if (combatant instanceof DamageAbility) {
                if (!onOpposingTeams(combatant, other.combatant)) return false;
                ((DamageAbility) combatant).damageAbility(other.combatant);
            }
            if (combatant instanceof BuffAbility) {
                if (onOpposingTeams(combatant, other.combatant)) return false;
                ((BuffAbility) combatant).buffAbility(other.combatant);
            }
            return true;
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
