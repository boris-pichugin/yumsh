package org.yumsh.sort;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Random;

public class MinKTest {
    @Test
    public void testMinK() {
        Random rnd = new Random();
        testOneArray(0, -1, rnd);
        testOneArray(0, 0, rnd);
        testOneArray(0, 1, rnd);
        testOneArray(1, -1, rnd);
        testOneArray(1, 0, rnd);
        testOneArray(1, 1, rnd);
        testOneArray(1, 2, rnd);
        testOneArray(2, -1, rnd);
        testOneArray(2, 0, rnd);
        testOneArray(2, 1, rnd);
        testOneArray(2, 2, rnd);
        testOneArray(2, 3, rnd);
        for (int i = 0; i < 100; i++) {
            int n = rnd.nextInt(200);
            int k = rnd.nextInt(-2, n + 2);
            testOneArray(n, k, rnd);
        }
    }

    private static void testOneArray(int n, int k, Random rnd) {
        double[] arr = new double[n];
        testOneArray(arr, k);
        for (int j = 0; j < arr.length; j++) {
            arr[j] = rnd.nextDouble();
        }
        testOneArray(arr, k);
    }

    private static void testOneArray(double[] arr, int k) {
        MinK.collectMinK(arr, k);
        if (k < 1 || arr.length <= k) {
            return;
        }
        double max = arr[0];
        for (int i = 1; i < k; i++) {
            if (max < arr[i]) {
                max = arr[i];
            }
        }
        for (int i = k; i < arr.length; i++) {
            Assertions.assertTrue(max <= arr[i]);
        }
    }
}
