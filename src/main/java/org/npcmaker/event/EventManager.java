package org.npcmaker.event;

import org.bukkit.Server;
import org.bukkit.plugin.Plugin;

public class EventManager {
    public EventManager(Server server, Plugin plugin) {
        server.getPluginManager().registerEvents(new PlayerEvent(), plugin);
    }
}
