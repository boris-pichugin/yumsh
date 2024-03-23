package org.bpichugin.bitset;

/**
 * Класс, представляющий подмножество
 * множества {@code X = {0,...,63}}.
 */
public final class BitSet64 {
    /**
     * Битовая маска элементов, содержащися в множестве.
     */
    private long bitMap;


    public void put(int v) {
        assert 0 <= v && v < 64 : "Элемент %d не принадлежит [0;64)".formatted(v);
        bitMap |= (1L << v);
    }

    public void union(BitSet64 b) {
        this.bitMap |= b.bitMap;
    }

    /**
     * Проверить содержится ли данный элемент в этом множесте.
     *
     * @param v проверяемый элемент из {@code [0,64)}.
     * @return {@code true}, если данный элмент  {@code v}
     * содержится в этом множестве.
     */
    public boolean contains(int v) {
        assert 0 <= v && v < 64 : "Элемент %d не принадлежит [0;64)".formatted(v);
        return (bitMap & (1L << v)) != 0L;
    }

    public void remove(int v) {
        assert 0 <= v && v < 64 : "Элемент %d не принадлежит [0;64)".formatted(v);
        bitMap &= ~(1L << v);
    }


    public void intersect(BitSet64 b) {
        bitMap &= b.bitMap;
    }

    public void minus(BitSet64 b) {
        bitMap &= ~b.bitMap;
    }

    public void invert() {
        bitMap = ~bitMap;
    }

    public void disjunctiveUnion(BitSet64 b) {
        bitMap ^= b.bitMap;
    }
}
