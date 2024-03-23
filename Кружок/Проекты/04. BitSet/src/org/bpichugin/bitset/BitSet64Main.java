package org.bpichugin.bitset;

public class BitSet64Main {
    public static void main(String[] args) {
        BitSet64 a = new BitSet64();
        a.put(7);
        if (a.contains(7)) {
            System.out.println("A содержит 7");
        }
        BitSet64 b = new BitSet64();
        b.put(8);

        a.union(b);
        a.remove(8);
        a.intersect(b);
        a.minus(b);
        a.invert();
        a.disjunctiveUnion(b);
    }
}
