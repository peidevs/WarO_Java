package org.peidevs.waro.player;

import java.util.Comparator;

public class PlayerComparator implements Comparator<Player> {
    
    @Override
    public int compare(Player a, Player b) {
        Integer totalA = a.getPlayerStats().getTotal();
        Integer totalB = b.getPlayerStats().getTotal();
        int result = totalA.compareTo(totalB);
        
        return result;
    }
}
