package org.peidevs.waro.function;

import java.util.List;
import java.util.stream.*;
import java.util.function.*;
import static java.util.stream.Collectors.toList;

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

        List<Player> newPlayers = play(kitty, readyPlayers);

        List<Player> newPlayers2 = determineWinner(newPlayers);

        logger.log("FINAL", newPlayers2);
                
        return newPlayers2;
    }
        
    protected List<Player> play(Hand kitty, List<Player> players) {
        List<Player> newPlayers = players;

        List<Integer> prizeCards = kitty.cardsAsIntStream().boxed().collect(toList());
        
        // TODO: find a better way, possibly 'zip' function?
        for (int prizeCard : prizeCards) {
            newPlayers = new Round(prizeCard).apply(newPlayers);
            logger.log("ROUND", newPlayers, prizeCard);
        }
        
        return newPlayers;
    }
        
    // ---- internal 
    
    protected List<Player> determineWinner(List<Player> players) {
        PlayerComparator comparator = new PlayerComparator(PlayerStats::getTotal);
        Player winner = players.stream().max(comparator).get().winsGame();
        String winnerName = winner.getName();
        List<Player> newPlayers = players.stream()
                                         .filter(p->!p.getName().equals(winnerName))
                                         .collect(toList());
        newPlayers.add(winner);
        return newPlayers;
    }
    
}
