package org.yumsh.collections;

/**
 * Стек.
 */
public interface YStack {

    /**
     * @return число элементов в стеке.
     */
    int size();

    /**
     * Положить элемент на вершину стека.
     *
     * @param value новый элемент.
     */
    void push(Object value);

    /**
     * Извлечь элемент с вершины стека.
     *
     * @return элемент с вершины стека.
     */
    Object pop();

    /**
     * Посмотреть на элемент с вершины стека.
     *
     * @return элемент с вершины стека.
     */
    Object peek();
}
