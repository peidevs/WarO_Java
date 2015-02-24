package org.peidevs.waro.strategy;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.*;

import java.util.stream.*;
import static java.util.stream.Collectors.toList;

public class NextCardTest {

    @Test
    public void testSelectCard() {
        Strategy strategy = new NextCard();
        int maxCard = 50;
        int prizeCard = 10;
        int handSize = 20;
        IntStream hand = IntStream.range(1,handSize+1).boxed().mapToInt(i -> i);
        
        // test 
        int result = strategy.selectCard(prizeCard, hand, maxCard);
      
        assertEquals(1, result);
    }

}
