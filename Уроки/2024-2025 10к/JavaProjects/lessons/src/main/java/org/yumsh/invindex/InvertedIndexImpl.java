package org.yumsh.invindex;

public class InvertedIndexImpl implements InvertedIndex {

    @Override
    public int put(String... docTerms) {
        return 0;
    }

    @Override
    public int[] get(String term) {
        return new int[0];
    }

    @Override
    public int[] getAnd(String term1, String term2) {
        return new int[0];
    }

    @Override
    public int[] getOr(String term1, String term2) {
        return new int[0];
    }

    @Override
    public int[] getAnd(String... terms) {
        return new int[0];
    }

    @Override
    public int[] getOr(String... terms) {
        return new int[0];
    }

    @Override
    public int[] getRelaxedAnd(int minShouldMatch, String... terms) {
        return new int[0];
    }
}
