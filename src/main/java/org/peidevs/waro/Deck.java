
package org.peidevs.waro;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Deck {

    private int size = 60;
    private List<Integer> cards;
    private Iterator<Integer> iter;

    public Deck() {
        cards = new ArrayList<Integer>();
        for (int i = 1; i <= size; i++) {
            cards.add(new Integer(i));
        }
        iter = cards.iterator();
    }

    public int getNext() {
        int n = iter.next();
        // cards.remove(Integer(n));
        return n;
    }

    public void shuffle() {
        List<Integer> newCards = new ArrayList<Integer>();

        while (!cards.isEmpty()) {
            int x = (int) (System.currentTimeMillis() % cards.size());
            System.out.println(x);
            newCards.add(cards.get(x));
            cards.remove(x);
        }
        cards.addAll(newCards);
        iter = cards.iterator();
    }
}
