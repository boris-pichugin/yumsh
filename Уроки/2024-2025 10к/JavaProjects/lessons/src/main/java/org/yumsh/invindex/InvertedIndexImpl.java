package org.yumsh.invindex;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
        if (terms.length == 0) {
            return new PostingList();
        }
        Set<PostingList> andPostingListSet = new HashSet<>();
        for (String term : terms) {
            PostingList postingList = postingLists.get(term);
            if (postingList != null) {
                andPostingListSet.add(postingList);
            } else {
                return new PostingList();
            }
        }
        if (andPostingListSet.size() == 1) {
            return andPostingListSet.iterator().next().copy();
        }
        PostingList[] andPostingLists = andPostingListSet.toArray(PostingList[]::new);
        Arrays.sort(andPostingLists, Comparator.comparingInt(PostingList::size));

        PostingListIterator[] iterators = new PostingListIterator[andPostingLists.length];
        int maxDocId = -1;
        for (int i = 0; i < andPostingLists.length; i++) {
            iterators[i] = andPostingLists[i].iterator();
            int docId = iterators[i].next();
            if (maxDocId < docId) {
                maxDocId = docId;
            }
        }
        PostingListPriorityQueue queue = new PostingListPriorityQueue(iterators);
        PostingList result = new PostingList();

        while (maxDocId < Integer.MAX_VALUE) {
            PostingListIterator outsider = queue.least();
            int minDocId = outsider.docId();
            if (minDocId < maxDocId) {
                maxDocId = Math.max(maxDocId, outsider.advance(maxDocId));
            } else {
                result.add(minDocId);
                maxDocId = Math.max(maxDocId, outsider.next());
            }
            queue.replaceLeast(outsider);
        }
        return result;
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
