package main.arena;

import com.sun.istack.internal.NotNull;
import main.arena.combatants.Combatant;
import main.arena.combatants.abilities.Ability;
import main.arena.combatants.team.*;
import main.arena.combatants.abilities.DefensiveAbility;
import main.arena.combatants.abilities.OffensiveAbility;
import main.arena.combatants.abilities.SplashOffensiveAbility;
import main.arena.levelStructure.Level;
import main.arena.levelStructure.Level_1;
import main.arena.particles.Particle;
import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;

import static main.Main.BOARD_SIZE;
import static main.Main.arena;

public class Arena {

    public boolean enemiesTurn;
    public ArrayList<Particle> particles;
    public Slot[] teamSlots;
    public Slot[] enemySlots;

    private static final int TIME_BETWEEN_ACTIONS = 30;

    private final PApplet P;

    private int selected;
    private int actionTimer;
    private int currentWave;
    private Level level;

    /**
     * This will hold the players and gui and stuff
     */
    public Arena(PApplet p) {
        P = p;

        particles = new ArrayList<>();

        teamSlots = new Slot[] {
          new Slot(new PVector(BOARD_SIZE.x / 6, BOARD_SIZE.y / 4)),
          new Slot(new PVector(BOARD_SIZE.x / 3, BOARD_SIZE.y / 2)),
          new Slot(new PVector(BOARD_SIZE.x / 6, 3 * (BOARD_SIZE.y / 4)))
        }; enemySlots = new Slot[] {
          new Slot(new PVector(BOARD_SIZE.x - (BOARD_SIZE.x / 6), BOARD_SIZE.y / 4)),
          new Slot(new PVector(BOARD_SIZE.x - (BOARD_SIZE.x / 3), BOARD_SIZE.y / 2)),
          new Slot(new PVector(BOARD_SIZE.x - (BOARD_SIZE.x / 6), 3 * (BOARD_SIZE.y / 4)))
        };

        teamSlots[0].setCombatant(new Spider(P));
        teamSlots[1].setCombatant(new Slime(P));
        teamSlots[2].setCombatant(new Shielder(P));

        currentWave = -1;
        level = new Level_1(p);
        advanceWave();
    }

    private void advanceWave() {
        enemiesTurn = false;
        selected = 0;
        actionTimer = 0;
        currentWave++;
        if (currentWave >= level.waves.length) System.out.println("You win!\nPromptly crashing...");
        for (int i = 0; i < level.waves[currentWave].length; i++) {
            enemySlots[i].setCombatant(level.waves[currentWave][i]);
        }
    }

    public void main() {
        display();
        if (actionTimer >= TIME_BETWEEN_ACTIONS) {
            if (enemiesTurn) simEnemyTurn();
            else simTeamTurn();
        }
        if (noEnemies()) advanceWave();
        actionTimer++;
    }

    private void simEnemyTurn() {
        if (enemySlots[selected].empty() || noTeam()) {
            advanceTurn();
            return;
        }
        Combatant enemy = enemySlots[selected].combatant;
        boolean attack = P.random(2) < 1;
        if (!(enemy instanceof Ability)) attack = true;
        if (enemySlots[selected].cantAct()) {
            actionTimer = 0;
            advanceTurn();
            return;
        }
        if (attack) {
            Slot target = teamSlots[(int) P.random(teamSlots.length)];
            while (target.empty()) {
                target = teamSlots[(int) P.random(teamSlots.length)];
            }
            enemySlots[selected].attack(target);
        } else {
            if (enemy instanceof OffensiveAbility) {
                Slot target = teamSlots[(int) P.random(teamSlots.length)];
                while (target.empty()) {
                    target = teamSlots[(int) P.random(teamSlots.length)];
                }
                enemySlots[selected].ability(target);
            } else if (enemy instanceof DefensiveAbility) {
                Slot target = enemySlots[(int) P.random(enemySlots.length)];
                while (target.empty()) {
                    target = enemySlots[(int) P.random(enemySlots.length)];
                }
                enemySlots[selected].ability(target);
            } else if (enemy instanceof SplashOffensiveAbility) {
                Slot target = teamSlots[(int) P.random(teamSlots.length)];
                while (target.empty()) {
                    target = teamSlots[(int) P.random(teamSlots.length)];
                }
                enemySlots[selected].ability(target);
            }
        }
        actionTimer = 0;
        advanceTurn();
    }

    private boolean noEnemies() {
        for (Slot slot : enemySlots) {
            if (!slot.empty()) return false;
        }
        return true;
    }

    private boolean noTeam() {
        for (Slot slot : teamSlots) {
            if (!slot.empty()) return false;
        }
        return true;
    }

    private void simTeamTurn() {
        if (teamSlots[selected].cantAct()) advanceTurn();
        for (Slot slot : enemySlots) {
            //none, left, right
            int actionState = slot.actionState();
            if (actionState == 1) {
                actionTimer = 0;
                teamSlots[selected].attack(slot);
                advanceTurn();
            } else if (actionState == 2) {
                if (teamSlots[selected].ability(slot)) {
                    actionTimer = 0;
                    advanceTurn();
                }
            }
        } for (Slot slot : teamSlots) {
            //none, left, right
            int actionState = slot.actionState();
            if (actionState == 2) {
                if (teamSlots[selected].ability(slot)) {
                    actionTimer = 0;
                    advanceTurn();
                }
            }
        }
    }

    private void advanceTurn() {
        selected++;
        if (enemiesTurn) {
            if (selected >= enemySlots.length) endEnemyTurn();
            else if (enemySlots[selected].empty()) advanceTurn();
        } else {
            if (selected >= teamSlots.length) endTeamTurn();
            else if (teamSlots[selected].empty()) advanceTurn();
        }
    }

    private void endEnemyTurn() {
        enemiesTurn = false;
        selected = 0;
        for (Slot slot : enemySlots) slot.updateBuffs();
    }

    private void endTeamTurn() {
        enemiesTurn = true;
        selected = 0;
        for (Slot slot : teamSlots) slot.updateBuffs();
    }

    private void display() {
        for (Slot slot : teamSlots) slot.display();
        for (Slot slot : enemySlots) slot.display();
        if (actionTimer >= TIME_BETWEEN_ACTIONS) {
            if (enemiesTurn) enemySlots[selected].selectionOverlay();
            else teamSlots[selected].selectionOverlay();
        }
        for (int i = particles.size() - 1; i >= 0; i--) {
            Particle particle = particles.get(i);
            particle.main();
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
            if (combatant instanceof OffensiveAbility) {
                if (!onOpposingTeams(combatant, other.combatant)) return false;
                ((OffensiveAbility) combatant).ability(other.combatant);
            }
            if (combatant instanceof SplashOffensiveAbility) {
                if (!onOpposingTeams(combatant, other.combatant)) return false;
                Combatant[] others;
                if (combatant.isEnemy) {
                    others = new Combatant[arena.teamSlots.length];
                    for (int i = 0; i < others.length; i++) {
                        others[i] = arena.teamSlots[i].combatant;
                    }
                } else {
                    others = new Combatant[arena.enemySlots.length];
                    for (int i = 0; i < others.length; i++) {
                        others[i] = arena.enemySlots[i].combatant;
                    }
                }
                ((SplashOffensiveAbility) combatant).ability(others);
            }
            if (combatant instanceof DefensiveAbility) {
                if (onOpposingTeams(combatant, other.combatant)) return false;
                ((DefensiveAbility) combatant).ability(other.combatant);
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

        private boolean cantAct() {
            return empty() || combatant.sticky != null;
        }
    }
}
