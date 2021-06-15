package main.arena.levelStructure;

import main.arena.combatants.Combatant;
import main.gui.guiObjects.Dialogue;
import processing.core.PApplet;

public abstract class Level {

    public boolean isCutscene;
    public String soundtrack;
    public Combatant[] team;
    public Combatant[][] waves;
    public Dialogue[][] dialogues;

    protected final PApplet P;

    public Level(PApplet p) {
        P = p;
        isCutscene = false;
    }
}
