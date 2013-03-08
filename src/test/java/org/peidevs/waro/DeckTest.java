
package org.peidevs.waro;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class DeckTest {

    @Test
    public void getNextOnceTest() {
        Deck d = new Deck();
        int first = d.getNext();
        assertEquals(1, first);
    }

    @Test
    public void getNextAgainTest() {
        Deck d = new Deck();
        int num = d.getNext();
        num = d.getNext();
        assertEquals(2, num);
    }

    @Test
    public void shuffleTest() {
        //multiple calls to make sure pseudo random doesn't eff it up.
        Deck d = new Deck();
        d.shuffle();
        int first = d.getNext();
        assertNotSame(1, first);
        first = d.getNext();
        assertNotSame(2, first);
        first = d.getNext();
        assertNotSame(3, first);
        first = d.getNext();
        assertNotSame(4, first);
    }

}
