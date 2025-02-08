package org.yumsh.collections;

import java.util.Arrays;

public class ArrayYList implements YList {

    Object[] values = new Object[16];
    int size;

    @Override
    public int size() {
        return size;
    }

    @Override
    public Object get(int i) {
        if (i < 0 || size <= i) {
            throw new ArrayIndexOutOfBoundsException(i);
        }
        return values[i];
    }

    @Override
    public void set(int i, Object item) {

    }

    @Override
    public void add(Object item) {
        if (size == values.length) {
            values = Arrays.copyOf(values, size * 2);
        }
        values[size] = item;
        size += 1;
    }

    @Override
    public void insert(int i, Object item) {

    }

    @Override
    public void remove(int i) {

    }

    @Override
    public int indexOf(Object item) {
        return 0;
    }

    @Override
    public void removeAll(Object item) {

    }
}
