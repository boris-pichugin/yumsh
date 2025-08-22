package org.yumsh.decissiontree;

/// Построитель регрессии.
public interface RegressionBuilder {
    /// Добавить обучающий образ.
    ///
    /// @param x точка.
    /// @param y наблюдаемое значение в точке `x`.
    void add(double[] x, double y);

    /// Построить регрессию.
    Regression build();
}
