package org.yumsh.invindex;

public interface StringLexEnumerator {
    /// Добавить строку в коллекцию.
    ///
    /// @return число строк коллекции, которые лексикографически меньше, чем данная строка.
    int put(String str);

    /// @param str некоторая строка.
    /// @return число строк коллекции, которые лексикографически меньше, чем данная строка.
    int get(String str);
}
