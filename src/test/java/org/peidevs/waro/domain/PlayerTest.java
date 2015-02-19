package org.peidevs.waro.domain;

import org.peidevs.waro.strategy.*;
import org.peidevs.waro.table.Hand;

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
        List<Integer> cards = IntStream.range(1,5).boxed().collect(toList());
        Hand hand = new Hand(cards);
        Player player = new Player("Randy", strategy, maxCard, hand);

        // test
        Bid bid = player.getBid(prizeCard);
        
        assertEquals(player, bid.getBidder());
        assertEquals(4, bid.getOffer());
        assertEquals(prizeCard, bid.getPrizeCard());
   }
}