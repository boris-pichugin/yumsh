package org.yumsh.collections;

public interface TopCollector {
    /**
     * Добавить новый элемент.
     *
     * @param value новый элемент.
     */
    void put(int value);

    /**
     * @return заданное число (известно заранее) самых больших элементов из добавленных.
     */
    int[] getTop();
}
