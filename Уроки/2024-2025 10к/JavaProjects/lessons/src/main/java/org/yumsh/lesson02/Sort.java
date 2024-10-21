package org.yumsh.lesson02;

/**
 * Методы сортировки массивов.
 */
public class Sort {
    /**
     * Отсортировать массив методом выбора.
     *
     * @param arr массив, который будет отсортирован.
     */
    public static void sortBySelection(double[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }
            if (i != minIdx) {
                double tmp = arr[i];
                arr[i] = arr[minIdx];
                arr[minIdx] = tmp;
            }
        }
    }

    /**
     * Отсортировать массив методом вставки.
     *
     * @param arr массив, который будет отсортирован.
     */
    public static void sortByInsertion(double[] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j] <= arr[j - 1]) {
                    double tmp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = tmp;
                } else {
                    break;
                }
            }
        }
    }

    /**
     * Сортировка бакетами.
     *
     * @param arr массив, который будет отсортирован.
     */
    public static void sortByBucket(int[] arr) {
        int m = 0;
        for (int x : arr) {
            if (m < x) {
                m = x;
            }
        }
        int[] counts = new int[m + 1];
        for (int x : arr) {
            counts[x]++;
        }
        int i = 0;
        for (int x = 0; x <= m; x++) {
            int count = counts[x];
            for (int k = 0; k < count; k++) {
                arr[i++] = x;
            }
        }
    }
}
