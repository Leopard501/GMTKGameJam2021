package main.arena.combatants;

import main.Main;
import main.arena.buffs.Bleeding;
import main.arena.buffs.Shielded;
import main.arena.buffs.StatBoost;
import main.arena.buffs.Sticky;
import main.arena.particles.FloatParticle;
import main.arena.particles.GravityParticle;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

import java.awt.*;

import static main.Main.*;
import static main.misc.Utilities.pointOnRect;
import static main.sound.SoundUtilities.playSound;
import static processing.core.PConstants.CENTER;
import static processing.core.PConstants.CORNER;

public abstract class Combatant {

    public boolean alive;
    public boolean isEnemy;
    public int mp;
    public int mpCost;

    //This might suck, but it's a game jam so who cares.
    public Bleeding bleeding;
    public Sticky sticky;
    public Shielded shielded;
    public StatBoost statBoost;

    protected static final PVector SIZE = new PVector(20, 20);

    protected final PApplet P;

    protected PVector position;
    protected Color bloodColor;
    protected int hp;
    protected int maxHp;
    protected int maxMp;
    protected int attackDamage;
    protected int betweenIdleFrames;
    protected float abilityStrength;

    private int frame;
    private int betweenFrameTimer;
    private PImage[] idleAnimation;

    /**
     * These are the little dudes that will fight.
     */
    public Combatant(PApplet p, int maxHp, int maxMp, int mpCost, int attackDamage,
                     float abilityStrength, Color bloodColor) {
        P = p;
        this.maxHp = maxHp;
        this.maxMp = maxMp;
        this.mpCost = mpCost;
        this.attackDamage = attackDamage;
        this.abilityStrength = abilityStrength;
        this.bloodColor = bloodColor;

        alive = true;
        hp = maxHp;
        mp = maxMp;
        betweenIdleFrames = 30;
        betweenFrameTimer = (int) P.random(betweenIdleFrames);
    }

    protected void loadAnimations(String name) {
        idleAnimation = animations.get(name + "idle" + "CB");
    }

    public void setPosition(float x, float y) {
        position = new PVector(x, y);
    }

    public void display() {
        if (idleAnimation == null) {
            P.fill(255);
            P.noStroke();
            P.circle(position.x, position.y, SIZE.x);
        } else {
            animate();
            if (isEnemy) P.image(idleAnimation[frame], position.x, position.y, -SIZE.x, SIZE.y);
            P.image(idleAnimation[frame], position.x, position.y);
        }
        hpBar();
        if (maxMp > 0) mpBar();
    }

    private void animate() {
        betweenFrameTimer++;
        if (betweenFrameTimer >= betweenIdleFrames) {
            betweenFrameTimer = 0;
            frame++;
            if (frame >= idleAnimation.length) frame = 0;
        }
    }

    private void hpBar() {
        P.rectMode(CORNER);
        P.strokeWeight(0.3f);
        Color barColor = new Color(255, 0, 0);
        float barWidth = SIZE.x * (hp / (float) maxHp);
        P.stroke(barColor.getRGB());
        P.noFill();
        P.rect(position.x - SIZE.x / 2, position.y + SIZE.y / 2 + 3, SIZE.x, 3);
        P.fill(barColor.getRGB());
        if (hp > 0) P.rect(position.x - SIZE.x / 2, position.y + SIZE.y / 2 + 3, barWidth, 3);
        P.rectMode(CENTER);
        P.strokeWeight(1);
    }

    private void mpBar() {
        P.rectMode(CORNER);
        P.strokeWeight(0.2f);
        Color barColor = new Color(0, 175, 255);
        float barWidth = SIZE.x * (mp / (float) maxMp);
        P.stroke(barColor.getRGB());
        P.noFill();
        P.rect(position.x - SIZE.x / 2, position.y + SIZE.y / 2 + 7, SIZE.x, 3);
        P.fill(barColor.getRGB());
        if (mp > 0) P.rect(position.x - SIZE.x / 2, position.y + SIZE.y / 2 + 7, barWidth, 3);
        P.rectMode(CENTER);
        P.strokeWeight(1);
    }

    public void selectionOverlay() {
        P.noFill();
        P.stroke(255);
        P.rect(position.x, position.y, SIZE.x, SIZE.y);
    }

    public int actionState() {
        if (pointOnRect(position, new PVector(SIZE.x / 2, SIZE.y / 2), matrixMousePosition)) {
            if (inputHandler.leftMousePressedPulse) playSound(Main.sounds.get("clickIn"), 1, 1);
            if (inputHandler.leftMouseReleasedPulse) {
                playSound(Main.sounds.get("clickOut"), 1, 1);
                return 1;
            }
            if (inputHandler.rightMousePressedPulse) playSound(Main.sounds.get("clickIn"), 1, 1);
            if (inputHandler.rightMouseReleasedPulse) {
                playSound(Main.sounds.get("clickOut"), 1, 1);
                return 2;
            }
        }
        return 0;
    }

    public void attack(Combatant other) {
        int damage = attackDamage;
        if (statBoost != null) {
            damage = round((float) damage * statBoost.strength);
        }
        other.hurt(damage);
    }

    public void hurt(int amount) {
        if (shielded != null) return;
        int damage = amount;
        if (statBoost != null) {
            damage = round((float) damage * (2 - statBoost.strength));
        }
        hp -= damage;
        for (int i = 0; i < 8; i++) arena.particles.add(new GravityParticle(P, position.x, position.y, bloodColor));
        if (hp <= 0) alive = false;
    }

    public void heal(int amount) {
        hp += amount;
        if (hp > maxHp) hp = maxHp;
        for (int i = 0; i < 8; i++) arena.particles.add(new FloatParticle(P, position.x, position.y,
          new Color(0, 255, 120)));
    }

    public void updateBuffs() {
        if (bleeding != null) bleeding.effect(this);
        if (sticky != null) sticky.effect(this);
    }
}
