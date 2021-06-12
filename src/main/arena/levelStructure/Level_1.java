package main.arena.levelStructure;

import main.arena.combatants.Combatant;
import main.arena.combatants.enemies.*;
import main.arena.combatants.team.*;
import processing.core.PApplet;

public class Level_1 extends Level {

    public Level_1(PApplet p) {
        super(p);

        waves = new Combatant[][]{{
            new T800(p),
            new Drone(p),
            new Router(p)
          }, {
          new Spider(p),
          new Slime(p),
          new Necromancer(p)
          }, {
          new Spider(p),
          new Slime(p),
          new Necromancer(p)
          }, {
          new Spider(p),
          new Slime(p),
          new Necromancer(p)
        }};
        for (Combatant[] combatants : waves) {
            for (Combatant combatant : combatants) {
                combatant.isEnemy = true;
            }
        }
    }

}
