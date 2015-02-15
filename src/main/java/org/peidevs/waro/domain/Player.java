package org.peidevs.waro.domain;

import java.util.List;

import org.peidevs.waro.strategy.Strategy;

public class Player {
    private final String name;
    private final Strategy strategy;
    private final PlayerStats playerStats = new PlayerStats();
    private final int maxCard; 
    private List<Integer> hand;
    
    public Player(String name, Strategy strategy, int maxCard) {
        this.name = name;
        this.strategy = strategy;
        this.maxCard = maxCard;
    }

    public void setHand(List<Integer> hand) {
        this.hand = hand;
    }

    public int getNumCardsInHand() {
        return hand.size();
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
