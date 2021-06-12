package main.arena.combatants.enemies;

import main.arena.combatants.Combatant;
import processing.core.PApplet;
import processing.core.PVector;

import java.awt.*;

public class Android extends Combatant {

    public Android(PApplet p) {
        super(p, 75, 10, 2, 25, 20, new Color(29, 23, 0));
    }
}
