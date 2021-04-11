package Skywars.commands;

import Skywars.Util.Language;
import Skywars.Util.LanguageKeyword;
import Skywars.tools.Map;
import Skywars.tools.Mapbuilder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SkywarsCompleteMap implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if(strings.length == 0) {
                //Überprüfe, ob der richtige Spieler auf eine vorhandene Map zugreift
                if(Mapbuilder.getPlayer() != null && Mapbuilder.getPlayer() == player){
                    //Beim erfolgreichen Abspeichern werden Spieler und Map aus dem Zwischenspeicher gelöscht
                    if(Mapbuilder.getMap().saveMap(player)){
                        Mapbuilder.setMap(null);
                        Mapbuilder.setPlayer(null);
                    }
                } else {
                    player.sendMessage(Language.format(Language.getStringFromKeyword(LanguageKeyword.ERR_NO_WORLD_IN_CREATION)));
                }
            } else {
                return false;
            }
        }
        return true;
    }
}