package org.peidevs.waro.player;

import java.util.Comparator;
import java.util.function.Function;

public class PlayerComparator implements Comparator<Player> {
    
    // TODO: it seems wrong that we compare Players via methods on PlayerStats
    private final Function<PlayerStats,Integer> compareFunction;
    
    public PlayerComparator(Function<PlayerStats,Integer> compareFunction) {
        this.compareFunction = compareFunction;
    }
    
    private Integer getValue(Player player) {
        return compareFunction.apply(player.getPlayerStats());
    }
    
    @Override
    public int compare(Player a, Player b) {
        Integer valueA = getValue(a);
        Integer valueB = getValue(b);
        int result = valueA.compareTo(valueB);
        
        return result;
    }
}
