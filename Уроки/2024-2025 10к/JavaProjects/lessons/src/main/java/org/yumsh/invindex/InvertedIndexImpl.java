package org.yumsh.invindex;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
        List<Integer> postingList1 = postingLists.get(term1);
        List<Integer> postingList2 = postingLists.get(term2);
        if (postingList1 == null || postingList1 == postingList2) {
            return toArray(postingList2);
        }
        if (postingList2 == null) {
            return toArray(postingList1);
        }
        List<Integer> orPostingList = new ArrayList<>();
        int i = 0;
        int j = 0;
        while (i < postingList1.size() && j < postingList2.size()) {
            int docId1 = postingList1.get(i);
            int docId2 = postingList2.get(j);
            if (docId1 < docId2) {
                orPostingList.add(docId1);
                i++;
            } else if (docId2 < docId1) {
                orPostingList.add(docId2);
                j++;
            } else {
                orPostingList.add(docId1);
                i++;
                j++;
            }
        }
        while (i < postingList1.size()) {
            orPostingList.add(postingList1.get(i++));
        }
        while (j < postingList2.size()) {
            orPostingList.add(postingList2.get(j++));
        }
        return toArray(orPostingList);
    }

    @Override
    public int[] getAnd(String... terms) {
        if (terms.length == 0) {
            return new int[0];
        }
        Set<List<Integer>> andPostingListSet = new HashSet<>();
        for (String term : terms) {
            List<Integer> postingList = postingLists.get(term);
            if (postingList != null) {
                andPostingListSet.add(postingList);
            } else {
                return new int[0];
            }
        }
        if (andPostingListSet.size() == 1) {
            return toArray(andPostingListSet.iterator().next());
        }
        List<List<Integer>> andPostingLists = new ArrayList<>(andPostingListSet);
        andPostingLists.sort(Comparator.comparingInt(List::size));

        int[] pos = new int[andPostingLists.size()];

        List<Integer> result = new ArrayList<>();

        while (true) {
            int minIdx = -1;
            int minDocId = Integer.MAX_VALUE;
            int maxDocId = -1;
            for (int i = 0; i < pos.length; i++) {
                List<Integer> postingList = andPostingLists.get(i);
                int p = pos[i];
                if (postingList.size() <= p) {
                    return toArray(result);
                }
                int docId = postingList.get(p);
                if (docId < minDocId) {
                    minIdx = i;
                    minDocId = docId;
                }
                if (maxDocId < docId) {
                    maxDocId = docId;
                }
            }

            if (minDocId < maxDocId) {
                List<Integer> postingList = andPostingLists.get(minIdx);
                int i = pos[minIdx] + 1;
                while (i < postingList.size() && postingList.get(i) < maxDocId) {
                    i += 1;
                }
                pos[minIdx] = i;
            } else {
                result.add(minDocId);
                pos[0] += 1;
            }
        }
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
