package org.peidevs.waro.domain;

import java.util.List;
import java.util.stream.IntStream;

public class Table {
    private final List<Player> players; 
    private final IntStream kitty;
    
    public Table(List<Player> players, IntStream kitty) {
        this.players = players;
        this.kitty = kitty;
    }
    
    public List<Player> getPlayers() { return players; }
    public IntStream getKitty() { return kitty; }
}
