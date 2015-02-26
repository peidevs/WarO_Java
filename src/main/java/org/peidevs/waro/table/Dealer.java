package org.peidevs.waro.table;

import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.groupingBy;

import org.peidevs.waro.domain.*;
import org.peidevs.waro.player.*;

public class Dealer {

    public Table deal(int numCards, List<Player> players) {
        Table table = null;

        List<Hand> hands = deal(numCards, players.size()).collect(toList());
        Hand kitty = hands.remove(0);
        List<Player> newPlayers = new ArrayList<>();
        
        for (int i = 0; i < hands.size(); i++) {
            Player player = players.get(i);
            Hand hand = hands.get(i);
            newPlayers.add( player.reset(hand) );
        }
        
        table = new Table(newPlayers, kitty);
        
        return table;
    }
    
    // ------- internal 
    
    /*
    protected Player playRound(int prizeCard, List<Player> players) {
        Bid winningBid = findWinningBid(prizeCard, players);
        Player winner = winningBid.getBidder();
        return winner;
    }

    protected Bid findWinningBid(int prizeCard, List<Player> players) {
        BidComparator comparator = new BidComparator();
        Bid winningBid = players.stream().map(p -> p.getBid(prizeCard)).max(comparator).get();
        
        return winningBid;
    }
    */
    
    protected Stream<Hand> deal(int numCards, int numPlayers) {
        int numGroups = numPlayers + 1; // include kitty 
        assertEvenNumberOfCards(numCards, numGroups);
        
        Map<Integer, List<Integer>> map = buildShuffledDeck(numCards)
                                            .stream()
                                            .mapToInt(i->i)
                                            .boxed()
                                            .collect(groupingBy(i -> (i % numGroups)));
                
        Stream<Hand> hands = map.keySet()
                              .stream()
                              .map(k -> new Hand(map.get(k)));
        
        return hands;
    }
    
    protected List<Integer> buildShuffledDeck(int numCards) {
        List<Integer> cards = IntStream.range(1,numCards+1).boxed().collect(toList());
        Collections.shuffle(cards);
        return cards;
    }
        
    protected void assertEvenNumberOfCards(int numCards, int numGroups) {
        if ((numCards % numGroups) != 0) {
            throw new IllegalArgumentException("uneven # of cards");
        }
    }    
}
