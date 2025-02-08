package org.yumsh.sort;

import java.util.concurrent.ThreadLocalRandom;

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

    /**
     * Квадратичная сортировка подсчётом.
     *
     * @param arr массив, который будет отсортирован.
     */
    public static void sortByCount(double[] arr) {
        int n = arr.length;
        if (n <= 1) {
            return;
        }
        double[] original = arr.clone();
        for (int i = 0; i < n; i++) {
            double x = original[i];
            int k = 0;
            for (int j = 0; j < n; j++) {
                if (original[j] < x || (original[j] == x && j < i)) {
                    k += 1;
                }
            }
            arr[k] = x;
        }
    }

    /**
     * Быстрая сортировка.
     *
     * @param arr массив, который будет отсортирован.
     */
    public static void sortByQuick(double[] arr) {
        sortByQuick(arr, 0, arr.length);
    }

    public static void sortByQuick(double[] arr, int l, int r) {
        if (r <= l + 1) {
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
        if (q == l) {
            arr[m] = arr[l];
            arr[l] = pivot;
            sortByQuick(arr, l + 1, r);
        } else {
            sortByQuick(arr, l, q);
            sortByQuick(arr, q, r);
        }
    }

    /**
     * Сортировка слиянием.
     *
     * @param arr массив, который будет отсортирован.
     */
    public static void sortByMerge(double[] arr) {
        if (arr.length <= 1) {
            return;
        }
        sortByMerge(arr, new double[arr.length], 0, arr.length);
    }

    private static void sortByMerge(double[] arr, double[] tmp, int l, int r) {
        if (r - l <= 1) {
            return;
        }
        int m = (l + r) >> 1; // l < m < r
        sortByMerge(arr, tmp, l, m);
        sortByMerge(arr, tmp, m, r);

        int i = l;
        int i1 = l;
        int i2 = m;
        while (i1 < m && i2 < r) {
            if (arr[i1] < arr[i2]) {
                tmp[i++] = arr[i1++];
            } else {
                tmp[i++] = arr[i2++];
            }
        }
        if (i1 < m) {
            System.arraycopy(arr, i1, tmp, i, m - i1);
            System.arraycopy(tmp, l, arr, l, r - l);
        } else {
            System.arraycopy(tmp, l, arr, l, i2 - l);
        }
    }

    public static void sortByHeap(double[] arr) {
        // I этап
        int n = arr.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            siftDown(arr, n, i);
        }
        // II этап
        for (int i = n - 1; i > 0; i--) {
            // Поменять arr[0] <-> arr[i]
            double tmp = arr[i];
            arr[i] = arr[0];
            arr[0] = tmp;
            siftDown(arr, i, 0);
        }
    }

    private static void siftDown(double[] arr, int heapSize, int i) {
        double v = arr[i];
        while (true) {
            int left = 2 * i + 1;
            if (heapSize <= left) {
                break;
            }
            int next = i;
            double max = v;
            if (max < arr[left]) {
                next = left;
                max = arr[left];
            }
            int right = left + 1;
            if (right < heapSize && max < arr[right]) {
                next = right;
            }
            if (next == i) {
                break;
            }
            arr[i] = arr[next];
            i = next;
        }
        arr[i] = v;
    }
}
