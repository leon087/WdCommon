package cm.java.util;

import android.test.InstrumentationTestCase;

public class CharsetsTest extends InstrumentationTestCase {

    public void testToBigEndianUtf16Bytes() throws Exception {
        char[] chars = {'s', 'c', 'h', 'o', 'o', 'l'};
        byte[] result = {0, 115, 0, 99, 0, 104, 0, 111, 0, 111, 0, 108};
        byte[] temp = Charsets.toBigEndianUtf16Bytes(chars, 0, 6);
        assertEquals(new String(temp), new String(result));
    }
}
