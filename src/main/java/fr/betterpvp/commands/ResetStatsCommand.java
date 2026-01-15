package fr.betterpvp.commands;

import com.hypixel.hytale.server.core.command.AbstractPlayerCommand;
import com.hypixel.hytale.server.core.command.CommandContext;
import com.hypixel.hytale.server.shared.text.Message;
import fr.betterpvp.BetterPvPPlugin;

public class ResetStatsCommand extends AbstractPlayerCommand {
    private final BetterPvPPlugin plugin;
    
    public ResetStatsCommand(BetterPvPPlugin plugin) {
        super("resetstats", "Réinitialise vos statistiques", true);
        this.plugin = plugin;
    }
    
    @Override
    public void execute(CommandContext ctx) {
        var player = ctx.senderAsPlayer();
        String[] args = ctx.arguments();
        
        // Vérifier la confirmation
        if (args.length == 0 || !args[0].equals("--confirm")) {
            player.sendMessage(Message.raw("§c⚠ ATTENTION !"));
            player.sendMessage(Message.raw("§7Cette action va réinitialiser TOUTES vos statistiques."));
            player.sendMessage(Message.raw("§7Pour confirmer, tapez: §e/resetstats --confirm"));
            return;
        }
        
        var stats = plugin.getStatsManager().getStats(player.getUUID());
        
        if (stats == null) {
            player.sendMessage(Message.raw("§cVous n'avez aucune statistique à réinitialiser."));
            return;
        }
        
        stats.reset();
        player.sendMessage(Message.raw("§8§m━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"));
        player.sendMessage(Message.raw("§a✓ Vos statistiques ont été réinitialisées !"));
        player.sendMessage(Message.raw("§7Toutes vos stats sont maintenant à 0."));
        player.sendMessage(Message.raw("§8§m━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"));
        
        plugin.getStatsManager().saveStats();
    }
}
