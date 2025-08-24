package org.yumsh.cluster;

public interface ClusterizationBuilder {
    void add(double[] x);

    Clusterization build();
}
