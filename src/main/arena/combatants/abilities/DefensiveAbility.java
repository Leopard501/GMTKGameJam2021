package main.arena.combatants.abilities;

import main.arena.combatants.Combatant;

public interface DefensiveAbility extends Ability {

    void ability(Combatant other);
}
