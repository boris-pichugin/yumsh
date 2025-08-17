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
        if (pos == -1) {
            pos = 0;
            if (pos == size) {
                return docId = Integer.MAX_VALUE;
            }
            docId = docIds[0];
        }
        if (targetDocId <= docId) {
            return docId;
        }

        int step = 16;
        int s = pos; // начало интервала поиска (включительно)
        int e = pos + step; // конец интервала поиска (включительно)
        while (e < size && docIds[e] < targetDocId) {
            step *= 2;
            s = e;
            e = s + step;
        }
        if (size <= e) {
            e = size - 1;
            if (docIds[e] < targetDocId) {
                pos = size;
                return docId = Integer.MAX_VALUE;
            }
        }

        // Инвариант: docIds[s] < targetDocId <= docIds[e]
        while (s + 1 < e) {
            int m = (s + e) >> 1;
            if (docIds[m] < targetDocId) {
                s = m;
            } else {
                e = m;
            }
        }
        pos = e;
        return docId = docIds[e];
    }
}
