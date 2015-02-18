package org.peidevs.waro.domain;

import java.util.Comparator;

public class BidComparator implements Comparator<Bid> {
    
    @Override
    public int compare(Bid a, Bid b) {
        Integer offerA = a.getOffer();
        Integer offerB = b.getOffer();
        int result = offerA.compareTo(offerB);
        
        return result;
    }
}