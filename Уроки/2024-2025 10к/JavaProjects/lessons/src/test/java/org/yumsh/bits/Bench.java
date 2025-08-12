package org.yumsh.bits;

import java.util.Random;

public final class Bench {

    private Bench() {
    }

    public static void main(String[] args) {
        long[] values = new long[1 << 20];
        Random rnd = new Random(42);
        for (int i = 0; i < values.length; i++) {
            values[i] = rnd.nextLong();
        }
        long s = 0L;
        for (int i = 0; i < 2000; i++) {
            {
                long t = System.nanoTime();
                s += test0(values);
                t = System.nanoTime() - t;
                if (i >= 1000) {
                    System.out.println("test0 " + t);
                }
            }
            {
                long t = System.nanoTime();
                s += test1(values);
                t = System.nanoTime() - t;
                if (i >= 1000) {
                    System.out.println("test1 " + t);
                }
            }
            {
                long t = System.nanoTime();
                s += test2(values);
                t = System.nanoTime() - t;
                if (i >= 1000) {
                    System.out.println("test2 " + t);
                }
            }
            {
                long t = System.nanoTime();
                s += test3(values);
                t = System.nanoTime() - t;
                if (i >= 1000) {
                    System.out.println("test3 " + t);
                }
            }
        }
        System.out.println(s);
    }

    private static long test0(long[] values) {
        long s = 0;
        for (long v : values) {
            s += BitOperations.lowBitNo0(v);
        }
        return s;
    }

    private static long test1(long[] values) {
        long s = 0;
        for (long v : values) {
            s += BitOperations.lowBitNo1(v);
        }
        return s;
    }

    private static long test2(long[] values) {
        long s = 0;
        for (long v : values) {
            s += BitOperations.lowBitNo2(v);
        }
        return s;
    }

    private static long test3(long[] values) {
        long s = 0;
        for (long v : values) {
            s += BitOperations.lowBitNo3(v);
        }
        return s;
    }
}
