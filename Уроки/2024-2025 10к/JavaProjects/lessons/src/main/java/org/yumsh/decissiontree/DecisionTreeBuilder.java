package org.yumsh.decissiontree;

import java.util.Arrays;
import java.util.Comparator;

public class DecisionTreeBuilder implements RegressionBuilder {
    private final int maxDepth;

    private double[][] samples = new double[16][0];
    private int size = 0;

    public DecisionTreeBuilder(int maxDepth) {
        this.maxDepth = maxDepth;
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
        Node root = buildNode(samples, 0, size);
        return root::compute;
    }

    private Node buildNode(double[][] samples, int from, int to) {

        Arrays.sort(samples, from, to, Comparator.comparingDouble(x -> x[0]));
        return null;
    }

    public static final class Node {
        private final int idx;
        private final double threshold;
        private final double value;
        private Node left = null;
        private Node right = null;

        public Node(int idx, double threshold) {
            this.idx = idx;
            this.threshold = threshold;
            this.value = -1;
        }

        public Node(double value) {
            this.idx = -1;
            this.threshold = 0;
            this.value = value;
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
