package org.peidevs.waro.table;

import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.groupingBy;

import org.peidevs.waro.domain.*;

public class Dealer {
    
    public void play(Table table) {
        List<Player> players = table.getPlayers();
        IntStream kitty = table.getKitty();
        kitty.forEach(prizeCard -> playRound(prizeCard, players));
    }
    
    public Table deal(int numCards, List<Player> players) {
        Map<Integer, List<Integer>> map = deal(numCards, players.size());

        final int KITTY_INDEX = 0;
        List<Integer> kitty = map.get(KITTY_INDEX);

        IntStream.range(0,players.size()).forEach(i -> players.get(i).setHand(map.get(i+1)));

        Table table = new Table(players, kitty.stream().mapToInt(i->i));
        return table;
    }
    
    // ------- internal 
    
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
    
    protected Map<Integer, List<Integer>> deal(int numCards, int numPlayers) {
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