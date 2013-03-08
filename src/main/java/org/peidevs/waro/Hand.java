
package org.peidevs.waro;

import java.util.List;

public class Hand {

    List<Integer> cards;

    public Hand() {
    }
  
    public Hand(List<Integer> cards) {
        this.cards = cards;
    }

    public List<Integer> getCards() {
        return cards;
    }

    public void addCard(int card) {
        cards.add(card);
    }

}
