package me.tuoNome.chunkprotection;

import org.bukkit.plugin.java.JavaPlugin;

public class ChunkProtection extends JavaPlugin {

    private static ChunkProtection instance;
    private ClaimManager claimManager;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        claimManager = new ClaimManager();
        getServer().getPluginManager().registerEvents(new ChunkListener(), this);
        getCommand("claim").setExecutor(new ClaimCommand());
        getCommand("unclaim").setExecutor(new UnclaimCommand());
    }

    @Override
    public void onDisable() {
        claimManager.saveClaims();
    }

    public static ChunkProtection getInstance() {
        return instance;
    }

    public ClaimManager getClaimManager() {
        return claimManager;
    }
}
