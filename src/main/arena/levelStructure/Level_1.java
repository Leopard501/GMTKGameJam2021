package main.arena.levelStructure;

import main.arena.combatants.Combatant;
import main.arena.combatants.enemies.Android;
import main.arena.combatants.enemies.Drone;
import processing.core.PApplet;

public class Level_1 extends Level {

    public Level_1(PApplet p) {
        super(p);

        waves = new Combatant[][]{{
            new Android(p)
          }, {
            new Android(p),
            new Android(p)
          }, {
            new Drone(p),
            new Android(p),
            new Android(p)
          }, {
            new Drone(p),
            new Drone(p),
            new Android(p)
        }};
        for (Combatant[] combatants : waves) {
            for (Combatant combatant : combatants) {
                combatant.isEnemy = true;
            }
        }
    }

}
