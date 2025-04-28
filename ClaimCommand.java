package me.tuoNome.chunkprotection;

import org.bukkit.Chunk;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ClaimCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Solo i giocatori possono usare questo comando.");
            return true;
        }

        Chunk chunk = player.getLocation().getChunk();
        ClaimManager manager = ChunkProtection.getInstance().getClaimManager();

        if (manager.isClaimed(chunk)) {
            player.sendMessage("§cQuesto chunk è già stato claimato!");
            return true;
        }

        manager.claimChunk(chunk, player.getUniqueId());
        player.sendMessage("§aHai claimato questo chunk!");
        return true;
    }
}
