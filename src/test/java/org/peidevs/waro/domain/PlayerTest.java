package org.peidevs.waro.domain;

import org.peidevs.waro.strategy.*;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.*;

import java.util.stream.*;
import static java.util.stream.Collectors.toList;

public class PlayerTest {

    @Test
    public void testGetBid_Basic() {
        Strategy strategy = new MaxCard();
        int maxCard = 40;
        int prizeCard = 10;
        Player player = new Player("Randy", strategy, maxCard);
        List<Integer> hand = IntStream.range(1,5).boxed().collect(toList());
        player.setHand(hand);

        // test
        Bid bid = player.getBid(prizeCard);
        
        assertEquals(player, bid.getBidder());
        assertEquals(4, bid.getOffer());
   }
}