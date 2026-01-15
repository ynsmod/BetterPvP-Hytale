package fr.betterpvp.data;

import java.util.UUID;

public class PlayerStats {
    private final UUID playerId;
    private String playerName;
    private int kills;
    private int deaths;
    private int currentKillstreak;
    private int bestKillstreak;
    private long lastKillTime;
    
    public PlayerStats(UUID playerId, String playerName) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.kills = 0;
        this.deaths = 0;
        this.currentKillstreak = 0;
        this.bestKillstreak = 0;
        this.lastKillTime = 0;
    }
    
    public PlayerStats(UUID playerId, String playerName, int kills, int deaths, 
                      int currentKillstreak, int bestKillstreak, long lastKillTime) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.kills = kills;
        this.deaths = deaths;
        this.currentKillstreak = currentKillstreak;
        this.bestKillstreak = bestKillstreak;
        this.lastKillTime = lastKillTime;
    }
    
    public void addKill() {
        kills++;
        currentKillstreak++;
        lastKillTime = System.currentTimeMillis();
        
        if (currentKillstreak > bestKillstreak) {
            bestKillstreak = currentKillstreak;
        }
    }
    
    public void addDeath() {
        deaths++;
        currentKillstreak = 0;
    }
    
    public double getKDRatio() {
        if (deaths == 0) {
            return kills;
        }
        return (double) kills / deaths;
    }
    
    public void reset() {
        kills = 0;
        deaths = 0;
        currentKillstreak = 0;
        bestKillstreak = 0;
        lastKillTime = 0;
    }
    
    // Getters
    public UUID getPlayerId() { return playerId; }
    public String getPlayerName() { return playerName; }
    public void setPlayerName(String name) { this.playerName = name; }
    public int getKills() { return kills; }
    public int getDeaths() { return deaths; }
    public int getCurrentKillstreak() { return currentKillstreak; }
    public int getBestKillstreak() { return bestKillstreak; }
    public long getLastKillTime() { return lastKillTime; }
}
