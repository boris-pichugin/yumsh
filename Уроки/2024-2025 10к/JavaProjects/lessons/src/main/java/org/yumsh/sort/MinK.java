package org.yumsh.sort;

import java.util.concurrent.ThreadLocalRandom;

public class MinK {
    /**
     * Поиск k самых маленьких элементов массива.
     *
     * @param arr массив, в первых k элементах
     *            которого будут собраны самые
     *            маленькие элементы.
     * @param k   искомое число минимальных элементов.
     */
    public static void collectMinK(double[] arr, int k) {
        collectMinK(arr, k, 0, arr.length);
    }

    private static void collectMinK(double[] arr, int k, int l, int r) {
        if (k <= 0 || r - l <= k) {
            return;
        }
        int m = ThreadLocalRandom.current().nextInt(l, r);
        double pivot = arr[m];
        int q = l;
        for (int i = l; i < r; i++) {
            double vi = arr[i];
            if (vi < pivot) {
                arr[i] = arr[q];
                arr[q] = vi;
                q += 1;
            }
        }
        int w = q - l;
        if (w == 0) {
            arr[m] = arr[l];
            arr[l] = pivot;
            collectMinK(arr, k - 1, l + 1, r);
        } else if (w < k) {
            collectMinK(arr, k - w, q, r);
        } else if (w > k) {
            collectMinK(arr, k, l, q);
        }
    }
}
