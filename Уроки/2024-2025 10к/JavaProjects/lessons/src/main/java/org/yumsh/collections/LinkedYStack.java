package org.yumsh.collections;

public class LinkedYStack implements YStack {
    private Item top = null;
    private int size = 0;

    @Override
    public int size() {
        return size;
    }

    @Override
    public void push(Object value) {
        top = new Item(value, top);
        size += 1;
    }

    @Override
    public Object pop() {
        if (top == null) {
            throw new IllegalStateException("Stack is empty.");
        }
        Object value = top.value;
        top = top.next;
        size -= 1;
        return value;
    }

    @Override
    public Object peek() {
        if (top == null) {
            throw new IllegalStateException("Stack is empty.");
        }
        return top.value;
    }

    private static class Item {
        private final Object value;
        private final Item next;

        public Item(Object value, Item next) {
            this.value = value;
            this.next = next;
        }
    }
}
