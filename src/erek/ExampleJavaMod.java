package erek;

import arc.*;
import arc.util.*;
import erek.content.ErTurret;
import mindustry.game.EventType.*;
import mindustry.mod.*;

public class ExampleJavaMod extends Mod{

    public ExampleJavaMod(){
        Log.info("Loaded ExampleJavaMod constructor.");

        Events.on(ClientLoadEvent.class, e -> {
        });
    }

    @Override
    public void loadContent(){
        ErTurret.load();
    }

}
