package org.yumsh.invindex;

import java.util.HashMap;
import java.util.Map;

public class InvertedIndexImpl implements InvertedIndex {
    private final Map<String, PostingList> postingLists = new HashMap<>();
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
            postingLists.computeIfAbsent(term, t -> new PostingList()).add(docId);
        }
        return docId;
    }

    @Override
    public PostingList get(String term) {
        return copy(postingLists.get(term));
    }

    @Override
    public PostingList getAnd(String term1, String term2) {
        PostingList postingList1 = postingLists.get(term1);
        PostingList postingList2 = postingLists.get(term2);

        PostingList andPostingList = new PostingList();
        if (postingList1 == null || postingList2 == null) {
            return andPostingList;
        }
        PostingListIterator i = postingList1.iterator();
        PostingListIterator j = postingList2.iterator();
        int docId1 = i.next();
        int docId2 = j.next();
        while (docId1 < Integer.MAX_VALUE && docId2 < Integer.MAX_VALUE) {
            if (docId1 < docId2) {
                docId1 = i.advance(docId2);
            } else if (docId2 < docId1) {
                docId2 = j.advance(docId1);
            } else {
                andPostingList.add(docId1);
                docId1 = i.next();
                docId2 = j.next();
            }
        }
        return andPostingList;
    }

    @Override
    public PostingList getOr(String term1, String term2) {
        PostingList postingList1 = postingLists.get(term1);
        PostingList postingList2 = postingLists.get(term2);
        if (postingList1 == null) {
            return copy(postingList2);
        }
        if (postingList2 == null) {
            return copy(postingList1);
        }
        if (postingList1 == postingList2) {
            return copy(postingList1);
        }
        PostingList orPostingList = new PostingList();
        PostingListIterator i = postingList1.iterator();
        PostingListIterator j = postingList2.iterator();
        int docId1 = i.next();
        int docId2 = j.next();
        while (docId1 < Integer.MAX_VALUE && docId2 < Integer.MAX_VALUE) {
            if (docId1 < docId2) {
                orPostingList.add(docId1);
                docId1 = i.next();
            } else if (docId2 < docId1) {
                orPostingList.add(docId2);
                docId2 = j.next();
            } else {
                orPostingList.add(docId1);
                docId1 = i.next();
                docId2 = j.next();
            }
        }
        while (docId1 < Integer.MAX_VALUE) {
            orPostingList.add(docId1);
            docId1 = i.next();
        }
        while (docId2 < Integer.MAX_VALUE) {
            orPostingList.add(docId2);
            docId2 = j.next();
        }
        return orPostingList;
    }

    @Override
    public PostingList getAnd(String... terms) {
//        if (terms.length == 0) {
//            return new int[0];
//        }
//        Set<List<Integer>> andPostingListSet = new HashSet<>();
//        for (String term : terms) {
//            PostingList postingList = postingLists.get(term);
//            if (postingList != null) {
//                andPostingListSet.add(postingList);
//            } else {
//                return new int[0];
//            }
//        }
//        if (andPostingListSet.size() == 1) {
//            return toArray(andPostingListSet.iterator().next());
//        }
//        List<List<Integer>> andPostingLists = new ArrayList<>(andPostingListSet);
//        andPostingLists.sort(Comparator.comparingInt(List::size));
//
//        PostingList pos = new int[andPostingLists.size()];
//
//        PostingList result = new ArrayList<>();
//
//        while (true) {
//            int minIdx = -1;
//            int minDocId = Integer.MAX_VALUE;
//            int maxDocId = -1;
//            for (int i = 0; i < pos.length; i++) {
//                PostingList postingList = andPostingLists.get(i);
//                int p = pos[i];
//                if (postingList.size() <= p) {
//                    return toArray(result);
//                }
//                int docId = postingList.get(p);
//                if (docId < minDocId) {
//                    minIdx = i;
//                    minDocId = docId;
//                }
//                if (maxDocId < docId) {
//                    maxDocId = docId;
//                }
//            }
//
//            if (minDocId < maxDocId) {
//                PostingList postingList = andPostingLists.get(minIdx);
//                int i = pos[minIdx] + 1;
//                while (i < postingList.size() && postingList.get(i) < maxDocId) {
//                    i += 1;
//                }
//                pos[minIdx] = i;
//            } else {
//                result.add(minDocId);
//                pos[0] += 1;
//            }
//        }
        return new PostingList();
    }

    @Override
    public PostingList getOr(String... terms) {
        return new PostingList();
    }

    @Override
    public PostingList getRelaxedAnd(int minShouldMatch, String... terms) {
        return new PostingList();
    }

    private static PostingList copy(PostingList postingList) {
        return postingList == null ? new PostingList() : postingList.copy();
    }
}
