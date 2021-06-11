package main.arena;

import com.sun.istack.internal.NotNull;
import main.arena.combatants.Combatant;
import main.arena.combatants.enemies.Android;
import main.arena.combatants.team.Fighter;
import main.arena.combatants.team.Healer;
import main.arena.combatants.team.Slime;
import main.arena.combatants.abilities.BuffAbility;
import main.arena.combatants.abilities.DamageAbility;
import main.arena.combatants.abilities.SplashAbility;
import processing.core.PApplet;
import processing.core.PVector;

import static main.Main.BOARD_SIZE;
import static main.Main.arena;

public class Arena {

    public boolean enemiesTurn;

    public final Slot[] TEAM_SLOTS;
    public final Slot[] ENEMY_SLOTS;

    private static final int TIME_BETWEEN_ACTIONS = 30;

    private final PApplet P;

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
        TEAM_SLOTS[2].setCombatant(new Slime(P));

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
            if (selected >= ENEMY_SLOTS.length) endEnemyTurn();
            else if (ENEMY_SLOTS[selected].empty()) advanceTurn();
        } else {
            if (selected >= TEAM_SLOTS.length) endTeamTurn();
            else if (TEAM_SLOTS[selected].empty()) advanceTurn();
        }
    }

    private void endEnemyTurn() {
        enemiesTurn = false;
        selected = 0;
        for (Slot slot : ENEMY_SLOTS) slot.updateBuffs();
    }

    private void endTeamTurn() {
        enemiesTurn = true;
        selected = 0;
        for (Slot slot : TEAM_SLOTS) slot.updateBuffs();
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
            if (empty()) return;
            combatant.display();
            if (!combatant.alive) setCombatant(null);
        }

        private void setCombatant(Combatant combatant) {
            this.combatant = combatant;
            if (empty()) return;
            combatant.setPosition(POSITION.x, POSITION.y);
        }

        private int actionState() {
            if (empty()) return 0;
            return combatant.actionState();
        }

        private void attack(Slot other) {
            if (empty()) return;
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
            if (empty()) return false;
            if (combatant.mp < combatant.mpCost) return false;
            if (combatant instanceof DamageAbility) {
                if (!onOpposingTeams(combatant, other.combatant)) return false;
                ((DamageAbility) combatant).ability(other.combatant);
            }
            if (combatant instanceof SplashAbility) {
                if (!onOpposingTeams(combatant, other.combatant)) return false;
                Combatant[] others;
                if (combatant.isEnemy) {
                    others = new Combatant[arena.TEAM_SLOTS.length];
                    for (int i = 0; i < others.length; i++) {
                        others[i] = arena.TEAM_SLOTS[i].combatant;
                    }
                } else {
                    others = new Combatant[arena.ENEMY_SLOTS.length];
                    for (int i = 0; i < others.length; i++) {
                        others[i] = arena.ENEMY_SLOTS[i].combatant;
                    }
                }
                ((SplashAbility) combatant).ability(others);
            }
            if (combatant instanceof BuffAbility) {
                if (onOpposingTeams(combatant, other.combatant)) return false;
                ((BuffAbility) combatant).ability(other.combatant);
            }
            return true;
        }

        private void selectionOverlay() {
            if (empty()) return;
            combatant.selectionOverlay();
        }

        private boolean empty() {
            return combatant == null;
        }

        private void updateBuffs() {
            if (empty()) return;
            combatant.updateBuffs();
        }
    }
}
