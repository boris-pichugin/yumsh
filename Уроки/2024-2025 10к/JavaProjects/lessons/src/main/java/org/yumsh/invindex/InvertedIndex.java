package org.yumsh.invindex;

/// Обратный индекс.
public interface InvertedIndex {
    /// @return число добавленных документов.
    int size();

    /// Добавить документ в индекс.
    ///
    /// @param docTerms список термов документа.
    /// @return порядковый номер добавленного документа.
    int put(String... docTerms);

    /// @param term некоторый терм.
    /// @return список документов, в которых содержится данный терм.
    PostingList get(String term);

    /// @param term1 некоторый терм.
    /// @param term2 второй терм.
    /// @return список документов, в которых содержатся оба терма.
    PostingList getAnd(String term1, String term2);

    /// @param term1 первый терм.
    /// @param term2 второй терм.
    /// @return список документов, в которых содержится хотя бы один из данных термов.
    PostingList getOr(String term1, String term2);

    /// @param terms список термов.
    /// @return список документов, в которых содержатся все термы.
    PostingList getAnd(String... terms);

    /// @param terms список термов.
    /// @return список документов, в которых содержится хотя бы один из данных термов.
    PostingList getOr(String... terms);

    /// @param terms          список термов.
    /// @param minShouldMatch минимальное число термов, в документе.
    /// @return список документов, в которых содержится не менее, чем `minShouldMatch` из данных термов.
    PostingList getRelaxedAnd(int minShouldMatch, String... terms);
}
