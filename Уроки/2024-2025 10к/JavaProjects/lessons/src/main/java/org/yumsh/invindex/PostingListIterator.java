package org.yumsh.invindex;

/// Итератор по постинг-листу.
public class PostingListIterator {
    public static final PostingListIterator EMPTY = new PostingListIterator(new int[0], 0);

    private final int[] docIds;
    private final int size;
    private int pos = -1;

    public PostingListIterator(int[] docIds, int size) {
        this.docIds = docIds;
        this.size = size;
    }

    /// Переместить итератор на следующий документ.
    ///
    /// @return номер следующего документа или `Integer.MAX_VALUE`,
    /// если документов больше нет.
    public int next() {
        return pos < size
            ? docIds[++pos]
            : Integer.MAX_VALUE;
    }

    /// Переместить итератор на ближайший справа к данному документу.
    ///
    /// @param targetDocId целевой номер документа.
    /// @return номер документа `docId >= targetDocId`, на который установился итератор,
    /// или `Integer.MAX_VALUE` если номера всех документов в данном постинг листе меньше, чем `targetDocId`
    public int advance(int targetDocId) {
        // TODO переделать на бинпоиск.
        while (pos < size && docIds[pos] < targetDocId) {
            pos += 1;
        }
        return pos < size ? docIds[pos] : Integer.MAX_VALUE;
    }
}
