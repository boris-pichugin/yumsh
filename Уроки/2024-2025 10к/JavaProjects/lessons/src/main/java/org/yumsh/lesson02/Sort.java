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
}
