package fr.betterpvp.commands;

import com.hypixel.hytale.server.core.command.AbstractPlayerCommand;
import com.hypixel.hytale.server.core.command.CommandContext;
import com.hypixel.hytale.server.shared.text.Message;
import fr.betterpvp.BetterPvPPlugin;
import fr.betterpvp.data.PlayerStats;

public class KillstreakCommand extends AbstractPlayerCommand {
    private final BetterPvPPlugin plugin;
    
    public KillstreakCommand(BetterPvPPlugin plugin) {
        super("killstreak", "Affiche votre killstreak actuel", false);
        this.plugin = plugin;
    }
    
    @Override
    public void execute(CommandContext ctx) {
        var player = ctx.senderAsPlayer();
        var stats = plugin.getStatsManager().getOrCreateStats(
            player.getUUID(), player.getDisplayName()
        );
        
        int streak = stats.getCurrentKillstreak();
        String message = getKillstreakMessage(streak);
        String bar = getProgressBar(streak);
        
        player.sendMessage(Message.raw("§8§m━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"));
        player.sendMessage(Message.raw("§d§l🔥 KILLSTREAK"));
        player.sendMessage(Message.raw(""));
        player.sendMessage(Message.raw("§fKillstreak actuel: §d" + streak));
        player.sendMessage(Message.raw("§fMeilleur killstreak: §b" + stats.getBestKillstreak()));
        player.sendMessage(Message.raw(""));
        player.sendMessage(Message.raw(bar));
        player.sendMessage(Message.raw(""));
        player.sendMessage(Message.raw(message));
        player.sendMessage(Message.raw("§8§m━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"));
    }
    
    private String getKillstreakMessage(int streak) {
        if (streak == 0) return "§7Aucun killstreak en cours";
        if (streak < 3) return "§eContinue comme ça !";
        if (streak < 5) return "§6Bon début ! Continue !";
        if (streak < 10) return "§6§lIMPRESSIONNANT !";
        if (streak < 15) return "§c§lDOMINATION !";
        if (streak < 20) return "§4§lUNSTOPPABLE !";
        return "§4§l§kII§r §4§lLÉGENDAIRE !§r §4§l§kII";
    }
    
    private String getProgressBar(int streak) {
        int nextMilestone = getNextMilestone(streak);
        int progress = Math.min(streak, nextMilestone);
        int barLength = 20;
        int filled = (int) ((double) progress / nextMilestone * barLength);
        
        StringBuilder bar = new StringBuilder("§7[");
        for (int i = 0; i < barLength; i++) {
            if (i < filled) {
                bar.append("§d▰");
            } else {
                bar.append("§8▱");
            }
        }
        bar.append("§7] §f").append(progress).append("§7/§f").append(nextMilestone);
        
        return bar.toString();
    }
    
    private int getNextMilestone(int streak) {
        if (streak < 3) return 3;
        if (streak < 5) return 5;
        if (streak < 10) return 10;
        if (streak < 15) return 15;
        if (streak < 20) return 20;
        return streak + 5; // Au-delà de 20, paliers de 5
    }
}
