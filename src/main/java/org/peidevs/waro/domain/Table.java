package org.peidevs.waro.domain;

import org.peidevs.waro.table.Hand;

import java.util.List;
import java.util.stream.IntStream;

public class Table {
    private final List<Player> players; 
    private final Hand kitty;
    
    public Table(List<Player> players, Hand kitty) {
        this.players = players;
        this.kitty = kitty;
    }
    
    public List<Player> getPlayers() { return players; }
    public Hand getKitty() { return kitty; }
}
