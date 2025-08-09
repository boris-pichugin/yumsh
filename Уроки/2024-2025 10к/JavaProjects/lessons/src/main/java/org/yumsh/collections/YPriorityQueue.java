package org.yumsh.collections;

public interface YPriorityQueue {
    /**
     * @return число элементов в очереди.
     */
    int size();

    /**
     * Положить элемент в очередь.
     * <p>
     * O(ln n)
     *
     * @param value новый элемент.
     */
    void add(int value);

    /**
     * Удалить первый элемент очереди.
     * <p>
     * O(ln n)
     *
     * @return первый элемент очереди.
     */
    int removeSmallest();

    /**
     * Посмотреть первый элемент очереди.
     * <p>
     * O(1)
     *
     * @return первый элемент очереди.
     */
    int elementSmallest();

    /**
     * @param value новое значение, которое заменит самое маленькое.
     */
    void replaceSmallest(int value);

    /**
     * @return массив всех добавленных значений.
     */
    int[] getValues();
}
