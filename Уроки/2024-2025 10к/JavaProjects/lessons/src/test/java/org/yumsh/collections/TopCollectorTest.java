package org.yumsh.collections;

import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TopCollectorTest {

    private static TopCollector createTopCollector(int topSize) {
        return new MyTopCollector(topSize);
    }

    @Test
    public void testZero() {
        TopCollector topCollector = createTopCollector(0);
        Assertions.assertArrayEquals(new int[0], topCollector.getTop());

        topCollector.put(533);
        Assertions.assertArrayEquals(new int[0], topCollector.getTop());

        for (int i = 0; i < 1000; i++) {
            topCollector.put(533);
        }
        Assertions.assertArrayEquals(new int[0], topCollector.getTop());
    }

    @Test
    public void test() {
        int topSize = 10;
        TopCollector topCollector = createTopCollector(topSize);
        Assertions.assertArrayEquals(new int[0], topCollector.getTop());

        topCollector.put(533);
        Assertions.assertArrayEquals(new int[] {533}, topCollector.getTop());

        for (int i = 0; i < 1000; i++) {
            topCollector.put(i);
        }
        int[] expected = new int[topSize];
        for (int i = 990; i < 1000; i++) {
            expected[i - 990] = i;
        }
        int[] actual = topCollector.getTop();
        Arrays.sort(actual);
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void testConst() {
        int topSize = 10;
        TopCollector topCollector = createTopCollector(topSize);
        Assertions.assertArrayEquals(new int[0], topCollector.getTop());

        topCollector.put(533);
        Assertions.assertArrayEquals(new int[] {533}, topCollector.getTop());

        topCollector.put(533);
        Assertions.assertArrayEquals(new int[] {533, 533}, topCollector.getTop());

        for (int i = 0; i < 1000; i++) {
            topCollector.put(533);
        }
        int[] expected = new int[topSize];
        for (int i = 990; i < 1000; i++) {
            expected[i - 990] = 533;
        }
        int[] actual = topCollector.getTop();
        Arrays.sort(actual);
        Assertions.assertArrayEquals(expected, actual);
    }
}
