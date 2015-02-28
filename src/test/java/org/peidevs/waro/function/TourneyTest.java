package org.peidevs.waro.function;

import org.peidevs.waro.player.*;
import org.peidevs.waro.strategy.*;
import org.peidevs.waro.table.*;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.*;

import java.util.stream.*;
import static java.util.stream.Collectors.toList;

public class TourneyTest {
    @Test
    public void testApply_Basic() {
        int numGames = 3;
        int numCards = 12;
        int maxCard = numCards;
        Strategy strategy = new NextCard();
        List<Player> players = new ArrayList<>();
        
        Player p1 = new Player("p1", strategy, maxCard, new Hand());
        Player p2 = new Player("p2", strategy, maxCard, new Hand());
        Player p3 = new Player("p3", strategy, maxCard, new Hand());
        
        players.add(p1);
        players.add(p2);
        players.add(p3);
                
        // test
        List<Player> newPlayers = new Tourney(numCards, numGames, false).apply(players);
        
        assertEquals(3, newPlayers.size());
        assertEquals(0, newPlayers.get(0).getNumCardsInHand());
        assertEquals(0, newPlayers.get(1).getNumCardsInHand());
        assertEquals(0, newPlayers.get(2).getNumCardsInHand());
        /*
        assertEquals(1, newPlayers.stream().filter(p -> p.getPlayerStats().getNumGamesWon() == 1).count());
        assertEquals(2, newPlayers.stream().filter(p -> p.getPlayerStats().getNumGamesWon() == 0).count());
        assertEquals(3, newPlayers.stream().filter(p -> p.getPlayerStats().getNumRoundsWon() == 1).count());
        assertEquals(1, newPlayers.stream().filter(p -> p.getPlayerStats().getTotal() == 10).count());
        assertEquals(1, newPlayers.stream().filter(p -> p.getPlayerStats().getTotal() == 11).count());
        assertEquals(1, newPlayers.stream().filter(p -> p.getPlayerStats().getTotal() == 12).count());
        */
    }
}
