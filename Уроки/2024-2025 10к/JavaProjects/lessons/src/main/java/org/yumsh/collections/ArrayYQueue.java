package org.yumsh.collections;

public class ArrayYQueue implements YQueue {
    private Object[] values = new Object[16];
    private int size = 0;
    private int first = 0;

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(Object value) {
        if (size == values.length) {
            Object[] newValues = new Object[size * 2];
            System.arraycopy(values, first, newValues, 0, size - first);
            System.arraycopy(values, 0, newValues, size - first, first);
            first = 0;
            values = newValues;
        }
        int last = (first + size) % values.length;
        values[last] = value;
        size += 1;
    }

    @Override
    public Object remove() {
        if (size == 0) {
            throw new IllegalStateException("Queue is empty.");
        }
        Object value = values[first];
        values[first] = null;
        first = (first + 1) % values.length;
        size -= 1;
        return value;
    }

    @Override
    public Object element() {
        if (size == 0) {
            throw new IllegalStateException("Queue is empty.");
        }
        return values[first];
    }
}
