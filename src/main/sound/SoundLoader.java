package main.sound;

import processing.core.PApplet;
import processing.sound.SoundFile;

import static main.Main.sounds;

public class SoundLoader {

    public static void loadSounds(PApplet p) {
        //ui
        sounds.put("clickIn", new SoundFile(p, "sounds/gui/clickIn.wav"));
        sounds.put("clickOut", new SoundFile(p, "sounds/gui/clickOut.wav"));

        //hurt
        sounds.put("squish", new SoundFile(p, "sounds/combatants/hurt/squish.wav"));
        sounds.put("crunch", new SoundFile(p, "sounds/combatants/hurt/crunch.wav"));
        sounds.put("crunchSquish", new SoundFile(p, "sounds/combatants/hurt/crunchSquish.wav"));
        sounds.put("splash", new SoundFile(p, "sounds/combatants/hurt/splash.wav"));
        sounds.put("woodDamage", new SoundFile(p, "sounds/combatants/hurt/woodDamage.wav"));
        sounds.put("metalDamage", new SoundFile(p, "sounds/combatants/hurt/metalDamage.wav"));
        sounds.put("metalBreak", new SoundFile(p, "sounds/combatants/hurt/metalBreak.wav"));
    }
}
