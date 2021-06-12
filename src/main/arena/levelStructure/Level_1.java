package main.arena.levelStructure;

import main.arena.combatants.Combatant;
import main.arena.combatants.enemies.Android;
import main.arena.combatants.enemies.Drone;
import main.arena.combatants.team.*;
import processing.core.PApplet;

public class Level_1 extends Level {

    public Level_1(PApplet p) {
        super(p);

        waves = new Combatant[][]{{
            new Fighter(p),
            new Healer(p)
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
