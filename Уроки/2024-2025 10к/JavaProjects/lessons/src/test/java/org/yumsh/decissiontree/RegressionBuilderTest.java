package org.yumsh.decissiontree;

import java.util.Random;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RegressionBuilderTest {
    private static final int D = 5;
    private static final int TRAIN_SIZE = 100000;
    private static final int TEST_SIZE = 100000;

    private static RegressionBuilder createRegressionBuilder() {
// max R2 = 94.48063676281174
//        return new DecisionTreeBuilder(5, 10); // 80.05634337658259
//        return new DecisionTreeBuilder(5, 50); // 80.05634337658259
//        return new DecisionTreeBuilder(100, 100); // 95.76787501759077
//        return new DecisionTreeBuilder(100, 10); // 96.99879490230437


//        return new DecisionTreeBuilder(100, 10); // 91.27741855900054
//        return new DecisionTreeBuilder(100, 1); // 86.95185866215799
//        return new DecisionTreeBuilder(100, 100); // 91.37677184504722
//        return new DecisionTreeBuilder(1000, 100); // 91.37677184504722
//        return new DecisionTreeBuilder(1000, 10, 1.0, new Random(11)); // 91.37677184504722
//        return new DecisionTreeBuilder(6, 100, 1.0, new Random(11)); // 79.03616245549581
//        return new RandomForestBuilder(6, 100, 1.0, new Random(11), 1); // 78.44927792888687
//        return new RandomForestBuilder(6, 100, 0.5, new Random(11), 2); // 78.62600074108684
//        return new RandomForestBuilder(6, 100, 0.5, new Random(11), 20); // 79.04296694189635
//        return new RandomForestBuilder(6, 100, 0.5, new Random(11), 200); // 79.11815581611495

//        return new BoostedForestBuilder(6, 100, 1); // 78.44927792888687
//        return new BoostedForestBuilder(6, 100, 2); // 83.1151517295535
//        return new BoostedForestBuilder(6, 100, 20); // 87.60404310748719
//        return new BoostedForestBuilder(6, 100, 200); // 88.62106650544396
//        return new BoostedForestBuilder(5, 100, 200); // 88.56490006720375
//        return new BoostedForestBuilder(7, 100, 200); // 88.92501695234472
        return new BoostedForestBuilder(7, 30, 200); // 88.1168547434391
//        return new BoostedForestBuilder(15, 100, 200); // 87.66086435209452

//        return new RegressionBuilder() {
//            final Random rnd = new Random(11);
//
//            @Override
//            public void add(double[] x, double y) {
//            }
//
//            @Override
//            public Regression build() {
//                return x -> computeEY(x, rnd);
//            }
//        };
    }

    @Test
    public void test() {
        Random rnd = new Random(42);
        RegressionBuilder regressionBuilder = createRegressionBuilder();
        for (int i = 0; i < TRAIN_SIZE; i++) {
            double[] x = generateX(rnd);
            double y = computeY(x, rnd);
            regressionBuilder.add(x, y);
        }
        Regression regression = regressionBuilder.build();
        double mse = 0.0;
        double sumY = 0.0;
        double sumY2 = 0.0;
        for (int i = 0; i < TEST_SIZE; i++) {
            double[] x = generateX(rnd);
            double y = computeY(x, rnd);
            double f = regression.compute(x);
            sumY += y;
            sumY2 += y * y;
            double d = f - y;
            mse += d * d;
        }
        mse /= TEST_SIZE;
        double evy = sumY / TEST_SIZE;
        double var = sumY2 / TEST_SIZE - evy * evy;

        double r2 = (1.0 - mse / var) * 100.0;
        System.out.println("R2 = " + (1.0 - mse / var) * 100.0);
        Assertions.assertTrue(r2 > -1e-3);
    }

    private static double[] generateX(Random rnd) {
        double[] x = new double[D];
        for (int i = 0; i < D; i++) {
            x[i] = rnd.nextGaussian();
        }
        return x;
    }

    private static double computeY(double[] x, Random rnd) {
        double y = 0.2 * rnd.nextGaussian();
        for (int i = 0; i < D; i++) {
            y = Math.sin(y + x[i]) + Math.cos(y - x[i]);
        }
        return y + 0.2 * rnd.nextGaussian();
    }

    private static double computeEY(double[] x, Random rnd) {
        double y = 0.0;
        for (int i = 0; i < 1000; i++) {
            y += computeY(x, rnd);
        }
        return y / 1000.0;
    }
}
