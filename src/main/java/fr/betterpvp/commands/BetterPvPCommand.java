package fr.betterpvp.commands;

import com.hypixel.hytale.server.core.command.AbstractPlayerCommand;
import com.hypixel.hytale.server.core.command.CommandContext;
import com.hypixel.hytale.server.shared.text.Message;
import fr.betterpvp.BetterPvPPlugin;

public class BetterPvPCommand extends AbstractPlayerCommand {
    private final BetterPvPPlugin plugin;
    
    public BetterPvPCommand(BetterPvPPlugin plugin) {
        super("betterpvp", "Informations sur BetterPvP", false);
        this.plugin = plugin;
    }
    
    @Override
    public void execute(CommandContext ctx) {
        var player = ctx.senderAsPlayer();
        var manager = plugin.getStatsManager();
        
        player.sendMessage(Message.raw("§8§m━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"));
        player.sendMessage(Message.raw("§6§l⚔ BetterPvP v1.0.0"));
        player.sendMessage(Message.raw(""));
        player.sendMessage(Message.raw("§e§lCommandes disponibles:"));
        player.sendMessage(Message.raw("§a/stats §7- Affiche vos statistiques"));
        player.sendMessage(Message.raw("§a/top [kills|kd|ks] §7- Classement des meilleurs"));
        player.sendMessage(Message.raw("§a/killstreak §7- Votre série de kills"));
        player.sendMessage(Message.raw("§a/resetstats --confirm §7- Réinitialiser vos stats"));
        player.sendMessage(Message.raw(""));
        player.sendMessage(Message.raw("§e§lStatistiques globales:"));
        player.sendMessage(Message.raw("§7Joueurs enregistrés: §f" + manager.getTotalPlayers()));
        player.sendMessage(Message.raw("§7Total de kills: §f" + manager.getTotalKills()));
        player.sendMessage(Message.raw(""));
        player.sendMessage(Message.raw("§e§lKillstreaks disponibles:"));
        player.sendMessage(Message.raw("§73 kills §7- §eEn série !"));
        player.sendMessage(Message.raw("§75 kills §7- §6Domine !"));
        player.sendMessage(Message.raw("§710 kills §7- §cUNSTOPPABLE !"));
        player.sendMessage(Message.raw("§715 kills §7- §4LÉGENDAIRE !"));
        player.sendMessage(Message.raw("§720 kills §7- §4§lGODLIKE !"));
        player.sendMessage(Message.raw("§8§m━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"));
    }
}
