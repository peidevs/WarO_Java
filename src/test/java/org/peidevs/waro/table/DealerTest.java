package org.peidevs.waro.table;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.*;

import java.util.stream.*;
import static java.util.stream.Collectors.toList;

public class DealerTest {

    @Test
    public void testDeal_Basic() {
        Dealer dealer = new Dealer();
        int numCards = 40;
        int numPlayers = 4;

        // test
        Map<Integer, List<Integer>> map = dealer.deal(numCards, numPlayers);
        
        assertEquals(5, map.keySet().size());
        assertEquals(8, map.get(0).size());
        assertEquals(8, map.get(1).size());
        assertEquals(8, map.get(2).size());
        assertEquals(8, map.get(3).size());
        assertEquals(8, map.get(4).size());
    }

    @Test(expected=IllegalArgumentException.class)
    public void testDeal_Uneven() {
        Dealer dealer = new Dealer();
        int numCards = 42;
        int numPlayers = 4;

        // test
        Map<Integer, List<Integer>> map = dealer.deal(numCards, numPlayers);        
    }

    @Test
    public void testBuildShuffledDeck() {
        Dealer dealer = new Dealer();
        int numCards = 4;
        
        // test
        IntStream resultStream = dealer.buildShuffledDeck(numCards);
        
        List<Integer> result = resultStream.boxed().collect(toList());
        assertEquals(4, result.size());
        assertTrue(result.contains(1));
        assertTrue(result.contains(2));
        assertTrue(result.contains(3));
        assertTrue(result.contains(4));
    }

}
