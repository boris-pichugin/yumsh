package org.yumsh.collections;

public class LinkedYList implements YList {
    private Item first = null;

    @Override
    public int size() {
        if (first == null) {
            return 0;
        }
        int size = 1;
        Item current = first;
        while (current.next != null) {
            size += 1;
            current = current.next;
        }
        return size;
    }

    @Override
    public Object get(int i) {
        // TODO
        return null;
    }

    @Override
    public void set(int i, Object value) {
        if (i < 0 || first == null) {
            throw new ArrayIndexOutOfBoundsException(i);
        }
        Item current = first;
        for (; i > 0; i--) {
            if (current.next == null) {
                throw new ArrayIndexOutOfBoundsException(i);
            }
            current = current.next;
        }
        current.value = value;
    }

    @Override
    public void add(Object value) {
        // TODO

    }

    @Override
    public void insert(int i, Object value) {
        if (i < 0) {
            throw new ArrayIndexOutOfBoundsException(i);
        }
        if (i == 0) {
            first = new Item(value, first);
            return;
        }
        Item current = first;
        for (; i > 1; i--) {
            if (current.next == null) {
                throw new ArrayIndexOutOfBoundsException(i);
            }
            current = current.next;
        }

        current.next = new Item(value, current.next);
    }

    @Override
    public void remove(int i) {
        if (i < 0 || first == null) {
            throw new ArrayIndexOutOfBoundsException(i);
        }
        if (i == 0) {
            first = first.next;
            return;
        }
        Item current = first;
        for (; i > 1; i--) {
            if (current.next == null) {
                throw new ArrayIndexOutOfBoundsException(i);
            }
            current = current.next;
        }
        if (current.next == null) {
            throw new ArrayIndexOutOfBoundsException(i);
        }
        current.next = current.next.next;
    }

    @Override
    public int indexOf(Object value) {
        // TODO
        return 0;
    }

    @Override
    public void removeAll(Object value) {
        // TODO

    }

    private static class Item {
        private Object value;
        private Item next;

        public Item(Object value, Item next) {
            this.value = value;
            this.next = next;
        }
    }
}
