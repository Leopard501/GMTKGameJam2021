package main.arena.combatants.abilities;

import main.arena.combatants.Combatant;

public interface OffensiveAbility extends Ability {

    void ability(Combatant other);
}
