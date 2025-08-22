package org.yumsh.decissiontree;

/// Регрессия.
@FunctionalInterface
public interface Regression {
    /// Вычислить значение регрессии в данной точке.
    ///
    /// @param x точка.
    double compute(double[] x);
}
