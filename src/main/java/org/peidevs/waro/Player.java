
package org.peidevs.waro;

public class Player {

    private Hand h;

    public Player() {
      h = new Hand();
    }

    public void addCard(int card){
      h.addCard(card);
    }
  
    public void setHand(Hand h) {
        this.h = h;
    }

    public Player(Hand h) {
        this.h = h;
    }

    public int bid() {
        return h.getCards().get(0);
    }
}
