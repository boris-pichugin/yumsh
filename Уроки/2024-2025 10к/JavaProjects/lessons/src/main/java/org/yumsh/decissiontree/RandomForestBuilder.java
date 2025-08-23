package org.yumsh.decissiontree;

public class RandomForestBuilder implements RegressionBuilder {

    private final DecisionTreeBuilder decisionTreeBuilder;
    private final int numTrees;

    /// @param maxDepth         максимальная глубина дерева.
    /// @param minSamplesInNode минимальное число обучающих образов в листе дерева.
    /// @param subsamplePart    доля обучающих образов, которые будут использованы для построения дерева: `subsamplePart in [0;1]`.
    /// @param numTrees         число деревьев в лесу.
    public RandomForestBuilder(int maxDepth, int minSamplesInNode, double subsamplePart, int numTrees) {
        this.decisionTreeBuilder = new DecisionTreeBuilder(maxDepth, minSamplesInNode, subsamplePart);
        this.numTrees = numTrees;
    }

    @Override
    public void add(double[] x, double y) {

    }

    @Override
    public Regression build() {
        return null;
    }
}
