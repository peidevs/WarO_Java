
package org.peidevs.waro; 

import java.util.List;

public class Dealer {

    List<Player> players;
    Deck theDeck;

    public Dealer(List<Player> players) {
        this.players = players;
        theDeck = new Deck();
    }
  
    public void deal() {
        theDeck.shuffle();
        for(Player p: players){
          // p.addCard(theDeck.);
        }
    }
}
