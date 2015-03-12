import org.junit.AfterClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 *
 */
public class StringVsStringBuilder {
    static final int CHARS = 30000;
    static String resString;
    static String resStringBuilder;

    @AfterClass
    static public void compareResults() {
        assertEquals(resString, resStringBuilder);
    }

    @Test
    public void testString() {
        String s = "";
        for (int i = 0; i < CHARS; ++i)
            s += Integer.toString(i);

        resString = s;
    }

    @Test
    public void testStringBuilder() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < CHARS; ++i)
            s.append(Integer.toString(i));

        resStringBuilder = s.toString();
    }
}
