package main.arena.particles;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;

import java.awt.*;

public class SimpleParticle extends Particle {

    private static final PVector ACCELERATION = new PVector(0, 0.2f);
    private static final int RADIUS = 3;
    private static final int ALPHA_CHANGE = 15;

    private final Color COLOR;

    private int alpha;

    public SimpleParticle(PApplet p, float x, float y, Color color) {
        super(p, x, y);
        COLOR = color;

        alpha = 254;
        velocity = PVector.fromAngle(P.random(PConstants.TWO_PI)).setMag(p.random(2,5));
    }

    @Override
    public void move() {
        velocity.add(ACCELERATION);
        position.add(velocity);
    }

    @Override
    public void display() {
        P.noStroke();
        P.fill(COLOR.getRGB(), alpha);
        P.circle(position.x, position.y, RADIUS);
        alpha -= ALPHA_CHANGE;
        if (alpha < 0) dead = true;
    }
}
