package org.yumsh.collections;

public class LinkedYQueue implements YQueue {
    private Item first = null;
    private Item last = null;
    private int size = 0;

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(Object value) {
        if (first == null) {
            first = last = new Item(value, null);
        } else {
            last = last.next = new Item(value, null);
        }
        size += 1;
    }

    @Override
    public Object remove() {
        if (size == 0) {
            throw new IllegalStateException("Queue is empty.");
        }
        Object value = first.value;
        first = first.next;
        if (first == null) {
            last = null;
        }
        size -= 1;
        return value;
    }

    @Override
    public Object element() {
        if (size == 0) {
            throw new IllegalStateException("Queue is empty.");
        }
        return first.value;
    }

    private static class Item {
        private final Object value;
        private Item next;

        public Item(Object value, Item next) {
            this.value = value;
            this.next = next;
        }
    }
}
