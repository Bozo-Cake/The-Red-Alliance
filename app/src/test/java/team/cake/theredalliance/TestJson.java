package team.cake.theredalliance;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class TestJson {
    @Test
    public void writeToJsonTest() {
        String fileName = "test.json";
        String contents = "Testing";
        Json json = new Json();
        json.writeJson(contents, fileName);
        String newContents = json.readJson(fileName);
        assertEquals(contents, newContents);
    }
}