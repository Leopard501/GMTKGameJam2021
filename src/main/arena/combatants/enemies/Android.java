package main.arena.combatants.enemies;

import main.arena.combatants.Combatant;
import processing.core.PApplet;

import java.awt.*;

public class Android extends Combatant {

    public Android(PApplet p) {
        super(p, 75, 0, 0, 10, 0, new Color(255, 255, 0));
        loadAnimations("android");
    }
}
