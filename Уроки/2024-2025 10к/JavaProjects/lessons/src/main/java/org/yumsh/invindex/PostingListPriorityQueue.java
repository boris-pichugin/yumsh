package org.yumsh.invindex;

public final class PostingListPriorityQueue {
    private final PostingListIterator[] heap;

    public PostingListPriorityQueue(PostingListIterator[] heap) {
        this.heap = heap;
        for (int i = heap.length / 2 - 1; i >= 0; i--) {
            siftDown(heap, heap[i], i);
        }
    }

    public PostingListIterator least() {
        return heap[0];
    }

    public void replaceLeast(PostingListIterator iterator) {
        siftDown(heap, iterator, 0);
    }

    private static void siftDown(PostingListIterator[] heap, PostingListIterator value, int i) {
        int size = heap.length;
        int half = size >>> 1;
        while (i < half) {
            int next = (i << 1) + 1;
            PostingListIterator min = heap[next];
            int right = next + 1;
            if (right < size && less(heap[right], min)) {
                next = right;
                min = heap[right];
            }
            if (less(value, min)) {
                break;
            }
            heap[i] = heap[next];
            i = next;
        }
        heap[i] = value;
    }

    private static boolean less(PostingListIterator a, PostingListIterator b) {
        return a.docId() < b.docId() || (a.docId() == b.docId() && a.size() < b.size());
    }
}
