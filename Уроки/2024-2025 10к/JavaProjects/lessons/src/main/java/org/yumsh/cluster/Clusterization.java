package org.yumsh.cluster;

@FunctionalInterface
public interface Clusterization {
    ClusterizationResult getCluster(double[] x);

    record ClusterizationResult(int clusterId, double distance) {
    }
}
