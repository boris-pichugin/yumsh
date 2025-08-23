package org.yumsh.decissiontree;

import java.util.Random;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RegressionBuilderTest {
    private static final int D = 5;
    private static final int TRAIN_SIZE = 1000000;
    private static final int TEST_SIZE = 1000000;

    private static RegressionBuilder createRegressionBuilder() {
        return new DecisionTreeBuilder(5, 10);
    }

    @Test
    public void test() {
        Random rnd = new Random();
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
        return y;
    }
}
