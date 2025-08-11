package org.yumsh.bits;

public final class BitOperations {
    /**
     * @param p степень.
     * @return 2**p
     */
    public static long pow2(int p) {
        return 1L << p;
    }

    /**
     * @param p число единиц.
     * @return 0b00...00111...111 (p единиц)
     */
    public static long mask(int p) {
        return 64 <= p ? 0xFFFFFFFFFFFFFFFFL : (1L << p) - 1L;
    }

    /**
     * @param p число единиц.
     * @return 0b00...00111...100...000 (единицы в позициях (k;p]).
     */
    public static long mask(int p, int k) {
        return mask(p) - mask(k);
    }

    /**
     * @param x любое число.
     * @return число, в котором все биты нули, кроме того,
     * который является самой младшей единицей в x.
     */
    public static long lowBit(long x) {
        return x & (-x);
    }

    /**
     * @param x любое число.
     * @return число, в котором все биты нули, кроме того,
     * который является самой старшей единицей в x.
     */
    public static long highBit(long x) {
        for (int i = 63; i >= 0; i--) {
            if ((x & pow2(i)) != 0L) {
                return pow2(i);
            }
        }
        return 0L;
    }

    /**
     * @param x любое число.
     * @return число, в котором все биты нули, кроме того,
     * который является самой старшей единицей в x.
     */
    public static long highBit2(long x) {
        while (true) {
            long lb = lowBit(x);
            if (x == lb) {
                return lb;
            }
            x = x & ~lb;
        }
    }

    /**
     * @param x любое число.
     * @return число, в котором все биты нули, кроме того,
     * который является самой старшей единицей в x.
     */
    public static long highBit3(long x) {
        x |= (x >>> 1L);
        x |= (x >>> 2L);
        x |= (x >>> 4L);
        x |= (x >>> 8L);
        x |= (x >>> 16L);
        x |= (x >>> 32L);
        return x ^ (x >>> 1L);
    }

    public static long reverse1(long x) {
        long y = 0L;
        for (int i = 0; i < 64; i++) {
            if ((x & pow2(i)) != 0L) {
                y |= pow2(63 - i);
            }
        }
        return y;
    }

    public static long reverse2(long x) {
        for (int i = 0; i < 32; i++) {
            long a = ((x >>> i) & 1L) << (63 - i) | (((x >>> (63 - i)) & 1L) << i);
            x &= ~((1L << i) | (1L << (63 - i)));
            x |= a;
        }
        return x;
    }

    public static long reverse3(long x) {
        for (int i = 0; i < 32; i++) {
            long a = ((x >>> i) & 1L) << (63 - i) | (((x >>> (63 - i)) & 1L) << i);
            x &= ~((1L << i) | (1L << (63 - i)));
            x |= a;
        }
        return x;
    }
}
