package org.peidevs.waro.config;

import org.peidevs.waro.player.*;
import org.peidevs.waro.strategy.*;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;

@Configuration
public class Config {
    private static final int NUM_CARDS = 12;
    private static final int MAX_CARD = NUM_CARDS;
    private static final int NUM_GAMES = 3;
    private static final boolean IS_VERBOSE = true;
    
    @Bean public int numCards() { return NUM_CARDS; }    
    @Bean public int numGames() { return NUM_GAMES; }    
    @Bean public boolean isVerbose() { return IS_VERBOSE; }
    
    @Bean
    public List<Player> players() {
        List<Player> players = new ArrayList<>();
                        
        players.add(new Player("Beethoven", new NextCard(), MAX_CARD));
        players.add(new Player("Chopin", new NextCard(), MAX_CARD));
        players.add(new Player("Mozart", new NextCard(), MAX_CARD));
        
        return players;
    }   
}