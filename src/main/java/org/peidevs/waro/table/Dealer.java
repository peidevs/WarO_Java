package org.peidevs.waro.table;

import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.groupingBy;

public class Dealer {
    public Map<Integer, List<Integer>> deal(int numCards, int numPlayers) {
        int numGroups = numPlayers + 1; // include kitty 
        assertEvenNumberOfCards(numCards, numGroups);
        
        Map<Integer, List<Integer>> map = buildShuffledDeck(numCards)
                                            .boxed()
                                            .collect(groupingBy(i -> (i % numGroups)));
                
        return map;
    }
    
    protected IntStream buildShuffledDeck(int numCards) {
        List<Integer> cards = buildDeck(numCards).collect(toList());
        Collections.shuffle(cards);
        IntStream shuffledDeck = cards.stream().mapToInt(i -> i);

        return shuffledDeck;
    }
    
    protected Stream<Integer> buildDeck(int numCards) {
        Stream<Integer> cards = IntStream.range(1,numCards+1).boxed();
        return cards;
    }
    
    protected void assertEvenNumberOfCards(int numCards, int numGroups) {
        if ((numCards % numGroups) != 0) {
            throw new IllegalArgumentException("uneven # of cards");
        }
    }    
}