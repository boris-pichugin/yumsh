package org.yumsh.sort;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;
import java.util.function.Consumer;

public class SortTest {

    @Test
    public void testSortBySelection() {
        testSortDoublesMethod(Sort::sortBySelection);
    }

    @Test
    public void testSortByInsertion() {
        testSortDoublesMethod(Sort::sortByInsertion);
    }

    @Test
    public void testSortByBucket() {
        testSortIntegersMethod(Sort::sortByBucket);
    }

    @Test
    public void testSortByCount() {
        testSortDoublesMethod(Sort::sortByCount);
    }

    @Test
    public void testSortByQuick() {
        testSortDoublesMethod(Sort::sortByQuick);
    }

    private void testSortDoublesMethod(Consumer<double[]> sortMethod) {
        Random rnd = new Random();
        for (int i = 0; i < 100; i++) {
            double[] arr = new double[rnd.nextInt(100)];
            for (int j = 0; j < arr.length; j++) {
                arr[j] = rnd.nextDouble();
            }
            double[] arrCopy = arr.clone();
            sortMethod.accept(arr);
            Arrays.sort(arrCopy);
            Assertions.assertArrayEquals(arr, arrCopy);
        }
    }

    private void testSortIntegersMethod(Consumer<int[]> sortMethod) {
        Random rnd = new Random();
        for (int i = 0; i < 100; i++) {
            int[] arr = new int[rnd.nextInt(100)];
            for (int j = 0; j < arr.length; j++) {
                arr[j] = rnd.nextInt(0, 100);
            }
            int[] arrCopy = arr.clone();
            sortMethod.accept(arr);
            Arrays.sort(arrCopy);
            Assertions.assertArrayEquals(arr, arrCopy);
        }
    }
}
