package org.yumsh.collections;

public class HeapTopCollector implements TopCollector {
    private final int topSize;
    private final HeapYPriorityQueue queue = new HeapYPriorityQueue();

    public HeapTopCollector(int topSize) {
        this.topSize = topSize;
    }

    @Override
    public void put(int value) {
        if (topSize == 0) {
            return;
        }
        if (queue.size() < topSize) {
            queue.add(value);
        } else if (queue.elementSmallest() < value) {
            queue.replaceSmallest(value);
        }
    }

    @Override
    public int[] getTop() {
        return queue.getValues();
    }
}
