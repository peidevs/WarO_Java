package org.peidevs.waro.config;

import org.peidevs.waro.player.*;
import org.peidevs.waro.strategy.*;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Configuration;

import java.util.*;

@Configuration
public class Config {
    // -----------------------------------                
    // configure these as desired
    private static final int NUM_CARDS = 12;
    private static final int MAX_CARD = NUM_CARDS;
    private static final int NUM_GAMES = 50;
    private static final boolean IS_VERBOSE = false;
        
    @Bean
    @Lazy
    public List<Player> players() {
        List<Player> players = new ArrayList<>();
                        
        // -----------------------------------                
        // configure players as desired
        players.add(new Player("Beethoven", new MinCard(), MAX_CARD));
        players.add(new Player("Chopin", new NextCard(), MAX_CARD));
        players.add(new Player("Mozart", new MaxCard(), MAX_CARD));
        
        return players;
    }

    @Bean public int numCards() { return NUM_CARDS; }    
    @Bean public int numGames() { return NUM_GAMES; }    
    @Bean public boolean isVerbose() { return IS_VERBOSE; }       
    
    protected static final String BEAN_NUM_CARDS = "numCards";
    protected static final String BEAN_NUM_GAMES = "numGames";
    protected static final String BEAN_IS_VERBOSE = "isVerbose";
    protected static final String BEAN_PLAYERS = "players";    
}
