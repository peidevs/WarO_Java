package org.peidevs.waro.domain;

import org.peidevs.waro.strategy.Strategy;

public class Player {
    private final String name;
    private final Strategy strategy;
    private final PlayerStats playerStats = new PlayerStats();
    private final int maxCard; 

    public Player(String name, Strategy strategy, int maxCard) {
        this.name = name;
        this.strategy = strategy;
        this.maxCard = maxCard;
    }

    public String toString() {
        return name;
    }
    
    /*        
    Bid getBid(int prizeCard) {
        def unmodifiableHand = Collections.unmodifiableList(hand)
        def offer = strategy.selectCard(prizeCard, unmodifiableHand, maxCard)

        def bid = new Bid(offer, this)        
        assert hand.contains(bid.offer)

        hand.remove(bid.offer as Object)
        
        return bid
    }
    
    void clear() {
        hand = []
        playerStats.clear()
    }
    */
}
