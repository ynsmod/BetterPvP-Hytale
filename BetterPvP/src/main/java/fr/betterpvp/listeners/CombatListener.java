package fr.betterpvp.listeners;

import com.hypixel.hytale.server.core.event.events.PlayerDeathEvent;
import com.hypixel.hytale.server.shared.text.Message;
import fr.betterpvp.BetterPvPPlugin;
import fr.betterpvp.data.PlayerStats;

public class CombatListener {
    private final BetterPvPPlugin plugin;
    
    public CombatListener(BetterPvPPlugin plugin) {
        this.plugin = plugin;
    }
    
    public void onPlayerDeath(PlayerDeathEvent event) {
        var victim = event.getPlayer();
        var killer = event.getKiller();
        
        // Stats de la victime
        var victimStats = plugin.getStatsManager().getOrCreateStats(
            victim.getUUID(), victim.getDisplayName()
        );
        int victimStreak = victimStats.getCurrentKillstreak();
        victimStats.addDeath();
        
        // Si tué par un joueur
        if (killer != null) {
            var killerStats = plugin.getStatsManager().getOrCreateStats(
                killer.getUUID(), killer.getDisplayName()
            );
            killerStats.addKill();
            
            int streak = killerStats.getCurrentKillstreak();
            
            // Message au tueur
            killer.sendMessage(Message.raw(""));
            killer.sendMessage(Message.raw("§a§l✓ +1 KILL"));
            killer.sendMessage(Message.raw("§7Total: §f" + killerStats.getKills() + " kills"));
            killer.sendMessage(Message.raw("§d🔥 Killstreak: §f" + streak));
            
            if (killerStats.getCurrentKillstreak() > killerStats.getBestKillstreak() - 1) {
                killer.sendMessage(Message.raw("§b⭐ Nouveau record personnel !"));
            }
            
            killer.sendMessage(Message.raw(""));
            
            // Message à la victime
            victim.sendMessage(Message.raw(""));
            victim.sendMessage(Message.raw("§c☠ Tué par §f" + killer.getDisplayName()));
            
            if (victimStreak > 0) {
                victim.sendMessage(Message.raw("§7Votre killstreak de §c" + victimStreak + " §7a été brisé !"));
            }
            
            victim.sendMessage(Message.raw("§7Stats: §f" + victimStats.getKills() + " kills §7- §f" + 
                victimStats.getDeaths() + " morts §7(§e" + 
                String.format("%.2f", victimStats.getKDRatio()) + " K/D§7)"));
            victim.sendMessage(Message.raw(""));
            
            // Annonce de killstreak
            announceKillstreak(killer.getDisplayName(), streak);
        }
        
        // Sauvegarder
        plugin.getStatsManager().saveStats();
    }
    
    private void announceKillstreak(String killerName, int streak) {
        String announcement = null;
        
        switch (streak) {
            case 3:
                announcement = "§e" + killerName + " §6est en série ! §e(3 kills)";
                break;
            case 5:
                announcement = "§6" + killerName + " §e§ldomine ! §6(5 kills)";
                break;
            case 10:
                announcement = "§c" + killerName + " §4§lest UNSTOPPABLE ! §c(10 kills)";
                break;
            case 15:
                announcement = "§4" + killerName + " §c§l§kII§r §4§lLÉGENDAIRE ! §c§l§kII§r §4(15 kills)";
                break;
            case 20:
                announcement = "§4§l" + killerName + " §c§lGODLIKE ! §4§l(20 kills)";
                break;
        }
        
        if (announcement != null) {
            plugin.getServer().broadcast(Message.raw(""));
            plugin.getServer().broadcast(Message.raw("§8§m━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"));
            plugin.getServer().broadcast(Message.raw("§d§l🔥 KILLSTREAK 🔥"));
            plugin.getServer().broadcast(Message.raw(announcement));
            plugin.getServer().broadcast(Message.raw("§8§m━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"));
            plugin.getServer().broadcast(Message.raw(""));
        }
    }
}
