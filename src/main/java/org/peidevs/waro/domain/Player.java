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

    // TODO: fix mutable state
    public void setHand(List<Integer> hand) {
        this.hand = hand;
    }

    public int getNumCardsInHand() {
        return hand.size();
    }
    
    public String toString() {
        return name;
    }

    public Bid getBid(int prizeCard) {
        int offer = strategy.selectCard(prizeCard, hand.stream().mapToInt(i->i), maxCard);
        // TODO: ensure that offer is contained in hand ! (no cheaters)
        
        Bid bid = new Bid(offer, this);        

        // TODO: fix mutable state
        hand.remove((Object) bid.getOffer());
        
        return bid;
    }
    
    /*        
    
    void clear() {
        hand = []
        playerStats.clear()
    }
    */
}
