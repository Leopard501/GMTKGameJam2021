package main.arena.combatants.enemies;

import main.arena.combatants.Combatant;
import processing.core.PApplet;

import static main.Main.sounds;

import java.awt.*;

public class Skeleton extends Combatant {

    public Skeleton(PApplet p) {
        super(p, 25, 0, 0, 3, 0, new Color(255, 255, 200));
        loadAnimations("skeleton");
        hurtSound = sounds.get("crunch");
    }
}
