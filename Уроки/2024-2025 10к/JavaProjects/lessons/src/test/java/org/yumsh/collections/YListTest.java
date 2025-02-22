package org.yumsh.collections;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class YListTest {

    private static YList createList() {
        return new DirectArrayYList();
//        return new ArrayYList();
    }

    @Test
    public void testSize() {
        YList list = createList();
        Assertions.assertEquals(0, list.size());
        list.add("ss");
        Assertions.assertEquals(1, list.size());
        for (int i = 0; i < 10; i++) {
            list.add(i);
            Assertions.assertEquals(2 + i, list.size());
        }
    }

    @Test
    public void testInsert() {
        YList list = createList();
        for (int i = 0; i < 10; i++) {
            Integer value = i;
            list.insert(i, value);
            Assertions.assertSame(list.get(i), value);
        }
        checkValues(list,
            0, 1, 2, 3, 4, 5, 6, 7, 8, 9
        );

        for (int i = 0; i < 10; i++) {
            Integer value = i + 10;
            int idx = 4 + i;
            list.insert(idx, value);
            Assertions.assertSame(list.get(idx), value);
        }
        checkValues(
            list,
            0, 1, 2, 3,
            10, 11, 12, 13, 14, 15, 16, 17, 18, 19,
            4, 5, 6, 7, 8, 9
        );

        try {
            list.insert(-1, 0);
            Assertions.fail();
        } catch (Exception ignored) {
        }

        try {
            list.insert(list.size() + 1, 0);
            Assertions.fail();
        } catch (Exception ignored) {
        }
    }

    private static void checkValues(YList list, Object... values) {
        Assertions.assertEquals(values.length, list.size());
        for (int i = 0; i < values.length; i++) {
            Assertions.assertEquals(values[i], list.get(i));
        }
    }
}
