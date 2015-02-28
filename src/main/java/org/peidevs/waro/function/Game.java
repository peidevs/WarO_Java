package org.peidevs.waro.function;

import java.util.List;
import java.util.stream.*;
import java.util.function.*;
import static java.util.stream.Collectors.toList;

import org.peidevs.waro.player.*;
import org.peidevs.waro.table.*;

public class Game implements UnaryOperator<List<Player>> {    
    private final boolean isVerbose;
    private final int numCards;
    
    public Game(int numCards, boolean isVerbose) {
        this.numCards = numCards;
        this.isVerbose = isVerbose;
    }
        
    @Override
    public List<Player> apply(List<Player> players) {
        Dealer dealer = new Dealer();
        Table table = dealer.deal(numCards, players);

        Hand kitty = table.getKitty();
        List<Player> readyPlayers = table.getPlayers();
       
        log("INIT", kitty, readyPlayers);

        List<Player> newPlayers = play(kitty, readyPlayers);

        List<Player> newPlayers2 = determineWinner(newPlayers);

        log("FINAL", newPlayers2, 0);
                
        return newPlayers2;
    }
    
    protected void log(String msg, Hand kitty, List<Player> players) {
        if (isVerbose) {
            System.out.println("---------------------------------- " + msg);
            System.out.println("TRACER kitty : " + kitty);
            for (Player p : players) {
                System.out.println("TRACER p - " + p);                
            }
        }        
    }

    protected void log(String msg, List<Player> players, int prizeCard) {
        if (isVerbose) {
            System.out.println("----------------------------------- " + msg);
            if (prizeCard != 0) {
                System.out.println("TRACER prize - " + prizeCard);                                
            }
            for (Player p : players) {
                System.out.println("TRACER p - " + p);                
            }
        }        
    }
    
    protected List<Player> play(Hand kitty, List<Player> players) {
        List<Player> newPlayers = players;
        
        List<Integer> prizeCards = kitty.cardsAsIntStream().boxed().collect(toList());
        
        for (int prizeCard : prizeCards) {
            newPlayers = new Round(prizeCard).apply(newPlayers);
            log("ROUND", newPlayers, prizeCard);
        }
        
        return newPlayers;
    }
        
    // ---- internal 
    
    protected List<Player> determineWinner(List<Player> players) {
        PlayerComparator comparator = new PlayerComparator();
        Player winner = players.stream().max(comparator).get().winsGame();
        String winnerName = winner.getName();
        List<Player> newPlayers = players.stream()
                                         .filter(p->!p.getName().equals(winnerName))
                                         .collect(toList());
        newPlayers.add(winner);
        return newPlayers;
        /*
        def kitty = table.kitty
        def players = table.players
        
        table.assertTotals()
        
        def winner = players.max { p -> p.playerStats.total }
        def max = winner.playerStats.total
        
        if (verbose) {
            players.each { p ->
                def stats = p.playerStats
                println "$p.name won $stats.numRoundsWon rounds with $stats.total"
            }                            
        }
        
        winner.playerStats.numGamesWon++
        println "\nGame summary:"
        println "overall WINNER is: $winner.name "                
        */
    }
    
}
