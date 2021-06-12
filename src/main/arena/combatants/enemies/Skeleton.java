package main.arena.combatants.enemies;

import main.arena.combatants.Combatant;
import processing.core.PApplet;

import java.awt.*;

public class Skeleton extends Combatant {

    public Skeleton(PApplet p) {
        super(p, 50, 0, 0, 8, 0, new Color(255, 255, 200));
        loadAnimations("skeleton");
    }
}
