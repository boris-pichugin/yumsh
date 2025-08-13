package org.yumsh.invindex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InvertedIndexImpl implements InvertedIndex {
    private final Map<String, List<Integer>> postingLists = new HashMap<>();

    private int size;

    @Override
    public int size() {
        return size;
    }

    @Override
    public int put(String... docTerms) {
        int docId = size;
        size += 1;
        for (String term : docTerms) {
            List<Integer> postingList = postingLists.computeIfAbsent(term, t -> new ArrayList<>());
            if (postingList.isEmpty() || postingList.get(postingList.size() - 1) != docId) {
                postingList.add(docId);
            }
        }
        return docId;
    }

    @Override
    public int[] get(String term) {
        List<Integer> postingList = postingLists.get(term);
        return toArray(postingList);
    }

    @Override
    public int[] getAnd(String term1, String term2) {
        List<Integer> postingList1 = postingLists.get(term1);
        List<Integer> postingList2 = postingLists.get(term2);
        if (postingList1 == null || postingList2 == null) {
            return new int[0];
        }
        List<Integer> andPostingList = new ArrayList<>();
        int i = 0;
        int j = 0;
        while (i < postingList1.size() && j < postingList2.size()) {
            int docId1 = postingList1.get(i);
            int docId2 = postingList2.get(j);
            if (docId1 < docId2) {
                i++;
            } else if (docId2 < docId1) {
                j++;
            } else {
                andPostingList.add(docId1);
                i++;
                j++;
            }
        }
        return toArray(andPostingList);
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

    private static int[] toArray(List<Integer> postingList) {
        if (postingList == null) {
            return new int[0];
        }
        return postingList.stream().mapToInt(x -> x).toArray();
    }
}
