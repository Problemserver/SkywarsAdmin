package SkywarsAdmin.tools;

import SkywarsAdmin.Main;
import SkywarsCore.Map;

import SkywarsAdmin.Util.Language;
import org.bukkit.entity.Player;
import org.yaml.snakeyaml.Yaml;

import java.io.*;

public class MapAdmin extends Map{

    public MapAdmin(String mapname) {
        super(mapname);
    }

    public boolean save(Player player){
        boolean complete = true;
        if(teamsize <= 0){
            player.sendMessage(Language.format(Language.getStringFromKeyword(Language.LanguageKeyword.ERR_NO_TEAMSIZE)));
            complete = false;
        }
        if(!positionsAvailable()){
            player.sendMessage(Language.format(Language.getStringFromKeyword(Language.LanguageKeyword.ERR_NO_POSITION)));
            complete = false;
        }
        if(spawnpoints.isEmpty()){
            player.sendMessage(Language.format(Language.getStringFromKeyword(Language.LanguageKeyword.ERR_NO_SPAWNPOINTS)));
            complete = false;
        }
        if(chests.isEmpty() || middleChests.isEmpty()){
            player.sendMessage(Language.format(Language.getStringFromKeyword(Language.LanguageKeyword.ERR_NO_CHESTS)));
            complete = false;
        }
        if(complete) {
            String pathname = Main.PATH + "/Map";
            String filename = mapname + "-map" + ".yml";

            try {
                new File(pathname).mkdirs();
                if(!new File(pathname + "/" + filename).exists() || Mapbuilder.isOverride()) {
                    PrintWriter writer = new PrintWriter(pathname + "/" + filename);
                    Yaml yaml = new Yaml();
                    yaml.dump(this, writer);
                    Mapbuilder.reset();
                    player.sendMessage(Language.format(Language.getStringFromKeyword(Language.LanguageKeyword.CMD_MAP_SAVED)));
                } else {
                    Mapbuilder.setOverride(true);
                    player.sendMessage(Language.format(Language.getStringFromKeyword(Language.LanguageKeyword.ERR_MAP_EXISTING)));
                    return false;
                }
            }
            catch (IOException exception){
                player.sendMessage(Language.format(Language.getStringFromKeyword(Language.LanguageKeyword.ERR_SAVING_UNSUCCESSFUL_Map)));
                return false;
            }
            return true;

        } else {
            return false;
        }
    }
}
