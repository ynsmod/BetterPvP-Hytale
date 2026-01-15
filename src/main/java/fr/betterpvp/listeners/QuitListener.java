package fr.betterpvp.listeners;

import com.hypixel.hytale.server.core.event.events.PlayerQuitEvent;
import fr.betterpvp.BetterPvPPlugin;

public class QuitListener {
    private final BetterPvPPlugin plugin;
    
    public QuitListener(BetterPvPPlugin plugin) {
        this.plugin = plugin;
    }
    
    public void onPlayerQuit(PlayerQuitEvent event) {
        var player = event.getPlayer();
        
        // Sauvegarder les stats avant la déconnexion
        plugin.getStatsManager().saveStats();
        
        plugin.getLogger().info(player.getDisplayName() + " déconnecté - Stats sauvegardées");
    }
}
