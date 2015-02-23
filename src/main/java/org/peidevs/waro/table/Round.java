package org.peidevs.waro.table;

import org.peidevs.waro.domain.*;

import java.util.*;
import java.util.stream.*;
import java.util.function.*;
import static java.util.stream.Collectors.toList;

public class Round implements UnaryOperator<List<Player>> {
    private final int prizeCard;
    
    public Round(int prizeCard) {
        this.prizeCard = prizeCard;
    }
    
    protected Round() {
        this.prizeCard = -1;
    }
    
    @Override
    public List<Player> apply(List<Player> players) {
        List<Player> nextRoundPlayers = new ArrayList<>();
        
        List<Bid> bids = getAllBids(players, prizeCard);
        
        Bid winningBid = findWinningBid(players, prizeCard);
        
        Player newWinner = winningBid.getBidder().wins(winningBid);
        nextRoundPlayers.add(newWinner);
        
        String winner = winningBid.getBidder().getName();
        List<Player> newLosers = bids.stream()
                                     .filter(b -> ! b.getBidder().getName().equals(winner))
                                     .map(b -> b.getBidder().loses(b))
                                     .collect(toList());

        nextRoundPlayers.addAll(newLosers);
        return nextRoundPlayers;
    }
    
    // --------- internal 
    
    protected List<Bid> getAllBids(List<Player> players, int prizeCard) {
        return players.stream().map(p -> p.getBid(prizeCard)).collect(toList());
    }
    
    protected Bid findWinningBid(List<Player> players, int prizeCard) {
        BidComparator comparator = new BidComparator();
        Bid winningBid = players.stream().map(p -> p.getBid(prizeCard)).max(comparator).get();
        return winningBid;
    }
}