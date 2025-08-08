package org.yumsh.collections;

public interface YDeque extends YStack, YQueue {
    /**
     * @return число элементов в очереди.
     */
    @Override
    int size();

    /**
     * @param value добавить элемент в начало.
     */
    void addFirst(Object value);

    /**
     * Удалить элемент из начала.
     */
    Object removeFirst();

    /**
     * @return элемент из начала.
     */
    Object firstElement();

    /**
     * @param value добавить элемент в конец.
     */
    void addLast(Object value);

    /**
     * Удалить элемент из конца.
     */
    Object removeLast();

    /**
     * @return элемент из конца.
     */
    Object lastElement();

    @Override
    default void push(Object value) {
        addLast(value);
    }

    @Override
    default Object pop() {
        return removeLast();
    }

    @Override
    default Object peek() {
        return lastElement();
    }

    @Override
    default void add(Object value) {
        addLast(value);
    }

    @Override
    default Object remove() {
        return removeFirst();
    }

    @Override
    default Object element() {
        return firstElement();
    }
}
