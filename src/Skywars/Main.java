package Skywars;

import Skywars.commands.*;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{
    public void onEnable(){
        getLogger().info("Loading Essence Plugin.");
        getLogger().info("Downloading Essence Language.");
        getLogger().info("Loading Essence Commands.");
        registerCommands();
        getLogger().info("Loading Essence Listeners.");
        registerListeners();
        getLogger().info("Essence primed and ready.");
    }

    private void registerCommands(){
        getCommand("skywarscompletemap").setExecutor(new SkywarsCompleteMap());
        getCommand("skywarscreatemap").setExecutor(new SkywarsCreateMap());
        getCommand("skywarsresetspawnpoints").setExecutor(new SkywarsResetSpawnpoints());
        getCommand("skywarsscanchests").setExecutor(new SkywarsScanChests());
        getCommand("skywarssetpos1").setExecutor(new SkywarsSetPos1());
        getCommand("skywarssetpos2").setExecutor(new SkywarsSetPos2());
        getCommand("skywarssetspawn").setExecutor(new SkywarsSetSpawn());
    }

    private void registerListeners(){
        //getServer().getPluginManager().registerEvents(new VanishListener(vanish), this);
    }
}