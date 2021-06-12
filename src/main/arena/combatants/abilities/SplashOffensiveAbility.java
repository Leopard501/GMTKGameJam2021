package main.arena.combatants.abilities;

import main.arena.combatants.Combatant;

public interface SplashOffensiveAbility extends Ability {

    void ability(Combatant[] others);
}
