package fr.betterpvp.commands;

import com.hypixel.hytale.server.core.command.AbstractPlayerCommand;
import com.hypixel.hytale.server.core.command.CommandContext;
import com.hypixel.hytale.server.shared.text.Message;
import fr.betterpvp.BetterPvPPlugin;
import fr.betterpvp.data.PlayerStats;
import java.util.List;

public class TopCommand extends AbstractPlayerCommand {
    private final BetterPvPPlugin plugin;
    
    public TopCommand(BetterPvPPlugin plugin) {
        super("top", "Affiche le classement PvP", false);
        this.plugin = plugin;
    }
    
    @Override
    public void execute(CommandContext ctx) {
        var player = ctx.senderAsPlayer();
        String[] args = ctx.arguments();
        
        String type = args.length > 0 ? args[0].toLowerCase() : "kills";
        
        player.sendMessage(Message.raw("§8§m━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"));
        
        switch (type) {
            case "kills":
                showTopKills(ctx);
                break;
            case "kd":
                showTopKD(ctx);
                break;
            case "killstreak":
            case "ks":
                showTopKillstreak(ctx);
                break;
            default:
                player.sendMessage(Message.raw("§cUsage: /top [kills|kd|killstreak]"));
                return;
        }
        
        player.sendMessage(Message.raw("§8§m━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"));
    }
    
    private void showTopKills(CommandContext ctx) {
        var player = ctx.senderAsPlayer();
        List<PlayerStats> top = plugin.getStatsManager().getTopPlayersByKills(10);
        
        player.sendMessage(Message.raw("§6§l⚔ TOP 10 - KILLS"));
        player.sendMessage(Message.raw(""));
        
        int rank = 1;
        for (PlayerStats stats : top) {
            String medal = getMedal(rank);
            player.sendMessage(Message.raw(
                medal + " §e#" + rank + " §f" + stats.getPlayerName() + 
                " §7- §a" + stats.getKills() + " kills"
            ));
            rank++;
        }
    }
    
    private void showTopKD(CommandContext ctx) {
        var player = ctx.senderAsPlayer();
        List<PlayerStats> top = plugin.getStatsManager().getTopPlayersByKD(10);
        
        player.sendMessage(Message.raw("§6§l⚡ TOP 10 - K/D RATIO"));
        player.sendMessage(Message.raw(""));
        
        int rank = 1;
        for (PlayerStats stats : top) {
            String medal = getMedal(rank);
            player.sendMessage(Message.raw(
                medal + " §e#" + rank + " §f" + stats.getPlayerName() + 
                " §7- §e" + String.format("%.2f", stats.getKDRatio()) + " K/D"
            ));
            rank++;
        }
    }
    
    private void showTopKillstreak(CommandContext ctx) {
        var player = ctx.senderAsPlayer();
        List<PlayerStats> top = plugin.getStatsManager().getTopPlayersByKillstreak(10);
        
        player.sendMessage(Message.raw("§6§l🔥 TOP 10 - KILLSTREAK"));
        player.sendMessage(Message.raw(""));
        
        int rank = 1;
        for (PlayerStats stats : top) {
            String medal = getMedal(rank);
            player.sendMessage(Message.raw(
                medal + " §e#" + rank + " §f" + stats.getPlayerName() + 
                " §7- §d" + stats.getBestKillstreak() + " kills"
            ));
            rank++;
        }
    }
    
    private String getMedal(int rank) {
        return switch (rank) {
            case 1 -> "§6🥇";
            case 2 -> "§7🥈";
            case 3 -> "§c🥉";
            default -> "§8▪";
        };
    }
}
