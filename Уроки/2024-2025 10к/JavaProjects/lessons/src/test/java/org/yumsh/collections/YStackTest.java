package org.yumsh.collections;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class YStackTest {

    private static YStack createStack() {
//        return new DirectArrayYStack();
//        return new ArrayYStack();
        return new ArrayYStack();
    }

    @Test
    public void testSize() {
        YStack stack = createStack();
        Assertions.assertEquals(0, stack.size());
        stack.push("ss");
        Assertions.assertEquals(1, stack.size());
        for (int i = 0; i < 100; i++) {
            stack.push(i);
            Assertions.assertEquals(2 + i, stack.size());
        }
    }

    @Test
    public void testPop() {
        YStack stack = createStack();
        try {
            stack.pop();
            Assertions.fail();
        } catch (Exception ignored) {
        }

        int size = 100;
        for (int i = 0; i < size; i++) {
            String value = "" + i;
            stack.push(value);
            Assertions.assertEquals(value, stack.peek());
        }
        for (int i = size - 1; i >= 0; i--) {
            String expected = "" + i;
            Assertions.assertEquals(expected, stack.peek());
            Assertions.assertEquals(expected, stack.pop());
        }

        try {
            stack.pop();
            Assertions.fail();
        } catch (Exception ignored) {
        }
    }
}
