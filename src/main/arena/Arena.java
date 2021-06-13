package main.arena;

import com.sun.istack.internal.NotNull;
import main.Main;
import main.arena.combatants.Combatant;
import main.arena.combatants.abilities.Ability;
import main.arena.combatants.abilities.DefensiveAbility;
import main.arena.combatants.abilities.OffensiveAbility;
import main.arena.combatants.abilities.SplashOffensiveAbility;
import main.arena.levelStructure.*;
import main.arena.particles.Particle;
import main.gui.guiObjects.Dialogue;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

import java.util.ArrayList;

import static main.Main.BOARD_SIZE;
import static main.Main.arena;
import static main.misc.Utilities.getPositionFromSlot;
import static main.misc.Utilities.getSlotFromPosition;
import static processing.core.PConstants.CENTER;
import static processing.core.PConstants.CORNER;

public class Arena {

    public boolean enemiesTurn;
    public ArrayList<Particle> particles;
    public ArrayList<Dialogue> dialogues;
    public Slot[] teamSlots;
    public Slot[] enemySlots;

    private static final int TIME_BETWEEN_ACTIONS = 60;
    private static final int TIME_BETWEEN_DIALOGUE = 120;
    private static final int INCREASE_DARK = 5;

    private final PApplet P;
    private final PImage BACKGROUND;

    private boolean gettingDark;
    private int darkAmount;
    private int selected;
    private int actionTimer;
    private int dialogueTimer;
    private int currentDialogue;
    private int currentWave;
    private int currentLevel;
    private Level[] levels;

    /**
     * This will hold the players and gui and stuff
     */
    public Arena(PApplet p) {
        P = p;

        BACKGROUND = Main.sprites.get("arenaBG");

        particles = new ArrayList<>();
        dialogues = new ArrayList<>();

        darkAmount = 254;

        teamSlots = new Slot[] {
          new Slot(getPositionFromSlot(0)),
          new Slot(getPositionFromSlot(1)),
          new Slot(getPositionFromSlot(2))
        }; enemySlots = new Slot[] {
          new Slot(getPositionFromSlot(3)),
          new Slot(getPositionFromSlot(4)),
          new Slot(getPositionFromSlot(5))
        };

        currentLevel = -1;
        levels = new Level[] {
          new Level_1(p),
          new Level_2(p),
          new Level_3(p),
          new Level_4(p),
          new Level_5(p),
          new Level_6(p),
          new Level_7(p),
          new Level_8(p),
          new Level_9(p),
          new Level_10(p)
        };
        advanceLevel();
    }

    private void advanceLevel() {
        currentLevel++;
        currentWave = -1;
        currentDialogue = 0;
        if (currentLevel >= levels.length) {
            Main.inMainMenu = true;
            arena = new Arena(P);
            return;
        }
        for (int i = 0; i < levels[currentLevel].team.length; i++) {
            teamSlots[i].setCombatant(levels[currentLevel].team[i]);
        }
        advanceWave();
    }

    private void advanceWave() {
        enemiesTurn = false;
        selected = 0;
        actionTimer = 0;
        dialogueTimer = TIME_BETWEEN_DIALOGUE / 2;
        currentDialogue = 0;
        currentWave++;
        dialogues = new ArrayList<>();
        if (currentWave >= levels[currentLevel].waves.length) {
            advanceLevel();
            return;
        }
        for (int i = 0; i < levels[currentLevel].waves[currentWave].length; i++) {
            enemySlots[i].setCombatant(levels[currentLevel].waves[currentWave][i]);
        }
    }

    private void resetLevel() {
        currentLevel--;
        gettingDark = false;
        advanceLevel();
    }

    public void main() {
        display();
        boolean ranOutOfDialogue = currentDialogue >= levels[currentLevel].dialogues[currentWave].length;
        if (levels[currentLevel].isCutscene) {
            if (ranOutOfDialogue) dialogueTimer++;
            if (ranOutOfDialogue && dialogueTimer > TIME_BETWEEN_DIALOGUE * 2) {
                if (darkAmount > 254) advanceWave();
                else gettingDark = true;
            }
        } else if (actionTimer >= TIME_BETWEEN_ACTIONS) {
            if (enemiesTurn) simEnemyTurn();
            else simPlayerTurn();
        }
        if (ranOutOfDialogue && currentLevel == levels.length) {
            System.out.println("test");
            return;
        }
        updateDialogue();
        if (noTeam()) {
            if (darkAmount >= 254) resetLevel();
            else gettingDark = true;
        }
        if (noEnemies()) {
            if (darkAmount > 254) advanceWave();
            else gettingDark = true;
        }
        actionTimer++;
    }

