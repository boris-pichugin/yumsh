package org.yumsh.invindex;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.function.Predicate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class InvertedIndexImplTest {
    @Test
    public void test() {
        Random rnd = new Random();
        int termsCount = 100;
        for (int k = 0; k < 10; k++) {
            Set<Set<String>> documents = generateDocuments(rnd, termsCount, 100000);
            InvertedIndexImpl index = createIndex(documents);

            for (int j = 0; j < 10; j++) {
                String term1 = generateTerm(rnd, termsCount);
                String term2 = generateTerm(rnd, termsCount);
                String term3 = generateTerm(rnd, termsCount);
                String term4 = generateTerm(rnd, termsCount);
                String term5 = generateTerm(rnd, termsCount);

                test(documents, doc -> doc.contains(term1), index.get(term1));
                test(documents, doc -> doc.contains(term1) && doc.contains(term2), index.getAnd(term1, term2));
                test(documents, doc -> doc.contains(term1) || doc.contains(term2), index.getOr(term1, term2));
                test(
                    documents,
                    doc -> doc.contains(term1)
                        && doc.contains(term2)
                        && doc.contains(term3)
                        && doc.contains(term4)
                        && doc.contains(term5),
                    index.getAnd(term1, term2, term3, term4, term5)
                );
                test(
                    documents,
                    doc -> doc.contains(term1)
                        || doc.contains(term2)
                        || doc.contains(term3)
                        || doc.contains(term4)
                        || doc.contains(term5),
                    index.getOr(term1, term2, term3, term4, term5)
                );
                test(
                    documents,
                    doc -> {
                        int count = (doc.contains(term1) ? 1 : 0)
                            + (doc.contains(term2) ? 1 : 0)
                            + (doc.contains(term3) ? 1 : 0)
                            + (doc.contains(term4) ? 1 : 0)
                            + (doc.contains(term5) ? 1 : 0);
                        return 3 <= count;
                    },
                    index.getRelaxedAnd(3, term1, term2, term3, term4, term5)
                );
            }
        }
    }

    private static void test(Set<Set<String>> documents, Predicate<Set<String>> predicate, int[] docIds) {
        int nextDocId = 0;
        int idx = 0;
        for (Set<String> document : documents) {
            int docId = nextDocId++;
            if (predicate.test(document)) {
                Assertions.assertEquals(docId, docIds[idx++]);
            } else if (idx < docIds.length) {
                Assertions.assertNotEquals(docId, docIds[idx]);
            }
        }
    }

    private InvertedIndexImpl createIndex(Set<Set<String>> documents) {
        InvertedIndexImpl index = new InvertedIndexImpl();
        for (Set<String> document : documents) {
            String[] terms = document.toArray(String[]::new);
            int duplicated = (terms.length + 1) / 2;
            String[] exTerms = Arrays.copyOf(terms, terms.length + duplicated);
            System.arraycopy(terms, 0, exTerms, terms.length, duplicated);
            index.put(exTerms);
        }
        return index;
    }

    public static Set<Set<String>> generateDocuments(Random rnd, int termsCount, int docCount) {
        Set<Set<String>> documents = new HashSet<>();
        for (int i = 0; i < docCount; i++) {
            documents.add(generateDocument(rnd, termsCount));
        }
        return documents;
    }

    private static Set<String> generateDocument(Random rnd, int termsCount) {
        Set<String> document = new HashSet<>();
        for (int i = 0; i < termsCount / 3; i++) {
            document.add(generateTerm(rnd, termsCount));
        }
        return document;
    }

    private static String generateTerm(Random rnd, int termsCount) {
        return ("" + rnd.nextInt(termsCount)).intern();
    }
}
