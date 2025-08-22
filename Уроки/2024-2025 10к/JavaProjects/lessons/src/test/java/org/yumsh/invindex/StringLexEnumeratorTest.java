package org.yumsh.invindex;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StringLexEnumeratorTest {

    private static StringLexEnumerator createEnumerator() {
//        return new DirectArrayYMap();
//        return new ArrayYMap();
        return new FstStringLexEnumerator();
    }

    @Test
    public void testEmpty() {
        StringLexEnumerator enumerator = createEnumerator();

        Assertions.assertEquals(0, enumerator.size());
        Assertions.assertEquals(0, enumerator.get("a"));
        try {
            enumerator.get(-1);
            Assertions.fail();
        } catch (Exception ignored) {
        }
        try {
            enumerator.get(0);
            Assertions.fail();
        } catch (Exception ignored) {
        }
        try {
            enumerator.get(10);
            Assertions.fail();
        } catch (Exception ignored) {
        }
    }

    @Test
    public void testRnd() {
        Random rnd = new Random(42);
        for (int k = 0; k < 100; k++) {
            StringLexEnumerator enumerator = createEnumerator();
            int size = rnd.nextInt(0, 1000);
            Set<String> stringsSet = new HashSet<>();
            while (stringsSet.size() < size) {
                stringsSet.add(generateString(rnd));
            }
            List<String> strings = new ArrayList<>(stringsSet);
            strings.sort(null);
//            Collections.shuffle(strings, rnd);
            for (int i = 0; i < strings.size(); i++) {
                String str = strings.get(i);
                int idx = enumerator.put(str);
                Assertions.assertEquals(i + 1, enumerator.size());

                strings.subList(0, i + 1).sort(String::compareTo);
                Assertions.assertEquals(idx, strings.indexOf(str));

                Assertions.assertEquals(str, enumerator.get(idx));
            }
            strings.sort(String::compareTo);
            for (int i = 0; i < strings.size(); i++) {
                Assertions.assertEquals(strings.get(i), enumerator.get(i));
            }

            try {
                enumerator.get(strings.size());
                Assertions.fail();
            } catch (Exception ignored) {
            }
        }
    }

    private static String generateString(Random rnd) {
        char[] chars = new char[rnd.nextInt(100)];
        for (int i = 0; i < chars.length; i++) {
            chars[i] = (char) rnd.nextInt('a', 'z' + 1);
        }
        return new String(chars);
    }
}
