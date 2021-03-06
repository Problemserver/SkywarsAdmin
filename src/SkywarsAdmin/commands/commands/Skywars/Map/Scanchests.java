package SkywarsAdmin.commands.commands.Skywars.Map;

import SkywarsAdmin.Util.Language;
import SkywarsAdmin.tools.Mapbuilder;
import com.mysql.jdbc.Buffer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class Scanchests{
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if(strings.length == 0) {
                //Überprüfe, ob der richtige Spieler auf eine vorhandene Map zugreift
                if(Mapbuilder.getPlayer() != null && Mapbuilder.getPlayer() == player){
                    if(!Mapbuilder.getMap().positionsAvailable()){
                        player.sendMessage(Language.format(Language.getStringFromKeyword(Language.LanguageKeyword.ERR_NO_POSITION)));
                    } else {
                        List<Location> chests = new ArrayList<>();
                        List<Location> middleChests = new ArrayList<>();
                        Location searchAt = Mapbuilder.getMap().getPos1().clone();
                        Vector searchVector = Mapbuilder.getMap().getPos2().toVector().subtract(searchAt.toVector());
                        if(searchVector.getBlockX() < 0){
                            searchAt.setX(searchAt.getBlockX()+searchVector.getBlockX());
                            searchVector.setX(searchVector.getBlockX()*-1);
                        }
                        if(searchVector.getBlockY() < 0){
                            searchAt.setY(searchAt.getBlockY()+searchVector.getBlockY());
                            searchVector.setY(searchVector.getBlockY()*-1);
                        }
                        if(searchVector.getBlockZ() < 0){
                            searchAt.setZ(searchAt.getBlockZ()+searchVector.getBlockZ());
                            searchVector.setZ(searchVector.getBlockZ()*-1);
                        }
                        for(int x = searchAt.getBlockX(); x <= searchAt.getBlockX()+searchVector.getBlockX(); x++){
                            for(int y = searchAt.getBlockY(); y <= searchAt.getBlockY()+searchVector.getBlockY(); y++){
                                for(int z = searchAt.getBlockZ(); z <= searchAt.getBlockZ()+searchVector.getBlockZ(); z++){
                                    player.sendMessage(x+","+y+","+z);
                                    if(player.getWorld().getBlockAt(x, y, z).getType().compareTo(Material.getMaterial("CHEST")) == 0) {
                                        chests.add(new Location(null, x, y, z));
                                    }
                                    if(player.getWorld().getBlockAt(x, y, z).getType().compareTo(Material.getMaterial("ENDER_CHEST")) == 0) {
                                        middleChests.add(new Location(null, x, y, z));
                                    }
                                    try {
                                        Thread.sleep(1);
                                    } catch (Exception e){
                                        Bukkit.broadcastMessage(e.getMessage());
                                    }
                                }
                            }
                        }
                        Mapbuilder.getMap().setChests(chests);
                        Mapbuilder.getMap().setMiddleChests(middleChests);
                        player.sendMessage(Language.format(Language.getStringFromKeyword(Language.LanguageKeyword.CMD_SCAN_CHESTS)));
                    }
                } else {
                    player.sendMessage(Language.format(Language.getStringFromKeyword(Language.LanguageKeyword.ERR_NO_WORLD_IN_CREATION)));
                }
            } else {
                return false;
            }
        }
        return true;
    }
}
