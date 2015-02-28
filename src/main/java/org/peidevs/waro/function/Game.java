package org.peidevs.waro.function;

import java.util.List;
import java.util.stream.*;
import java.util.function.*;
import static java.util.stream.Collectors.toList;

import org.peidevs.waro.domain.*;
import org.peidevs.waro.player.*;
import org.peidevs.waro.table.*;

public class Game implements UnaryOperator<List<Player>> {    
    private boolean verbose = true;
    private final int numCards;
    
    public Game(int numCards) {
        this.numCards = numCards;
    }
        
    @Override
    public List<Player> apply(List<Player> players) {
        Dealer dealer = new Dealer();
        Table table = dealer.deal(numCards, players);

        Hand kitty = table.getKitty();
        List<Player> readyPlayers = table.getPlayers();
       
        log(kitty, readyPlayers);

        List<Player> newPlayers = play(kitty, readyPlayers);

        List<Player> newPlayers2 = determineWinner(newPlayers);
                
        return newPlayers2;
    }
    
    protected void log(Hand kitty, List<Player> players) {
        if (verbose) {
            System.out.println("-----------------------------------");
            System.out.println("TRACER kitty : " + kitty);
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
            log(kitty, newPlayers);
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
