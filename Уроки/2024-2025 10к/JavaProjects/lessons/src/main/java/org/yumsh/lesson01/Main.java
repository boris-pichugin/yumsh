package org.yumsh.lesson01;

public class Main {
    public static void main(String[] a) {
        boolean b1 = true;
        boolean b2 = false; // 1B = 8bit

        byte x1 = 5; // 1B -128..127
        short x2 = -8; // 2B -2^15 .. (2^15 - 1)
        int x3 = 55; // 4B -2^31 .. 2^31
        int x4 = Integer.MAX_VALUE;
        long x5 = 333L; // 8B
        long x6 = 0xF333L; // 8B
        byte y = 0b1101111;

        float z1 = 0.4f; // 4B
        double z3 = 0.5; // 8B

        char ch = 't'; // 2B
        short x = (short)ch;

        String str = "Hello!"; // 4B / 8B
    }
}