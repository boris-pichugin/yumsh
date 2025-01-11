package org.yumsh.collections;

import java.util.Arrays;
import java.util.Objects;

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
        Object[] newValues = Arrays.copyOf(values, values.length + 1);
        newValues[values.length] = item;
        values = newValues;
    }

    @Override
    public void insert(int i, Object item) {
        int n = values.length;
        Object[] newValues = new Object[n + 1];
        System.arraycopy(values, 0, newValues, 0, i);
        System.arraycopy(values, i, newValues, i + 1, n - i);
        newValues[i] = item;
        values = newValues;
    }

    @Override
    public void remove(int i) {
        int n = values.length;
        Object[] newValues = new Object[n - 1];
        System.arraycopy(values, 0, newValues, 0, i);
        System.arraycopy(values, i + 1, newValues, i, n - (i + 1));
        values = newValues;
    }

    @Override
    public int indexOf(Object item) {
        for (int i = 0; i < values.length; i++) {
            if (Objects.equals(item, values[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void removeAll(Object item) {
        // TODO
    }
}
