package fr.betterpvp;

import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import fr.betterpvp.commands.*;
import fr.betterpvp.data.PlayerStatsManager;
import fr.betterpvp.listeners.*;
import javax.annotation.Nonnull;

public class BetterPvPPlugin extends JavaPlugin {
    
    private static BetterPvPPlugin instance;
    private PlayerStatsManager statsManager;
    
    public BetterPvPPlugin(@Nonnull JavaPluginInit init) {
        super(init);
    }
    
    @Override
    protected void setup() {
        instance = this;
        
        getLogger().info("╔════════════════════════════════╗");
        getLogger().info("║     BetterPvP v1.0.0          ║");
        getLogger().info("║  Chargement du plugin...      ║");
        getLogger().info("╚════════════════════════════════╝");
        
        // Initialiser le gestionnaire de stats
        statsManager = new PlayerStatsManager(this);
        
        // Enregistrer les commandes
        registerCommands();
        
        // Enregistrer les événements
        registerEvents();
        
        getLogger().info("✓ BetterPvP chargé avec succès !");
    }
    
    @Override
    protected void start() {
        getLogger().info("✓ BetterPvP démarré !");
        
        // Charger les stats depuis le disque
        statsManager.loadStats();
        
        // Sauvegarder les stats toutes les 5 minutes
        getTaskRegistry().scheduleRepeating(() -> {
            statsManager.saveStats();
            getLogger().info("Stats sauvegardées automatiquement");
        }, 20 * 60 * 5, 20 * 60 * 5); // 5 minutes en ticks
    }
    
    @Override
    protected void shutdown() {
        getLogger().info("Sauvegarde finale des stats...");
        statsManager.saveStats();
        getLogger().info("✓ BetterPvP arrêté proprement");
    }
    
    private void registerCommands() {
        var cmdRegistry = getCommandRegistry();
        
        cmdRegistry.registerCommand(new StatsCommand(this));
        cmdRegistry.registerCommand(new TopCommand(this));
        cmdRegistry.registerCommand(new ResetStatsCommand(this));
        cmdRegistry.registerCommand(new KillstreakCommand(this));
        cmdRegistry.registerCommand(new BetterPvPCommand(this));
        
        getLogger().info("✓ 5 commandes enregistrées");
    }
    
    private void registerEvents() {
        var eventRegistry = getEventRegistry();
        
        eventRegistry.register(PlayerDeathEvent.class, new CombatListener(this)::onPlayerDeath);
        eventRegistry.register(PlayerJoinEvent.class, new JoinListener(this)::onPlayerJoin);
        eventRegistry.register(PlayerQuitEvent.class, new QuitListener(this)::onPlayerQuit);
        
        getLogger().info("✓ 3 listeners enregistrés");
    }
    
    public static BetterPvPPlugin getInstance() {
        return instance;
    }
    
    public PlayerStatsManager getStatsManager() {
        return statsManager;
    }
}
