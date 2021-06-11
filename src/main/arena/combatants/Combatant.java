package main.arena.combatants;

import processing.core.PApplet;
import processing.core.PVector;

public abstract class Combatant {

    public boolean alive;

    protected static final PVector SIZE = new PVector(50, 50);

    protected final PApplet P;

    protected PVector position;
    protected int hp;
    protected int maxHp;
    protected int mp;
    protected int maxMp;
    protected int primaryDamage;
    protected int secondaryDamage;
    protected int mpCost;

    /**
     * These are the little dudes that will fight.
     */
    public Combatant(PApplet p, int maxHp, int maxMp, int mpCost, int primaryDamage,
                     int secondaryDamage) {
        P = p;
        this.maxHp = maxHp;
        this.maxMp = maxMp;
        this.mpCost = mpCost;
        this.primaryDamage = primaryDamage;
        this.secondaryDamage = secondaryDamage;
    }

    public void setPosition(PVector position) {
        this.position = position;
    }

    public void display() {
        P.fill(255);
        P.rect(position.x, position.y, SIZE.x, SIZE.y);
    }

    public void primaryAttack(Combatant other) {
        other.hurt(primaryDamage);
    }

    public void secondaryAttack(Combatant other) {
        mp -= mpCost;
        secondaryAttackEffect(other);
    }

    protected abstract void secondaryAttackEffect(Combatant other);

    public void hurt(int amount) {
        hp -= amount;
        if (hp <= 0) alive = false;
    }
}
