package org.yumsh.collections;

public interface YMap {

    /**
     * @return число элементов в словаре.
     */
    int size();

    /**
     * Присвоить данное значение данному ключу.
     * <p>
     * Если {@code value == null}, то пара {@code (key, value)}
     * удаляется из словаря.
     *
     * @param key   ключ.
     * @param value значение.
     */
    void put(Object key, Object value);

    /**
     * @param key ключ.
     * @return значение, сопоставленное ключу.
     */
    Object get(Object key);

    /**
     * Удалить из словаря соответствие для данного ключа,
     * если оно там есть.
     *
     * @param key ключ.
     */
    void remove(Object key);
}
