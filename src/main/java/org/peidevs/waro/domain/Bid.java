package org.peidevs.waro.domain;

public class Bid {
    private final int offer;
    private final Player bidder;
    
    public Bid (int offer, Player bidder) {
        this.offer = offer;
        this.bidder = bidder;
    }
    
    public int getOffer() { return offer; }
    public Player getBidder() { return bidder; }
}