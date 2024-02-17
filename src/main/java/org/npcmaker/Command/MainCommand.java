package org.npcmaker.Command;

import com.mojang.authlib.GameProfile;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.protocol.game.ClientboundPlayerInfoRemovePacket;
import net.minecraft.network.protocol.game.ClientboundPlayerInfoUpdatePacket;
import net.minecraft.network.protocol.game.ClientboundSetEntityDataPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ClientInformation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_20_R2.CraftServer;
import org.bukkit.craftbukkit.v1_20_R2.CraftWorld;
import org.bukkit.craftbukkit.v1_20_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.npcmaker.NPCMaker;
import org.npcmaker.module.PacketModule;
import org.npcmaker.module.SkinModule;

import java.util.Collections;
import java.util.Objects;
import java.util.UUID;

// 기본 플러그인 명령어 입니다
public class MainCommand {
    PacketModule packetModule = new PacketModule();
    SkinModule skinModule = new SkinModule();
    public void SpawnEntity(Player player){
        Location location = player.getLocation();
        MinecraftServer minecraftServer = ((CraftServer) Bukkit.getServer()).getServer();
        ServerLevel serverLevel = ((CraftWorld) Objects.requireNonNull(location.getWorld())).getHandle();
        GameProfile realWakgood = new GameProfile(UUID.randomUUID(), "RealWakgood");
        skinModule.setSkin("RealWakgood",realWakgood);
        skinModule.changeSkin("RealWakgood","RealWakgood" ,realWakgood);
        ServerPlayer serverPlayer = new ServerPlayer(minecraftServer, serverLevel, realWakgood, ClientInformation.createDefault());
        serverPlayer.setPos(location.getX(), location.getY(), location.getZ());

        SynchedEntityData synchedEntityData = serverPlayer.getEntityData();
        synchedEntityData.set(new EntityDataAccessor<>(17, EntityDataSerializers.BYTE), (byte) 127);

        packetModule.setValue(serverPlayer, "c", ((CraftPlayer) player).getHandle().connection);

        packetModule.sendPacket(new ClientboundPlayerInfoUpdatePacket(ClientboundPlayerInfoUpdatePacket.Action.ADD_PLAYER, serverPlayer), player);
        packetModule.sendPacket(new ClientboundAddEntityPacket(serverPlayer), player);
        packetModule.sendPacket(new ClientboundSetEntityDataPacket(serverPlayer.getId(), synchedEntityData.getNonDefaultValues()), player);
        Bukkit.getScheduler().runTaskLater(NPCMaker.getPlugin(), ()->{
            packetModule.sendPacket(new ClientboundPlayerInfoRemovePacket(Collections.singletonList(serverPlayer.getUUID())), player);
        }, 2 * 20);
    }
}
