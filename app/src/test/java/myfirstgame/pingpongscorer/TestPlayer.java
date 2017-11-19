package myfirstgame.pingpongscorer;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

/**
 * Created by James on 18/11/2017.
 */

public class TestPlayer {

    private Player player;

    @Before
    public void setUp() throws Exception {
        player = new Player("James", 13, 5, 110, 55);
    }

    @Test
    public void canGetWinPercentage() throws Exception {
        assertEquals(72, player.winPercentage());
    }

    @Test
    public void canGetScoreDifferent() throws Exception {
        assertEquals(55, player.calculateScoreDifference());
    }

    @Test
    public void canReturnSortedArray() {
        Player player1 = new Player("James", 15, 14, 110, 55);
        Player player2 = new Player("Brian", 10, 5, 110, 55);
        Player player3 = new Player("Steph", 20, 1, 110, 55);
        ArrayList<Player> testArray = new ArrayList<>();

        testArray.add(player1);
        testArray.add(player2);
        testArray.add(player3);

        Collections.sort(testArray, Collections.reverseOrder(Player.getComparatorByPercentage()));

        System.out.println(testArray.get(0).winPercentage());
        System.out.println(testArray.get(1).winPercentage());
        System.out.println(testArray.get(2).winPercentage());

        assertEquals("Steph", testArray.get(0).getName());
    }
}
