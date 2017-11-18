package myfirstgame.pingpongscorer;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by James on 18/11/2017.
 */

public class TestPlayer {

    Player player;

    @Before
    public void setUp() throws Exception {
        player = new Player("James", 13, 5, 110, 55);
    }

    @Test
    public void canGetWinPercentage() throws Exception {
        assertEquals(72, player.progressByPercentage());
    }

    @Test
    public void canGetScoreDifferent() throws Exception {
        assertEquals(55, player.calculateScoreDifference());
    }
}
