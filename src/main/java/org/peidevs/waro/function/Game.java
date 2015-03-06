package org.peidevs.waro.function;

import java.util.*;
import java.util.stream.*;
import java.util.function.*;
import static java.util.stream.Collectors.toList;
import static java.util.Comparator.comparing;

import org.peidevs.waro.player.*;
import org.peidevs.waro.table.*;
import org.peidevs.waro.util.*;

public class Game implements UnaryOperator<List<Player>> {    
    private final Log logger;
    private final int numCards;
    
    public Game(int numCards, boolean isVerbose) {
        this.numCards = numCards;
        this.logger = new Log(isVerbose);
    }
        
    @Override
    public List<Player> apply(List<Player> players) {
        Dealer dealer = new Dealer();
        Table table = dealer.deal(numCards, players);

        Hand kitty = table.getKitty();
        List<Player> readyPlayers = table.getPlayers();
       
        logger.log("INIT", kitty, readyPlayers);

        Stream<Player> newPlayers = play(kitty, readyPlayers.stream());

        List<Player> newPlayers2 = determineWinner(newPlayers.collect(toList()));

        logger.log("FINAL", newPlayers2);
                
        return newPlayers2;
    }
        
    protected Stream<Player> play(Hand kitty, Stream<Player> players) {
        Stack<Stream<Player>> results = new Stack<>();
        results.push(players);
        
        kitty.cardsAsIntStream()
             .boxed()
             .forEach( prizeCard -> { Stream<Player> lastPlayers = results.pop();
                                      results.push(new Round(prizeCard).apply(lastPlayers)); 
                                    });
        
        return results.pop();
    }
        
    // ---- internal 
    
    protected List<Player> determineWinner(List<Player> players) {
        Player winner = players.stream()
                               .max( comparing(Player::getTotal).reversed() )
                               .get()
                               .winsGame();
                               
        String winnerName = winner.getName();
        List<Player> newPlayers = players.stream()
                                         .filter(p->!p.getName().equals(winnerName))
                                         .collect(toList());
        newPlayers.add(winner);
        return newPlayers;
    }
    
}
