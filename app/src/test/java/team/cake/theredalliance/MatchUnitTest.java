package team.cake.theredalliance;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class MatchUnitTest {
    @Test
    public void writeToJsonTest() {
        Match match = new Match();
        boolean result  = match.writeToJson("Some String");
        assertTrue(result);
    }
    @Test
    public void readMatchData() {
        Match match = new Match();
        int result  = match.readMatchData("Some String");
        assertEquals(42, result);
    }
    @Test
    public void loadMatchData() {
        Match match = new Match();
        int result  = match.loadMatchData("Some String");
        assertEquals(42, result);
    }
}