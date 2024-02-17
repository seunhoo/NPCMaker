package org.npcmaker.Command;

import com.mojang.authlib.GameProfile;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.protocol.game.ClientboundPlayerInfoUpdatePacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_20_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.npcmaker.NPCMaker;


import java.util.Objects;
import java.util.UUID;

// 커맨드 를 할 수 있게 해줍니다!
public class CommandManager implements CommandExecutor {
    private final MainCommand mainCommand = new MainCommand();
    public CommandManager(NPCMaker npcMaker) {
        Objects.requireNonNull(npcMaker.getCommand("nm")).setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command,  String label, String[] args) {
        if(label.equals(NpcCommand.COMMAND_NM.getMessage())){
            mainCommand.SpawnEntity((Player) sender);
        }
        return true;
    }

}
