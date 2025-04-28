package me.tuoNome.chunkprotection;

import org.bukkit.Chunk;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

public class ClaimManager {

    private final HashMap<String, UUID> claimedChunks = new HashMap<>();
    private final File file;
    private final FileConfiguration config;

    public ClaimManager() {
        file = new File(ChunkProtection.getInstance().getDataFolder(), "claims.yml");
        config = YamlConfiguration.loadConfiguration(file);
        loadClaims();
    }

    private void loadClaims() {
        if (config.getConfigurationSection("claims") != null) {
            for (String key : config.getConfigurationSection("claims").getKeys(false)) {
                UUID owner = UUID.fromString(config.getString("claims." + key));
                claimedChunks.put(key, owner);
            }
        }
    }

    public void saveClaims() {
        for (String key : claimedChunks.keySet()) {
            config.set("claims." + key, claimedChunks.get(key).toString());
        }
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isClaimed(Chunk chunk) {
        return claimedChunks.containsKey(getChunkKey(chunk));
    }

    public UUID getOwner(Chunk chunk) {
        return claimedChunks.get(getChunkKey(chunk));
    }

    public void claimChunk(Chunk chunk, UUID playerUUID) {
        claimedChunks.put(getChunkKey(chunk), playerUUID);
    }

    public void unclaimChunk(Chunk chunk) {
        claimedChunks.remove(getChunkKey(chunk));
    }

    private String getChunkKey(Chunk chunk) {
        return chunk.getWorld().getName() + "," + chunk.getX() + "," + chunk.getZ();
    }
}
