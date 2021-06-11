package main.arena.combatants;

import main.Main;
import processing.core.PApplet;
import processing.core.PVector;

import java.awt.*;

import static main.Main.inputHandler;
import static main.Main.matrixMousePosition;
import static main.misc.Utilities.pointOnRect;
import static main.sound.SoundUtilities.playSound;
import static processing.core.PConstants.CENTER;
import static processing.core.PConstants.CORNER;

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

        alive = true;
        hp = maxHp;
        mp = maxMp;
    }

    public void setPosition(float x, float y) {
        position = new PVector(x, y);
    }

    public void display() {
        P.fill(255);
        P.noStroke();
        P.circle(position.x, position.y, SIZE.x);
        hpBar();
        mpBar();
    }

    public void hpBar() {
        P.rectMode(CORNER);
        Color barColor = new Color(255, 0, 0);
        float barWidth = SIZE.x * (hp / (float) maxHp);
        P.stroke(barColor.getRGB());
        P.noFill();
        P.rect(position.x - SIZE.x / 2, position.y + SIZE.y / 2 + 6, SIZE.x, 6);
        P.fill(barColor.getRGB());
        if (hp > 0) P.rect(position.x - SIZE.x / 2, position.y + SIZE.y / 2 + 6, barWidth, 6);
        P.rectMode(CENTER);
    }

    public void mpBar() {
        P.rectMode(CORNER);
        Color barColor = new Color(0, 175, 255);
        float barWidth = SIZE.x * (mp / (float) maxMp);
        P.stroke(barColor.getRGB());
        P.noFill();
        P.rect(position.x - SIZE.x / 2, position.y + SIZE.y / 2 + 13, SIZE.x, 6);
        P.fill(barColor.getRGB());
        if (mp > 0) P.rect(position.x - SIZE.x / 2, position.y + SIZE.y / 2 + 13, barWidth, 6);
        P.rectMode(CENTER);
    }

    public void selectionOverlay() {
        P.noFill();
        P.stroke(255);
        P.rect(position.x, position.y, SIZE.x, SIZE.y);
    }

    public boolean isClicked() {
        if (pointOnRect(position, new PVector(SIZE.x / 2, SIZE.y / 2), matrixMousePosition)) {
            if (inputHandler.leftMousePressedPulse) playSound(Main.sounds.get("clickIn"), 1, 1);
            if (inputHandler.leftMouseReleasedPulse) {
                playSound(Main.sounds.get("clickOut"), 1, 1);
                return true;
            }
        }
        return false;
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
