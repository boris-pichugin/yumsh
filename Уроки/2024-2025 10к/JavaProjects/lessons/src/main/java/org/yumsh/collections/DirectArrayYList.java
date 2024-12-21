package org.yumsh.collections;

import java.util.Arrays;

public class DirectArrayYList implements YList {

    Object[] values = new Object[0];

    @Override
    public int size() {
        return values.length;
    }

    @Override
    public Object get(int i) {
        return values[i];
    }

    @Override
    public void set(int i, Object item) {
        values[i] = item;
    }

    @Override
    public void add(Object item) {
        values = Arrays.copyOf(values, values.length + 1);
        values[values.length - 1] = item;
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
}
