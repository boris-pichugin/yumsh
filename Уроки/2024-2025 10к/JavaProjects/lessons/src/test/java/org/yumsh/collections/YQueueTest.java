package org.yumsh.collections;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class YQueueTest {

    private static YQueue createQueue() {
        return new ArrayYQueue();
//        return new LinkedYQueue();
    }

    @Test
    public void testSize() {
        YQueue queue = createQueue();
        Assertions.assertEquals(0, queue.size());
        queue.add("ss");
        Assertions.assertEquals(1, queue.size());
        for (int i = 0; i < 100; i++) {
            queue.add(i);
            Assertions.assertEquals(2 + i, queue.size());
        }
    }

    @Test
    public void testRemove() {
        YQueue queue = createQueue();
        try {
            queue.remove();
            Assertions.fail();
        } catch (Exception ignored) {
        }

        try {
            queue.element();
            Assertions.fail();
        } catch (Exception ignored) {
        }

        int size = 100;
        for (int i = 0; i < size; i++) {
            String value = "" + i;
            queue.add(value);
            Assertions.assertEquals("0", queue.element());
        }
        for (int i = 0; i < size; i++) {
            String expected = "" + i;
            Assertions.assertEquals(expected, queue.element());
            Assertions.assertEquals(expected, queue.remove());
            Assertions.assertEquals(size - i - 1, queue.size());
        }

        try {
            queue.remove();
            Assertions.fail();
        } catch (Exception ignored) {
        }

        try {
            queue.element();
            Assertions.fail();
        } catch (Exception ignored) {
        }
    }
}
