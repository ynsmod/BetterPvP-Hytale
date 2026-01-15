package fr.betterpvp.data;

import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class PlayerStatsManager {
    private final JavaPlugin plugin;
    private final Map<UUID, PlayerStats> statsMap;
    private final Path dataFile;
    
    public PlayerStatsManager(JavaPlugin plugin) {
        this.plugin = plugin;
        this.statsMap = new ConcurrentHashMap<>();
        this.dataFile = plugin.getDataFolder().resolve("player_stats.dat");
        
        try {
            Files.createDirectories(plugin.getDataFolder());
        } catch (IOException e) {
            plugin.getLogger().error("Erreur création dossier data", e);
        }
    }
    
    public PlayerStats getStats(UUID playerId) {
        return statsMap.get(playerId);
    }
    
    public PlayerStats getOrCreateStats(UUID playerId, String playerName) {
        return statsMap.computeIfAbsent(playerId, id -> new PlayerStats(id, playerName));
    }
    
    public void removeStats(UUID playerId) {
        statsMap.remove(playerId);
    }
    
    public List<PlayerStats> getTopPlayersByKills(int limit) {
        return statsMap.values().stream()
            .sorted((a, b) -> Integer.compare(b.getKills(), a.getKills()))
            .limit(limit)
            .collect(Collectors.toList());
    }
    
    public List<PlayerStats> getTopPlayersByKD(int limit) {
        return statsMap.values().stream()
            .filter(stats -> stats.getDeaths() > 0)
            .sorted((a, b) -> Double.compare(b.getKDRatio(), a.getKDRatio()))
            .limit(limit)
            .collect(Collectors.toList());
    }
    
    public List<PlayerStats> getTopPlayersByKillstreak(int limit) {
        return statsMap.values().stream()
            .sorted((a, b) -> Integer.compare(b.getBestKillstreak(), a.getBestKillstreak()))
            .limit(limit)
            .collect(Collectors.toList());
    }
    
    public void saveStats() {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(dataFile.toFile()))) {
            
            oos.writeInt(statsMap.size());
            
            for (PlayerStats stats : statsMap.values()) {
                oos.writeObject(stats.getPlayerId());
                oos.writeUTF(stats.getPlayerName());
                oos.writeInt(stats.getKills());
                oos.writeInt(stats.getDeaths());
                oos.writeInt(stats.getCurrentKillstreak());
                oos.writeInt(stats.getBestKillstreak());
                oos.writeLong(stats.getLastKillTime());
            }
            
            plugin.getLogger().info("Stats sauvegardées: " + statsMap.size() + " joueurs");
            
        } catch (IOException e) {
            plugin.getLogger().error("Erreur sauvegarde stats", e);
        }
    }
    
    public void loadStats() {
        if (!Files.exists(dataFile)) {
            plugin.getLogger().info("Aucune donnée à charger (premier démarrage)");
            return;
        }
        
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(dataFile.toFile()))) {
            
            statsMap.clear();
            int count = ois.readInt();
            
            for (int i = 0; i < count; i++) {
                UUID playerId = (UUID) ois.readObject();
                String playerName = ois.readUTF();
                int kills = ois.readInt();
                int deaths = ois.readInt();
                int currentKillstreak = ois.readInt();
                int bestKillstreak = ois.readInt();
                long lastKillTime = ois.readLong();
                
                PlayerStats stats = new PlayerStats(playerId, playerName, kills, deaths,
                    currentKillstreak, bestKillstreak, lastKillTime);
                statsMap.put(playerId, stats);
            }
            
            plugin.getLogger().info("Stats chargées: " + count + " joueurs");
            
        } catch (IOException | ClassNotFoundException e) {
            plugin.getLogger().error("Erreur chargement stats", e);
        }
    }
    
    public int getTotalPlayers() {
        return statsMap.size();
    }
    
    public int getTotalKills() {
        return statsMap.values().stream()
            .mapToInt(PlayerStats::getKills)
            .sum();
    }
}
