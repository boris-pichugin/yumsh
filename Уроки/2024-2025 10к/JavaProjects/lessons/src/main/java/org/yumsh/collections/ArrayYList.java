package org.yumsh.collections;

import java.util.Arrays;
import java.util.Objects;

public class ArrayYList implements YList {
    private Object[] values = new Object[16];
    private int size = 0;

    @Override
    public int size() {
        return size;
    }

    @Override
    public Object get(int i) {
        if (size <= i) {
            throw new ArrayIndexOutOfBoundsException(i);
        }
        return values[i];
    }

    @Override
    public void set(int i, Object item) {
        if (size <= i) {
            throw new ArrayIndexOutOfBoundsException(i);
        }
        values[i] = item;
    }

    @Override
    public void add(Object item) {
        if (size == values.length) {
            values = Arrays.copyOf(values, size * 2);
        }
        values[size++] = item;
    }

    @Override
    public void insert(int i, Object item) {
        if (size < i) {
            throw new ArrayIndexOutOfBoundsException(i);
        }
        if (size == values.length) {
            Object[] newValues = new Object[size * 2];
            System.arraycopy(values, 0, newValues, 0, i);
            System.arraycopy(values, i, newValues, i + 1, size - i);
            values = newValues;
        } else {
            System.arraycopy(values, i, values, i + 1, size - i);
        }
        values[i] = item;
        size += 1;
    }

    @Override
    public void remove(int i) {
        if (size <= i) {
            throw new ArrayIndexOutOfBoundsException(i);
        }
        size -= 1;
        System.arraycopy(values, i + 1, values, i, size - i);
        values[size] = null;
    }

    @Override
    public int indexOf(Object item) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(item, values[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void removeAll(Object item) {
        int j = 0;
        for (int i = 0; i < size; i++) {
            if (!Objects.equals(values[i], item)) {
                values[j++] = values[i];
            }
        }
        for (int i = j; i < size; i++) {
            values[i] = null;
        }
        size = j;
    }
}
