package org.yumsh.decissiontree;

public class BoostedForestBuilder implements RegressionBuilder {

    private final DecisionTreeBuilder decisionTreeBuilder;
    private final int numTrees;

    /// @param maxDepth         максимальная глубина дерева.
    /// @param minSamplesInNode минимальное число обучающих образов в листе дерева.
    /// @param numTrees         число деревьев в лесу.
    public BoostedForestBuilder(int maxDepth, int minSamplesInNode, int numTrees) {
        this.decisionTreeBuilder = new DecisionTreeBuilder(maxDepth, minSamplesInNode, 1.0, null);
        this.numTrees = numTrees;
    }

    @Override
    public void add(double[] x, double y) {
        decisionTreeBuilder.add(x, y);
    }

    @Override
    public Regression build() {
        Regression[] trees = new Regression[numTrees];
        for (int i = 0; i < numTrees; i++) {
            Regression tree = trees[i] = decisionTreeBuilder.build();
            decisionTreeBuilder.replaceY((x, y) -> y - tree.compute(x));
        }
        return x -> {
            double sum = 0.0;
            for (Regression tree : trees) {
                sum += tree.compute(x);
            }
            return sum;
        };
    }
}
