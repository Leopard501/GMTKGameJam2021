package main.misc;

import processing.core.PApplet;
import processing.core.PImage;

import static main.Main.*;

public class SpriteLoader {
    
    /**
     * Loads animations.
     * @param p the PApplet
     */
    public static void loadAnimations(PApplet p) {
        //ui
        getAnimation(p, "genericButton", "BT", 3);

        //combatants
        getAnimation(p, "idle", "fighter", "CB", 2);
        getAnimation(p, "attack", "fighter", "CB", 9);
        getAnimation(p, "ability", "fighter", "CB", 11);

        getAnimation(p, "idle", "healer", "CB", 2);
        getAnimation(p, "attack", "healer", "CB", 10);
        getAnimation(p, "ability", "healer", "CB", 8);

        getAnimation(p, "idle", "shielder", "CB", 2);
        getAnimation(p, "attack", "shielder", "CB", 9);
        getAnimation(p, "ability", "shielder", "CB", 12);

        getAnimation(p, "idle", "spider", "CB", 2);
        getAnimation(p, "attack", "spider", "CB", 9);
        getAnimation(p, "ability", "spider", "CB", 12);

        getAnimation(p, "idle", "slime", "CB", 2);
        getAnimation(p, "attack", "slime", "CB", 10);
        getAnimation(p, "ability", "slime", "CB", 12);

        getAnimation(p, "idle", "necromancer", "CB", 2);
        getAnimation(p, "attack", "necromancer", "CB", 11);
        getAnimation(p, "ability", "necromancer", "CB", 8);

        getAnimation(p, "idle", "android", "CB", 2);
        getAnimation(p, "attack", "android", "CB", 9);

        getAnimation(p, "idle", "drone", "CB", 2);
        getAnimation(p, "attack", "drone", "CB", 10);
        getAnimation(p, "ability", "drone", "CB", 11);

        getAnimation(p, "idle", "router", "CB", 2);
        getAnimation(p, "attack", "router", "CB", 7);
        getAnimation(p, "ability", "router", "CB", 10);

        getAnimation(p, "idle", "skeleton", "CB", 2);
        getAnimation(p, "attack", "skeleton", "CB", 11);

        getAnimation(p, "idle", "t800", "CB", 2);
        getAnimation(p, "attack", "t800", "CB", 24);
        getAnimation(p, "ability", "t800", "CB", 25);

        getAnimation(p, "idle", "pizzaBot", "CB", 2);
        getAnimation(p, "attack", "pizzaBot", "CB", 10);
        getAnimation(p, "ability", "pizzaBot", "CB", 7);

        getAnimation(p, "idle", "smartSpeaker", "CB", 2);
        getAnimation(p, "attack", "smartSpeaker", "CB", 14);
        getAnimation(p, "ability", "smartSpeaker", "CB", 5);
    }

    private static void getAnimation(PApplet p, String name, String folder, String type, int length) {
        StringBuilder path = new StringBuilder().append("sprites/");
        switch (type) {
            case "BT":
                path.append("gui/buttons");
                break;
            case "CB":
                path.append("combatants/");
        }
        path.append(folder).append("/");
        path.append(name).append("/");
        String fullName = folder+name+type;
        animations.put(fullName, new PImage[length]);
        for (int i = length-1; i >= 0; i--) {
            animations.get(fullName)[i] = p.loadImage(path + PApplet.nf(i,3) + ".png");
        }
    }

    private static void getAnimation(PApplet p, String name, String type, int length) {
        getAnimation(p, name, "", type, length);
    }

    public static void loadSprites(PApplet p) {
        //buffs
        sprites.put("stickyBF", p.loadImage("sprites/buffs/sticky.png"));
        sprites.put("shieldedBF", p.loadImage("sprites/buffs/shielded.png"));
        sprites.put("pizzaBF", p.loadImage("sprites/buffs/pizza.png"));

        //backgrounds
        sprites.put("arenaBG", p.loadImage("sprites/backgrounds/arena.png"));
    }
}
