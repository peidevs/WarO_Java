
package org.peidevs.waro;

import static org.junit.Assert.assertEquals;

import java.util.*;

import org.junit.Test;

public class PlayerTest {

    @Test
    public void playerCanMakeBid() {
        int num = 22;
        List<Integer> newCards = new ArrayList<Integer>();
        newCards.add(num);
        Hand h = new Hand(newCards);
        Player p = new Player(h);
        assertEquals(num, p.bid());
    }

}
