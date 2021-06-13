package main.arena.combatants.enemies;

import main.arena.combatants.Combatant;
import processing.core.PApplet;

import java.awt.*;

import static main.Main.sounds;

public class Android extends Combatant {

    public Android(PApplet p) {
        super(p, 50, 0, 0, 8, 0, new Color(255, 255, 0));
        hurtSound = sounds.get("metalDamage");
        loadAnimations("android");
    }
}
