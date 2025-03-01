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
    public void set(int i, Object value) {
        if (size <= i) {
            throw new ArrayIndexOutOfBoundsException(i);
        }
        values[i] = value;
    }

    @Override
    public void add(Object value) {
        if (size == values.length) {
            values = Arrays.copyOf(values, size * 2);
        }
        values[size++] = value;
    }

    @Override
    public void insert(int i, Object value) {
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
        values[i] = value;
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
    public int indexOf(Object value) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(value, values[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void removeAll(Object value) {
        int j = 0;
        for (int i = 0; i < size; i++) {
            if (!Objects.equals(values[i], value)) {
                values[j++] = values[i];
            }
        }
        for (int i = j; i < size; i++) {
            values[i] = null;
        }
        size = j;
    }
}
