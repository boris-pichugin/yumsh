package org.yumsh.invindex;

import java.util.Arrays;

public class PostingList {
    private int[] docIds;
    private int size;

    public PostingList() {
        docIds = new int[16];
        size = 0;
    }

    private PostingList(PostingList original) {
        docIds = Arrays.copyOf(original.docIds, original.size);
        size = original.size;
    }

    public PostingList copy() {
        return new PostingList(this);
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

    public PostingListIterator iterator() {
        return size == 0 ? PostingListIterator.EMPTY : new PostingListIterator(docIds, size);
    }
}
