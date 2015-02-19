package org.peidevs.waro.domain;

import java.util.List;

import org.peidevs.waro.strategy.Strategy;
import org.peidevs.waro.table.Hand;

public class Player {
    private final String name;
    private final Strategy strategy;
    private final PlayerStats playerStats;
    private final int maxCard; 
    private final Hand hand;
    
    public Player(String name, Strategy strategy, int maxCard, Hand hand) {
        this.name = name;
        this.strategy = strategy;
        this.maxCard = maxCard;
        this.hand = hand;
        this.playerStats = new PlayerStats();
    }

    private Player(String name, Strategy strategy, int maxCard, Hand hand, PlayerStats playerStats) {
        this.name = name;
        this.strategy = strategy;
        this.maxCard = maxCard;
        this.hand = hand;
        this.playerStats = playerStats;
    }

    public PlayerStats getPlayerStats() {
        return playerStats;
    }
    
    public String getName() {
        return name;
    }    
    
    public Player wins(Bid bid) {
        Hand newHand = hand.select(bid.getOffer());
        PlayerStats newPlayerStats = playerStats.winsRound(bid.getPrizeCard());
        Player newPlayer = new Player(name, strategy, maxCard, newHand, newPlayerStats);
        return newPlayer;
    }

    public Player loses(Bid bid) {
        Hand newHand = hand.select(bid.getOffer());
        Player newPlayer = new Player(name, strategy, maxCard, newHand);
        return newPlayer;
    }
    
    public Bid getBid(int prizeCard) {
        int offer = strategy.selectCard(prizeCard, hand.cardsAsIntStream(), maxCard);
        // TODO: ensure that offer is contained in hand ! (no cheaters)
        
        Bid bid = new Bid(prizeCard, offer, this);        
        
        return bid;
    }

    public long getNumCardsInHand() {
        return hand.cardsAsIntStream().boxed().count();
    }

    // --------- internal
    
    /*        
    
    void clear() {
        hand = []
        playerStats.clear()
    }
    */
}
