package org.peidevs.waro;

import org.peidevs.waro.function.*;
import org.peidevs.waro.player.*;
import org.peidevs.waro.strategy.*;

import java.util.*;

public class Main {
    // TODO: find a better way to configure players
    private static List<Player> buildPlayers(int maxCard) {
        List<Player> players = new ArrayList<>();
                        
        players.add(new Player("Beethoven", new NextCard(), maxCard));
        players.add(new Player("Chopin", new NextCard(), maxCard));
        players.add(new Player("Mozart", new NextCard(), maxCard));
        
        return players;
    }
    
    public static void main(String... args) {
        int numCards = 40;
        List<Player> players = buildPlayers(numCards);
        boolean isVerbose = true;
        
        Game game = new Game(numCards, isVerbose);
        List<Player> newPlayers = game.apply(players);
        
        System.out.println("Ready.");
    }
}