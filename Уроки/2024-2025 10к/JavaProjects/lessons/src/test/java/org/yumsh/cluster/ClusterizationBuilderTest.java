package org.yumsh.cluster;

import java.util.Random;
import org.junit.jupiter.api.Test;

public class ClusterizationBuilderTest {

    private static ClusterizationBuilder createClusterizationBuilder() {
        return new ClusterizationBuilder() {
            @Override
            public void add(double[] x) {

            }

            @Override
            public Clusterization build() {
                return null;
            }
        };
    }

    @Test
    public void test() {
        Random rnd = new Random();
        int dim = 10;
        int numClusters = 10;
        int numPoints = 10000;
        int testPoints = 1000;

        double[] zero = new double[dim];
        double[][] c = new double[10][];
        for (int i = 0; i < numClusters; i++) {
            c[i] = generatePoint(rnd, dim, zero, numClusters * 2);
        }

        ClusterizationBuilder clusterizationBuilder = createClusterizationBuilder();
        for (int i = 0; i < numPoints; i++) {
            int ci = rnd.nextInt(numClusters);
            double[] x = generatePoint(rnd, dim, c[ci], i + 1.0);
            clusterizationBuilder.add(x);
        }

        Clusterization clusterization = clusterizationBuilder.build();

        double sumDistance0 = 0.0;
        double sumDistance1 = 0.0;
        for (int i = 0; i < testPoints; i++) {
            int ci = rnd.nextInt(numClusters);
            double[] x = generatePoint(rnd, dim, c[ci], i + 1.0);
            sumDistance0 += distance(x, c[ci]);
            sumDistance1 += clusterization.getCluster(x).distance();
        }
        sumDistance0 /= testPoints;
        sumDistance1 /= testPoints;
        System.out.println("Original distance: " + sumDistance0);
        System.out.println("Cluster distance: " + sumDistance1);
    }

    private static double distance(double[] x, double[] y) {
        double sum2 = 0.0;
        for (int i = 0; i < x.length; i++) {
            double d = x[i] - y[i];
            sum2 += d * d;
        }
        return Math.sqrt(sum2);
    }

    private static double[] generatePoint(Random rnd, int dim, double[] c, double sd) {
        double[] x = new double[dim];
        for (int i = 0; i < dim; i++) {
            x[i] = rnd.nextGaussian(c[i], sd);
        }
        return x;
    }
}
