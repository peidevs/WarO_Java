package org.peidevs.waro.table;

import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.toList;

public class Hand {
    private final List<Integer> cards;
    
    public Hand(List<Integer> cards) {
        this.cards = Collections.unmodifiableList(cards);
    }

    public IntStream cardsAsIntStream() {
        return cards.stream().mapToInt(i->i);        
    }    
    
    public Hand select(int card) {
        Hand newHand = null;
        
        if (cards.contains(card)) {
            List<Integer> newCards = cardsAsIntStream().filter(c -> c != card).boxed().collect(toList());
            newHand = new Hand(newCards);
        } else {
            throw new IllegalArgumentException("illegal card : " + card);
        }
        
        return newHand;
    }
}