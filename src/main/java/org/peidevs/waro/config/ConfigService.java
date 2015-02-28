package org.peidevs.waro.config;

import org.peidevs.waro.player.Player;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class ConfigService {
    private final AnnotationConfigApplicationContext context;
    
    public ConfigService() {
        context = new AnnotationConfigApplicationContext(Config.class);        
    }
    
    public int getNumCards() { return context.getBean("numCards", Integer.class); }    
    public int getNumGames() { return context.getBean("numGames", Integer.class); }    
    public boolean isVerbose() { return context.getBean("isVerbose", Boolean.class); }
    
    public List<Player> getPlayers() {
        return context.getBean("players", List.class);
    }    
}