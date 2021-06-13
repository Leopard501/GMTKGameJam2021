package main.sound;

import processing.core.PApplet;
import processing.sound.SoundFile;

import static main.Main.sounds;

public class SoundLoader {

    public static void loadSounds(PApplet p) {
        //ui
        sounds.put("clickIn", new SoundFile(p, "sounds/gui/clickIn.wav"));
        sounds.put("clickOut", new SoundFile(p, "sounds/gui/clickOut.wav"));
        sounds.put("popup", new SoundFile(p, "sounds/gui/popup.wav"));

        //hurt
        sounds.put("squish", new SoundFile(p, "sounds/combatants/hurt/squish.wav"));
        sounds.put("crunch", new SoundFile(p, "sounds/combatants/hurt/crunch.wav"));
        sounds.put("crunchSquish", new SoundFile(p, "sounds/combatants/hurt/crunchSquish.wav"));
        sounds.put("splash", new SoundFile(p, "sounds/combatants/hurt/splash.wav"));
        sounds.put("woodDamage", new SoundFile(p, "sounds/combatants/hurt/woodDamage.wav"));
        sounds.put("metalDamage", new SoundFile(p, "sounds/combatants/hurt/metalDamage.wav"));
        sounds.put("metalBreak", new SoundFile(p, "sounds/combatants/hurt/metalBreak.wav"));
        sounds.put("deflect", new SoundFile(p, "sounds/combatants/hurt/deflect.wav"));

        //ability
        sounds.put("whooshImpact", new SoundFile(p, "sounds/combatants/ability/whooshImpact.wav"));
        sounds.put("magic", new SoundFile(p, "sounds/combatants/ability/magic.wav"));
        sounds.put("slimeSquish", new SoundFile(p, "sounds/combatants/ability/slimeSquish.wav"));
        sounds.put("metalPlace", new SoundFile(p, "sounds/combatants/ability/metalPlace.wav"));
        sounds.put("slingshot", new SoundFile(p, "sounds/combatants/ability/slingshot.wav"));
        sounds.put("darkMagic", new SoundFile(p, "sounds/combatants/ability/darkMagic.wav"));
        sounds.put("explosion", new SoundFile(p, "sounds/combatants/ability/explosion.wav"));
        sounds.put("badMagic", new SoundFile(p, "sounds/combatants/ability/badMagic.wav"));
        sounds.put("crossbow", new SoundFile(p, "sounds/combatants/ability/crossbow.wav"));
        sounds.put("ice", new SoundFile(p, "sounds/combatants/ability/ice.wav"));
        sounds.put("energy", new SoundFile(p, "sounds/combatants/ability/energy.wav"));
    }
}
