package org.yumsh.decissiontree;

import java.util.Arrays;
import java.util.Comparator;

public class DecisionTreeBuilder implements RegressionBuilder {
    private final int maxDepth;
    private final int minSamplesInNode;

    private double[][] samples = new double[16][0];
    private int size = 0;

    public DecisionTreeBuilder(int maxDepth, int minSamplesInNode) {
        this.maxDepth = maxDepth;
        this.minSamplesInNode = minSamplesInNode;
    }

    @Override
    public void add(double[] x, double y) {
        if (size == samples.length) {
            samples = Arrays.copyOf(samples, size * 2);
        }
        double[] sample = Arrays.copyOf(x, x.length + 1);
        sample[x.length] = y;
        samples[size++] = sample;
    }

    @Override
    public Regression build() {
        double sumY = 0.0;
        double sumY2 = 0.0;
        int yIdx = samples[0].length - 1;
        for (int i = 0; i < size; i++) {
            double y = samples[i][yIdx];
            sumY += y;
            sumY2 += y * y;
        }
        Node root = buildNode(0, size, maxDepth, sumY, sumY2);
        return root::compute;
    }

    private Node buildNode(int from, int to, int maxDepth, double sumY, double sumY2) {
        int n = to - from;
        if (maxDepth == 0 || n < 2 * minSamplesInNode) {
            return new Node(sumY / n);
        }
        int yIdx = samples[0].length - 1;
        int bestXIdx = -1;
        int bestSplit = -1;
        double bestThreshold = 0.0;
        double bestMSE = sumY2 - (sumY * sumY) / n;
        double bestSumYLeft = 0.0;
        double bestSumY2Left = 0.0;

        for (int idx = 0; idx < yIdx; idx++) {
            int xIdx = idx;
            Arrays.sort(samples, from, to, Comparator.comparingDouble(x -> x[xIdx]));

            double sumYLeft = 0.0;
            double sumY2Left = 0.0;
            for (int i = from; i < from + minSamplesInNode - 1; i++) {
                double y = samples[i][yIdx];
                sumYLeft += y;
                sumY2Left += y * y;
            }
            for (int i = from + minSamplesInNode - 1; i < to - minSamplesInNode; i++) {
                double[] sample = samples[i];
                double y = sample[yIdx];
                sumYLeft += y;
                sumY2Left += y * y;
                double sumYRight = sumY - sumYLeft;
                double sumY2Right = sumY2 - sumY2Left;

                int k = i - from + 1;
                int m = n - k;
                double mse = (sumY2Left - sumYLeft * sumYLeft / k) + (sumY2Right - sumYRight * sumYRight / m);
                if (mse < bestMSE) {
                    bestMSE = mse;
                    bestXIdx = xIdx;
                    bestSplit = i + 1;
                    bestThreshold = (sample[xIdx] + samples[i + 1][xIdx]) * 0.5;
                    bestSumYLeft = sumYLeft;
                    bestSumY2Left = sumY2Left;
                }
            }
        }

        if (bestXIdx == -1) {
            return new Node(sumY / n);
        }

        if (bestXIdx != yIdx - 1) {
            int xIdx = bestXIdx;
            Arrays.sort(samples, from, to, Comparator.comparingDouble(x -> x[xIdx]));
        }
        Node left = buildNode(from, bestSplit, maxDepth - 1, bestSumYLeft, bestSumY2Left);
        Node right = buildNode(bestSplit, to, maxDepth - 1, sumY - bestSumYLeft, sumY2 - bestSumY2Left);
        return new Node(bestXIdx, bestThreshold, left, right);
    }

    public static final class Node {
        private final int idx;
        private final double threshold;
        private final double value;
        private final Node left;
        private final Node right;

        public Node(int idx, double threshold, Node left, Node right) {
            this.idx = idx;
            this.threshold = threshold;
            this.value = -1;
            this.left = left;
            this.right = right;
        }

        public Node(double value) {
            this.idx = -1;
            this.threshold = 0;
            this.value = value;
            this.left = null;
            this.right = null;
        }

        double compute(double[] x) {
            if (idx < 0) {
                return value;
            }
            if (x[idx] < threshold) {
                return left.compute(x);
            } else {
                return right.compute(x);
            }
        }
    }
}
