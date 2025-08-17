package org.yumsh.invindex;

/// Итератор по постинг-листу.
public class PostingListIterator {
    public static final PostingListIterator EMPTY = new PostingListIterator(new int[0], 0);

    private final int[] docIds;
    private final int size;
    private int docId = -1;
    private int pos = -1;

    public PostingListIterator(int[] docIds, int size) {
        this.docIds = docIds;
        this.size = size;
    }

    /// @return размер постинг-листа.
    public int size() {
        return size;
    }

    /// @return номер текущего документа.
    public int docId() {
        return docId;
    }

    /// Переместить итератор на следующий документ.
    ///
    /// @return номер следующего документа или `Integer.MAX_VALUE`,
    /// если документов больше нет.
    public int next() {
        docId = (pos + 1 < size ? docIds[++pos] : Integer.MAX_VALUE);
        return docId;
    }

    /// Переместить итератор на ближайший справа к данному документу.
    ///
    /// @param targetDocId целевой номер документа.
    /// @return номер документа `docId >= targetDocId`, на который установился итератор,
    /// или `Integer.MAX_VALUE` если номера всех документов в данном постинг листе меньше, чем `targetDocId`
    public int advance(int targetDocId) {
        // TODO переделать на бинпоиск.
        if (pos == -1) {
            pos = 0;
        }
        while (pos < size && docIds[pos] < targetDocId) {
            pos += 1;
        }
        docId = pos < size ? docIds[pos] : Integer.MAX_VALUE;
        return docId;
    }
}
