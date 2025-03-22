package org.yumsh.collections;

import java.util.Objects;

public class LinkedYList implements YList {
    private Item first = null;

    @Override
    public int size() {
        int size = 0;
        Item current = first;
        while (current != null) {
            size += 1;
            current = current.next;
        }
        return size;
    }

    @Override
    public Object get(int i) {
        return getItem(i).value;
    }

    @Override
    public void set(int i, Object value) {
        getItem(i).value = value;
    }

    @Override
    public void add(Object value) {
        if (first == null) {
            first = new Item(value, null);
            return;
        }
        Item current = first;
        while (current.next != null) {
            current = current.next;
        }
        current.next = new Item(value, null);
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
        Item prevItem = getItem(i - 1);
        prevItem.next = new Item(value, prevItem.next);
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
        Item prevItem = getItem(i - 1);
        if (prevItem.next == null) {
            throw new ArrayIndexOutOfBoundsException(i);
        }
        prevItem.next = prevItem.next.next;
    }

    @Override
    public int indexOf(Object value) {
        int idx = 0;
        Item current = first;
        while (current != null) {
            if (Objects.equals(current.value, value)) {
                return idx;
            }
            idx += 1;
            current = current.next;
        }
        return -1;
    }

    @Override
    public void removeAll(Object value) {
        while (
            first != null
            && Objects.equals(first.value, value)
        ) {
            first = first.next;
        }
        if (first == null) {
            return;
        }
        Item prevItem = first;
        while (prevItem.next != null) {
            Item nextItem = prevItem.next;
            if (Objects.equals(nextItem.value, value)) {
                prevItem.next = nextItem.next;
            } else {
                prevItem = nextItem;
            }
        }
    }

    private Item getItem(int i) {
        if (i < 0) {
            throw new ArrayIndexOutOfBoundsException(i);
        }
        int idx = 0;
        Item current = first;
        while (current != null) {
            if (idx == i) {
                return current;
            }
            idx += 1;
            current = current.next;
        }
        throw new ArrayIndexOutOfBoundsException(i);
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
