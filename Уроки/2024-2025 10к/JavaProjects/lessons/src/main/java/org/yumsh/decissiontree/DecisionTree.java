package org.yumsh.decissiontree;

public class DecisionTree implements Regression {
    private final Node root;

    public DecisionTree(Node root) {
        this.root = root;
    }

    @Override
    public double compute(double[] x) {
        return root.compute(x);
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