    private void updateDialogue() {
        if (currentWave < levels[currentLevel].dialogues.length && currentDialogue < levels[currentLevel].dialogues[currentWave].length) {
            dialogueTimer++;
            if (dialogueTimer >= TIME_BETWEEN_DIALOGUE) {
                Dialogue currentDialogOb = levels[currentLevel].dialogues[currentWave][currentDialogue];
                int slot = getSlotFromPosition(currentDialogOb.position);
                if (slot < 3 && teamSlots[slot].empty()) return;
                if (slot > 3 && enemySlots[slot - 3].empty()) return;
                if (currentDialogue > 0) {
                    Dialogue lastDialogOb = levels[currentLevel].dialogues[currentWave][currentDialogue - 1];
                    if (currentDialogOb.position.equals(lastDialogOb.position)) currentDialogOb.moveUp(1);
                } if (currentDialogue > 1) {
                    Dialogue lastDialogOb = levels[currentLevel].dialogues[currentWave][currentDialogue - 2];
                    if (currentDialogOb.position.equals(lastDialogOb.position)) {
                        currentDialogOb.moveUp(2);
                    }
                }
                dialogues.add(currentDialogOb);
                dialogueTimer = 0;
                currentDialogue++;
            }
        }
    }

    private void simEnemyTurn() {
        if (enemySlots[selected].empty() || noTeam()) {
            advanceTurn();
            return;
        }
        Combatant enemy = enemySlots[selected].combatant;
        boolean attack = P.random(2) < 1;
        if (!(enemy instanceof Ability)) attack = true;
        if (enemy.mp < enemy.mpCost) attack = true;
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
            actionTimer = 0;
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

    private void simPlayerTurn() {
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
        P.imageMode(CORNER);
        P.image(BACKGROUND, 0, 0);
        P.imageMode(CENTER);
        for (Slot slot : teamSlots) {
            slot.display();
            if (!levels[currentLevel].isCutscene) slot.displayBars();
        }
        for (Slot slot : enemySlots) {
            slot.display();
            if (!levels[currentLevel].isCutscene) slot.displayBars();

        }
        if (actionTimer >= TIME_BETWEEN_ACTIONS && !levels[currentLevel].isCutscene) {
            if (enemiesTurn) enemySlots[selected].selectionOverlay();
            else teamSlots[selected].selectionOverlay();
        }
        for (int i = particles.size() - 1; i >= 0; i--) {
            Particle particle = particles.get(i);
            particle.main();
        }
        for (int i = dialogues.size() -1; i >= 0; i--) {
            Dialogue dialogue = dialogues.get(i);
            dialogue.main();
        }
        if (gettingDark) {
            darkAmount += INCREASE_DARK;
            if (darkAmount >= 254) gettingDark = false;
        } else if (darkAmount > 0) darkAmount -= INCREASE_DARK;
        P.rectMode(CORNER);
        P.fill(0, darkAmount);
        P.noStroke();
        P.rect(0, 0, BOARD_SIZE.x + 1, BOARD_SIZE.y + 1);
        P.rectMode(CENTER);
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

        private void displayBars() {
            if (empty()) return;
            combatant.displayBars();
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
            combatant.setAttacking(other.combatant);
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
                combatant.setAbility(other.combatant);
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
                combatant.setAbility(others);
            }
            if (combatant instanceof DefensiveAbility) {
                if (onOpposingTeams(combatant, other.combatant)) return false;
                combatant.setAbility(other.combatant);
            }
            return true;
        }

        private void selectionOverlay() {
            if (empty()) return;
            combatant.selectionOverlay();
        }

        private boolean empty() {
            if (combatant == null) return true;
            return !combatant.alive;
        }

        private void updateBuffs() {
            if (empty()) return;
            combatant.updateBuffs();
        }

        private boolean cantAct() {
            return empty() || combatant.sticky != null || combatant.pizza != null;
        }
    }
}
