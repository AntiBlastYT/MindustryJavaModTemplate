package erek;

import arc.*;
import arc.util.*;
import erek.content.ErSounds;
import erek.content.blocks.*;
import erek.content.units.*;
import erek.content.utils.*;
import erek.content.bullets.*;
import mindustry.game.EventType.*;
import mindustry.mod.*;
import erek.content.utils.*;;

public class ExampleJavaMod extends Mod{

    public ExampleJavaMod(){
        Log.info("Loaded ExampleJavaMod constructor.");

        Events.on(ClientLoadEvent.class, e -> {
        });
    }

    @Override
    public void loadContent(){
        ErSounds.load();
        ErTurret.load();
        ErUnits.load();
        ErUnitBlocks.load();
        Utils.init();
    }

}
