package org.yumsh.collections;

public interface YList {

    /**
     * @return число элементов в списке.
     */
    int size();

    /**
     * @param i номер требуемого элемента: {@code 0 <= i < size()}.
     * @return элемент списка с данным номером.
     */
    Object get(int i);

    /**
     * @param i    номер элемента, который требуется изменить: {@code 0 <= i < size()}.
     * @param item новый элемент.
     */
    void set(int i, Object item);

    /**
     * Добавить элемент в конец списка.
     *
     * @param item новый элемент.
     */
    void add(Object item);

    /**
     * @param i    номер элемента, на место которого
     *             следует вставить новый элемент:
     *             {@code 0 <= i <= size()}.
     * @param item новый элемент.
     */
    void insert(int i, Object item);

    /**
     * Удалить элемент.
     *
     * @param i номер элемента, который требуется удалить:
     *          {@code 0 <= i < size()}.
     */
    void remove(int i);

    /**
     * Найти элемент.
     *
     * @param item искомый элемент.
     * @return номер первого элемента, если он в списке есть,
     * или {@code -1}, если элемента в списке нет.
     */
    int indexOf(Object item);

    /**
     * Удалить все вхождения данного элемента из списка.
     *
     * @param item удаляемый элемент.
     */
    void removeAll(Object item);
}
