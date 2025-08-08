package org.yumsh.collections;

import java.util.Arrays;

public class HeapYPriorityQueue implements YPriorityQueue {
    private int size = 0;
    private int[] heap = new int[16];

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(int value) {
        if (size == heap.length) {
            heap = Arrays.copyOf(heap, heap.length * 2);
        }
        int i = size++;
        siftUp(this.heap, i, value);
    }

    @Override
    public int removeSmallest() {
        if (size == 0) {
            throw new IllegalStateException("Priority queue is empty.");
        }

        int smallest = heap[0];
        size -= 1;
        siftDown(heap, size, heap[size]);
        return smallest;
    }

    @Override
    public int elementSmallest() {
        if (size == 0) {
            throw new IllegalStateException("Priority queue is empty.");
        }
        return heap[0];
    }

    private static void siftUp(int[] heap, int i, int value) {
        while (i > 0) {
            int j = (i - 1) >>> 1;
            int parentValue = heap[j];
            if (parentValue <= value) {
                break;
            }
            heap[i] = parentValue;
            i = j;
        }
        heap[i] = value;
    }

    private static void siftDown(int[] heap, int size, int value) {
        int half = size >>> 1;
        int i = 0;
        while (i < half) {
            int next = (i << 1) + 1;
            int min = heap[next];
            int right = next + 1;
            if (right < size && heap[right] < min) {
                next = right;
                min = heap[right];
            }
            if (value < min) {
                break;
            }
            heap[i] = heap[next];
            i = next;
        }
        heap[i] = value;
    }
}
