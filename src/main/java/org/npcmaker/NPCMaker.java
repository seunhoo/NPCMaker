package org.npcmaker;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.npcmaker.Command.CommandManager;
import org.npcmaker.event.EventManager;

public final class NPCMaker extends JavaPlugin {
    private static Plugin plugin;

    public static Plugin getPlugin(){
        return plugin;
    }

    @Override
    public void onEnable() {
        plugin = this;
        // Plugin startup logic
        CommandManager commandManager = new CommandManager(this);
        new EventManager(this.getServer(),this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
