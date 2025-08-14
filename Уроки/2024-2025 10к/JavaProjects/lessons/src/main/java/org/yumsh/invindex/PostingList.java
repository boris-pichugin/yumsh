package org.yumsh.invindex;

import java.util.Arrays;

public class PostingList {
    private int[] docIds = new int[16];
    private int size = 0;

    public PostingList() {
    }

    private PostingList(PostingList original) {
        docIds = Arrays.copyOf(original.docIds, original.size);
        size = original.size;
    }

    public int size() {
        return size;
    }

    public void add(int docId) {
        if (size > 0 && docId <= docIds[size - 1]) {
            return;
        }
        if (size == docIds.length) {
            docIds = Arrays.copyOf(docIds, size * 2);
        }
        docIds[size++] = docId;
    }

    public PostingList copy() {
        return new PostingList(this);
    }

    public PostingListIterator iterator() {
        return size == 0 ? PostingListIterator.EMPTY : new PostingListIterator(docIds, size);
    }
}
