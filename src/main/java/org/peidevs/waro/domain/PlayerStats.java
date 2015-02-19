package org.peidevs.waro.domain;

public class PlayerStats {
    private final int total;
    private final int numGamesWon;
    private final int numRoundsWon;
    
    public PlayerStats() {
        this.total = 0;
        this.numGamesWon = 0;
        this.numRoundsWon = 0;
    }
    
    private PlayerStats(int total, int numGamesWon, int numRoundsWon) {
        this.total = total;
        this.numGamesWon = numGamesWon;
        this.numRoundsWon = numRoundsWon;
    }
    
    public int getTotal() { return total; }
    public int getNumRoundsWon() { return numRoundsWon; }
        
    public PlayerStats winsRound(int prizeCard) {
        PlayerStats newPlayerStats = new PlayerStats(this.total + prizeCard, this.numGamesWon, this.numRoundsWon + 1);
        return newPlayerStats;
    }
    
    public PlayerStats clear() {
        return new PlayerStats();
    }
}
