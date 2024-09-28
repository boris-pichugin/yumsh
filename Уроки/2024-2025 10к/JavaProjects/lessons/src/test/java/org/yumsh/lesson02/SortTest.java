package org.yumsh.lesson02;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SortTest {

    @Test
    public void testSortBySelection() {
        double[] arr = {8, 7, 6, 5, 4, 9, 78, 76, 2};
        Sort.sortBySelection(arr);
        for (int i = 0; i < arr.length - 1; i++) {
            Assertions.assertTrue(arr[i] <= arr[i + 1]);
        }
    }
}
