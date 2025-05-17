package org.yumsh.collections;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class YMapTest {

    private static YMap createMap() {
//        return new DirectArrayYMap();
//        return new ArrayYMap();
        return new HashYMap();
    }

    @Test
    public void testSize() {
        YMap map = createMap();
        Assertions.assertEquals(0, map.size());
        map.put("ss", "ss");
        Assertions.assertEquals(1, map.size());
        int n = 200;
        for (int i = 0; i < n; i++) {
            map.put("" + i, "" + i);
            map.put("" + i, "" + i);
            Assertions.assertEquals(2 + i, map.size());
        }
    }

    @Test
    public void test() {
        YMap map = createMap();
        Assertions.assertEquals(0, map.size());
        Assertions.assertNull(map.get("a"));
        map.put("a", "a");
        Assertions.assertEquals(1, map.size());

        map.remove("a");
        Assertions.assertEquals(0, map.size());
        Assertions.assertNull(map.get("a"));

        int n = 200;
        for (int k = 0; k < 10; k++) {
            for (int i = 0; i < n; i++) {
                String key = "k" + i;
                String value = "v" + k + "." + i;
                map.put(key, value);
                Assertions.assertEquals(value, map.get(key));
                Assertions.assertEquals(i + 1, map.size());
                map.put(key, value);
                Assertions.assertEquals(value, map.get(key));
                Assertions.assertEquals(i + 1, map.size());
            }
            for (int i = 0; i < n; i++) {
                String key = "k" + i;
                String value = "v" + k + "." + i;
                Assertions.assertEquals(value, map.get(key));
            }
            for (int i = 0; i < n; i++) {
                String key = "k" + ((i + 7) % n);
                map.remove(key);
                Assertions.assertNull(map.get(key));
                Assertions.assertEquals(n - i - 1, map.size());
                map.remove(key);
                Assertions.assertNull(map.get(key));
                Assertions.assertEquals(n - i - 1, map.size());
            }
        }
    }
}
