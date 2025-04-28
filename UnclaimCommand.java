package me.tuoNome.chunkprotection;

import org.bukkit.Chunk;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class UnclaimCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Solo i giocatori possono usare questo comando.");
            return true;
        }

        Chunk chunk = player.getLocation().getChunk();
        ClaimManager manager = ChunkProtection.getInstance().getClaimManager();

        if (!manager.isClaimed(chunk)) {
            player.sendMessage("§cQuesto chunk non è claimato!");
            return true;
        }

        if (!manager.getOwner(chunk).equals(player.getUniqueId())) {
            player.sendMessage("§cNon puoi unclaimare un chunk che non possiedi!");
            return true;
        }

        manager.unclaimChunk(chunk);
        player.sendMessage("§aHai liberato questo chunk!");
        return true;
    }
}
