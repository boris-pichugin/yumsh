package org.yumsh.decissiontree;

import java.util.Random;

public class RandomForestBuilder implements RegressionBuilder {

    private final DecisionTreeBuilder decisionTreeBuilder;
    private final int numTrees;

    /// @param maxDepth         максимальная глубина дерева.
    /// @param minSamplesInNode минимальное число обучающих образов в листе дерева.
    /// @param subsamplePart    доля обучающих образов, которые будут использованы для построения дерева: `subsamplePart in [0;1]`.
    /// @param numTrees         число деревьев в лесу.
    public RandomForestBuilder(int maxDepth, int minSamplesInNode, double subsamplePart, Random rnd, int numTrees) {
        this.decisionTreeBuilder = new DecisionTreeBuilder(maxDepth, minSamplesInNode, subsamplePart, rnd);
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
            trees[i] = decisionTreeBuilder.build();
        }
        return x -> {
            double sum = 0.0;
            for (Regression tree : trees) {
                sum += tree.compute(x);
            }
            return sum / numTrees;
        };
    }
}
