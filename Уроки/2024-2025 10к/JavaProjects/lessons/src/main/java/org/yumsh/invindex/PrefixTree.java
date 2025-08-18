package org.yumsh.invindex;

/// Префиксное дерево.
public interface PrefixTree {
    /// @return число элементов в словаре.
    int size();

    /// Присвоить данное значение данному ключу.
    ///
    /// Если `value == null`, то пара `(key, value)`
    /// удаляется из словаря.
    ///
    /// @param key   ключ.
    /// @param value значение.
    void put(String key, Object value);

    /// @param key ключ.
    /// @return значение, сопоставленное ключу.
    Object get(String key);
}
