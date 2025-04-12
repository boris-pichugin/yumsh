package org.yumsh.collections;

public interface YQueue {
    /**
     * @return число элементов в очереди.
     */
    int size();

    /**
     * Положить элемент в очередь.
     *
     * @param value новый элемент.
     */
    void add(Object value);

    /**
     * Удалить первый элемент очереди.
     *
     * @return первый элемент очереди.
     */
    Object remove();

    /**
     * Посмотреть первый элемент очереди.
     *
     * @return первый элемент очереди.
     */
    Object element();
}
