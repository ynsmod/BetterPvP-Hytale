package fr.betterpvp.commands;

import com.hypixel.hytale.server.core.command.AbstractPlayerCommand;
import com.hypixel.hytale.server.core.command.CommandContext;
import com.hypixel.hytale.server.shared.text.Message;
import fr.betterpvp.BetterPvPPlugin;
import fr.betterpvp.data.PlayerStats;

public class StatsCommand extends AbstractPlayerCommand {
    private final BetterPvPPlugin plugin;
    
    public StatsCommand(BetterPvPPlugin plugin) {
        super("stats", "Affiche vos statistiques PvP", false);
        this.plugin = plugin;
    }
    
    @Override
    public void execute(CommandContext ctx) {
        var player = ctx.senderAsPlayer();
        var stats = plugin.getStatsManager().getOrCreateStats(
            player.getUUID(), player.getDisplayName()
        );
        
        // Message de stats formaté
        player.sendMessage(Message.raw("§8§m━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"));
        player.sendMessage(Message.raw("§6§l⚔ Statistiques PvP de " + player.getDisplayName()));
        player.sendMessage(Message.raw(""));
        player.sendMessage(Message.raw("§a✓ Kills: §f" + stats.getKills()));
        player.sendMessage(Message.raw("§c✗ Morts: §f" + stats.getDeaths()));
        player.sendMessage(Message.raw("§e⚡ K/D Ratio: §f" + String.format("%.2f", stats.getKDRatio())));
        player.sendMessage(Message.raw("§d🔥 Killstreak actuel: §f" + stats.getCurrentKillstreak()));
        player.sendMessage(Message.raw("§b⭐ Meilleur killstreak: §f" + stats.getBestKillstreak()));
        player.sendMessage(Message.raw("§8§m━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"));
    }
}
