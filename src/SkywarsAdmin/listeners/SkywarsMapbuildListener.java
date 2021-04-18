package SkywarsAdmin.listeners;

import SkywarsAdmin.Util.Language;
import SkywarsAdmin.tools.Mapbuilder;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;

public class SkywarsMapbuildListener implements Listener {
 public SkywarsMapbuildListener(){

 }
 @EventHandler
 public void onPlayerChangedWorldEvent(PlayerChangedWorldEvent event){
     if(Mapbuilder.getPlayer() == event.getPlayer()){
         Mapbuilder.reset();
         event.getPlayer().sendMessage(Language.format(Language.getStringFromKeyword(Language.LanguageKeyword.CMD_MAP_TRASHED)));
     }
 }
}
