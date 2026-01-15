package fr.betterpvp.listeners;

import com.hypixel.hytale.server.core.event.events.PlayerJoinEvent;
import com.hypixel.hytale.server.shared.text.Message;
import fr.betterpvp.BetterPvPPlugin;

public class JoinListener {
    private final BetterPvPPlugin plugin;
    
    public JoinListener(BetterPvPPlugin plugin) {
        this.plugin = plugin;
    }
    
    public void onPlayerJoin(PlayerJoinEvent event) {
        var player = event.getPlayer();
        
        // Charger ou créer les stats du joueur
        var stats = plugin.getStatsManager().getOrCreateStats(
            player.getUUID(), player.getDisplayName()
        );
        
        // Message de bienvenue avec stats
        player.sendMessage(Message.raw(""));
        player.sendMessage(Message.raw("§8§m━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"));
        player.sendMessage(Message.raw("§6§l⚔ BetterPvP §e- Vos statistiques"));
        player.sendMessage(Message.raw(""));
        player.sendMessage(Message.raw("§a✓ Kills: §f" + stats.getKills()));
        player.sendMessage(Message.raw("§c✗ Morts: §f" + stats.getDeaths()));
        player.sendMessage(Message.raw("§e⚡ K/D: §f" + String.format("%.2f", stats.getKDRatio())));
        player.sendMessage(Message.raw("§d🔥 Meilleur killstreak: §f" + stats.getBestKillstreak()));
        player.sendMessage(Message.raw(""));
        player.sendMessage(Message.raw("§7Tapez §a/betterpvp §7pour plus d'infos"));
        player.sendMessage(Message.raw("§8§m━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"));
        player.sendMessage(Message.raw(""));
        
        plugin.getLogger().info(player.getDisplayName() + " connecté - Stats chargées");
    }
}
