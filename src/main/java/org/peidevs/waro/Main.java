package org.peidevs.waro;

import org.peidevs.waro.function.Tourney;
import org.peidevs.waro.player.Player;
import org.peidevs.waro.player.PlayerComparator;
import org.peidevs.waro.player.PlayerStats;
import org.peidevs.waro.config.ConfigService;

import java.util.List;

public class Main {
    
    public static void main(String... args) {
        ConfigService configService = new ConfigService();
        int numCards = configService.getNumCards();
        int numGames = configService.getNumGames();
        boolean isVerbose = configService.isVerbose();        
        List<Player> players = configService.getPlayers();
        
        Tourney tourney = new Tourney(numCards, numGames, isVerbose);
        List<Player> newPlayers = tourney.apply(players);

        PlayerComparator comparator = new PlayerComparator(PlayerStats::getNumGamesWon);
        Player winner = newPlayers.stream().max(comparator).get();
        
        // TODO: report on tie games
        System.out.println("------------------");
        System.out.println("WINNER : " + winner.getName());
    }
}