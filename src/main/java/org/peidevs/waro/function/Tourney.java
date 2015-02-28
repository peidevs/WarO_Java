package org.peidevs.waro.function;

import java.util.List;
import java.util.stream.*;
import java.util.function.*;
import static java.util.stream.Collectors.toList;

import org.peidevs.waro.player.*;
import org.peidevs.waro.table.*;
import org.peidevs.waro.util.*;

public class Tourney implements UnaryOperator<List<Player>> {    
    private final Log logger;
    private final boolean isVerbose;
    private final int numCards;
    private final int numGames;
    
    public Tourney(int numCards, int numGames, boolean isVerbose) {
        this.numCards = numCards;
        this.numGames = numGames;
        this.logger = new Log(isVerbose);
        this.isVerbose = isVerbose;
    }
        
    @Override
    public List<Player> apply(List<Player> players) {
        List<Player> newPlayers = players;
        
        // TODO: this is hideous
        for (int i = 1; i <= numGames; i++) {
            newPlayers = new Game(numCards, isVerbose).apply(newPlayers);
        }
        
        logger.log("END TOURNEY", newPlayers);
                
        return newPlayers;
    }
    
}
