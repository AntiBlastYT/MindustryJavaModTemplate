package erek.content;

import arc.audio.*;
import arc.files.*;
import mindustry.*;
import mindustry.gen.*;
import mindustry.world.blocks.power.*;

import static mindustry.Vars.*;

public class ErSounds{
    public static Sound

    spencerartillery = new Sound();

    public static void load() {
        if(Vars.headless) return;

        spencerartillery = Vars.tree.loadSound("spencer-audio");
    }

    protected static String soundPath(String soundName){
        String name = "sounds/" + soundName;
        return Vars.tree.get(name + ".ogg").exists() ? name + ".ogg" : name + ".mp3";
    }

    protected static Fi soundFile(String soundName){
        return Vars.tree.get(soundPath(soundName));
    }
}