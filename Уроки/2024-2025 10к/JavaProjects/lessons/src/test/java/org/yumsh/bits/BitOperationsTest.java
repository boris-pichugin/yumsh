package org.yumsh.bits;

import java.util.Random;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BitOperationsTest {
    @Test
    public void testReverse() {
        Random rnd = new Random(42);
        for (int k = 0; k < 1000; k++) {
            long x = rnd.nextLong();
            long r1 = BitOperations.reverse1(x);
            long r2 = BitOperations.reverse2(x);
            long r3 = BitOperations.reverse3(x);
            Assertions.assertEquals(r1, r2);
            Assertions.assertEquals(r1, r3);
        }
    }

    @Test
    public void testLowBitNo() {
        Random rnd = new Random(42);
        for (int k = 0; k < 1000; k++) {
            long x = rnd.nextLong();
            long r1 = BitOperations.lowBitNo0(x);
            long r2 = BitOperations.lowBitNo1(x);
            Assertions.assertEquals(r1, r2);
        }
    }
}
