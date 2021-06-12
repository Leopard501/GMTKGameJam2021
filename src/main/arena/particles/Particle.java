package main.arena.particles;

import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;

import static main.Main.arena;

public abstract class Particle {

    protected final PApplet P;

    protected PVector position;
    protected PVector velocity;
    protected boolean dead;

    public Particle(PApplet p, float x, float y) {
        P = p;
        position = new PVector(x, y);
    }

    public void main() {
        if (dead) arena.particles.remove(this);
        move();
        display();
    }

    protected abstract void move();

    protected abstract void display();
}
