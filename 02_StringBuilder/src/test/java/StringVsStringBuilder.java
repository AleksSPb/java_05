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
    static String resStringBuffer;

    @AfterClass
    static public void compareResults() {
        if (resString != null && resStringBuilder != null) {
            assertEquals(resString, resStringBuilder);
        }
        if (resString != null && resStringBuffer != null) {
            assertEquals(resString, resStringBuffer);
        }
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
        Runtime.getRuntime().gc();
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < CHARS; ++i)
            s.append(i);

        resStringBuilder = s.toString();
    }

    @Test
    public void testStringBuffer() {
        Runtime.getRuntime().gc();
        StringBuffer s = new StringBuffer();
        for (int i = 0; i < CHARS; ++i)
            s.append(i);

        resStringBuffer = s.toString();
    }
}
