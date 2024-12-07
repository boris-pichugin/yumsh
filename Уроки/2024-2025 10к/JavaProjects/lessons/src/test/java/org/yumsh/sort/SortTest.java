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

    @Test
    public void testSortByMerge() {
        testSortDoublesMethod(Sort::sortByMerge);
    }

    @Test
    public void testSortByHeap() {
        testSortDoublesMethod(Sort::sortByHeap);
    }

    private void testSortDoublesMethod(Consumer<double[]> sortMethod) {
        Random rnd = new Random();
        testSortOneArray(sortMethod, new double[0], rnd);
        testSortOneArray(sortMethod, new double[1], rnd);
        testSortOneArray(sortMethod, new double[2], rnd);
        for (int i = 0; i < 100; i++) {
            double[] arr = new double[rnd.nextInt(3, 100)];
            testSortOneArray(sortMethod, arr, rnd);
        }
    }

    private static void testSortOneArray(Consumer<double[]> sortMethod, double[] arr, Random rnd) {
        sortMethod.accept(arr);
        for (double v : arr) {
            Assertions.assertEquals(v, 0);
        }

        for (int j = 0; j < arr.length; j++) {
            arr[j] = rnd.nextDouble();
        }
        double[] arrCopy = arr.clone();
        sortMethod.accept(arr);
        Arrays.sort(arrCopy);
        Assertions.assertArrayEquals(arr, arrCopy);
    }

    private void testSortIntegersMethod(Consumer<int[]> sortMethod) {
        Random rnd = new Random();
        testOneIntArray(sortMethod, new int[0], rnd);
        testOneIntArray(sortMethod, new int[1], rnd);
        testOneIntArray(sortMethod, new int[2], rnd);
        for (int i = 0; i < 100; i++) {
            int[] arr = new int[rnd.nextInt(3, 100)];
            testOneIntArray(sortMethod, arr, rnd);
        }
    }

    private static void testOneIntArray(Consumer<int[]> sortMethod, int[] arr, Random rnd) {
        for (int j = 0; j < arr.length; j++) {
            arr[j] = rnd.nextInt(0, 100);
        }
        int[] arrCopy = arr.clone();
        sortMethod.accept(arr);
        Arrays.sort(arrCopy);
        Assertions.assertArrayEquals(arr, arrCopy);
    }
}
