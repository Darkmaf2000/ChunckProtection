package me.tuoNome.chunkprotection;

import org.bukkit.Chunk;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.UUID;

public class ChunkListener implements Listener {

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        Chunk chunk = event.getBlock().getChunk();
        ClaimManager manager = ChunkProtection.getInstance().getClaimManager();

        if (manager.isClaimed(chunk)) {
            UUID owner = manager.getOwner(chunk);
            if (!player.getUniqueId().equals(owner)) {
                player.sendMessage("§cQuesto chunk è claimato da un altro giocatore!");
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Chunk chunk = event.getBlock().getChunk();
        ClaimManager manager = ChunkProtection.getInstance().getClaimManager();

        if (manager.isClaimed(chunk)) {
            UUID owner = manager.getOwner(chunk);
            if (!player.getUniqueId().equals(owner)) {
                player.sendMessage("§cQuesto chunk è claimato da un altro giocatore!");
                event.setCancelled(true);
            }
        }
    }
}
