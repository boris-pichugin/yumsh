package org.yumsh.collections;

import java.util.Arrays;
import java.util.Random;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class YPriorityQueueTest {

    private static YPriorityQueue createPriorityQueue() {
        return new YPriorityQueueImpl();
    }

    @Test
    public void testEmpty() {
        YPriorityQueue queue = createPriorityQueue();
        testEmpty(queue);
    }

    @Test
    public void testOne() {
        YPriorityQueue queue = createPriorityQueue();
        queue.add(533);
        Assertions.assertEquals(1, queue.size());

        Assertions.assertEquals(533, queue.elementSmallest());
        Assertions.assertEquals(533, queue.removeSmallest());
        testEmpty(queue);
    }

    @Test
    public void testMany() {
        YPriorityQueue queue = createPriorityQueue();
        Random rnd = new Random(42);
        for (int k = 0; k < 10; k++) {
            int size = 1 + 100 * k;
            int[] values = new int[size];
            for (int i = 0; i < size; i++) {
                int v = rnd.nextInt(0, 100);
                queue.add(v);
                values[i] = v;
                Assertions.assertEquals(i + 1, queue.size());
            }
            Arrays.sort(values);
            for (int i = 0; i < size; i++) {
                int v = values[i];
                Assertions.assertEquals(v, queue.elementSmallest());
                Assertions.assertEquals(v, queue.removeSmallest());
                Assertions.assertEquals(size - i - 1, queue.size());
            }
            testEmpty(queue);
        }
    }

    private static void testEmpty(YPriorityQueue queue) {
        Assertions.assertEquals(0, queue.size());
        try {
            queue.removeSmallest();
            Assertions.fail();
        } catch (Exception ignored) {
        }

        try {
            queue.elementSmallest();
            Assertions.fail();
        } catch (Exception ignored) {
        }
    }
}
