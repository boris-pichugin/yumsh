package org.yumsh.collections;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class YListTest {

    private static YList createList() {
//        return new DirectArrayYList();
        return new ArrayYList();
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
    public void testGet() {
        YList list = createList();
        try {
            list.get(-1);
            Assertions.fail();
        } catch (Exception ignored) {
        }

        try {
            list.get(0);
            Assertions.fail();
        } catch (Exception ignored) {
        }

        for (int i = 0; i < 20; i++) {
            list.add("" + i);
        }
        for (int i = 0; i < 20; i++) {
            Assertions.assertEquals("" + i, list.get(i));
        }

        try {
            list.get(-1);
            Assertions.fail();
        } catch (Exception ignored) {
        }

        try {
            list.get(list.size());
            Assertions.fail();
        } catch (Exception ignored) {
        }
    }

    @Test
    public void testSet() {
        YList list = createList();
        try {
            list.set(-1, "a");
            Assertions.fail();
        } catch (Exception ignored) {
        }

        try {
            list.set(0, "a");
            Assertions.fail();
        } catch (Exception ignored) {
        }

        for (int i = 0; i < 20; i++) {
            list.add("" + i);
        }
        for (int i = 0; i < 20; i++) {
            list.set(i, "s" + i);
            Assertions.assertEquals("s" + i, list.get(i));
        }

        try {
            list.set(-1, "a");
            Assertions.fail();
        } catch (Exception ignored) {
        }

        try {
            list.set(list.size(), "a");
            Assertions.fail();
        } catch (Exception ignored) {
        }
    }

    @Test
    public void testAdd() {
        YList list = createList();
        list.add(0);
        checkValues(list, 0);
        for (int i = 1; i < 20; i++) {
            list.add(i);
        }
        checkValues(
            list,
            0, 1, 2, 3, 4, 5, 6, 7, 8, 9,
            10, 11, 12, 13, 14, 15, 16, 17, 18, 19
        );
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

    @Test
    public void testRemove() {
        YList list = createList();
        try {
            list.remove(-1);
            Assertions.fail();
        } catch (Exception ignored) {
        }

        try {
            list.remove(0);
            Assertions.fail();
        } catch (Exception ignored) {
        }

        for (int i = 0; i < 20; i++) {
            list.add(i);
        }
        list.remove(0);
        checkValues(
            list,
            1, 2, 3, 4, 5, 6, 7, 8, 9,
            10, 11, 12, 13, 14, 15, 16, 17, 18, 19
        );
        list.remove(18);
        checkValues(
            list,
            1, 2, 3, 4, 5, 6, 7, 8, 9,
            10, 11, 12, 13, 14, 15, 16, 17, 18
        );
        list.remove(3);
        checkValues(
            list,
            1, 2, 3, 5, 6, 7, 8, 9,
            10, 11, 12, 13, 14, 15, 16, 17, 18
        );
        list.remove(13);
        checkValues(
            list,
            1, 2, 3, 5, 6, 7, 8, 9,
            10, 11, 12, 13, 14, 16, 17, 18
        );

        try {
            list.remove(-5);
            Assertions.fail();
        } catch (Exception ignored) {
        }

        try {
            list.remove(list.size());
            Assertions.fail();
        } catch (Exception ignored) {
        }
    }

    @Test
    public void testIndexOf() {
        YList list = createList();
        Assertions.assertEquals(-1, list.indexOf("a"));
        for (int i = 0; i < 20; i++) {
            list.add("" + i);
        }
        Assertions.assertEquals(-1, list.indexOf("a"));
        Assertions.assertEquals(0, list.indexOf("0"));
        Assertions.assertEquals(1, list.indexOf("1"));
        Assertions.assertEquals(19, list.indexOf("19"));
        Assertions.assertEquals(-1, list.indexOf("20"));

        list.add("0");
        list.add("19");
        Assertions.assertEquals(0, list.indexOf("0"));
        Assertions.assertEquals(19, list.indexOf("19"));
        list.remove(0);
        Assertions.assertEquals(19, list.indexOf("0"));
        Assertions.assertEquals(18, list.indexOf("19"));
    }

    @Test
    public void testRemoveAll() {
        YList list = createList();
        list.removeAll("0");
        checkValues(list);
        for (int i = 0; i < 5; i++) {
            list.add("" + i);
        }
        for (int i = 0; i < 5; i++) {
            list.add("" + i);
        }
        for (int i = 0; i < 5; i++) {
            list.add("" + i);
        }
        list.removeAll("0");
        checkValues(list,
            "1", "2", "3", "4",
            "1", "2", "3", "4",
            "1", "2", "3", "4"
        );
        list.removeAll("2");
        checkValues(list,
            "1", "3", "4",
            "1", "3", "4",
            "1", "3", "4"
        );
        list.add("5");
        list.add("5");
        list.add("5");
        list.removeAll("5");
        checkValues(list,
            "1", "3", "4",
            "1", "3", "4",
            "1", "3", "4"
        );
    }

    private static void checkValues(YList list, Object... values) {
        Assertions.assertEquals(values.length, list.size());
        for (int i = 0; i < values.length; i++) {
            Assertions.assertEquals(values[i], list.get(i));
        }
    }
}
