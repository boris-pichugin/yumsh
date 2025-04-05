package org.yumsh.collections;

import java.util.Arrays;

public class ArrayYStack implements YStack {
    private Object[] values = new Object[16];
    private int size = 0;

    @Override
    public int size() {
        return size;
    }

    @Override
    public void push(Object value) {
        if (size == values.length) {
            values = Arrays.copyOf(values, size * 2);
        }
        values[size++] = value;
    }

    @Override
    public Object pop() {
        if (size == 0) {
            throw new IllegalStateException("Stack is empty.");
        }
        Object top = values[--size];
        values[size] = null;
        return top;
    }

    @Override
    public Object peek() {
        if (size == 0) {
            throw new IllegalStateException("Stack is empty.");
        }
        return values[size - 1];
    }
}
