package main.arena.levelStructure;

import main.arena.combatants.Combatant;
import processing.core.PApplet;

public abstract class Level {

    public Combatant[][] waves;

    private final PApplet P;

    public Level(PApplet p) {
        P = p;
    }
}
