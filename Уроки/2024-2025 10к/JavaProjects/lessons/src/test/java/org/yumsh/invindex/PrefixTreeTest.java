package org.yumsh.invindex;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PrefixTreeTest {

    private static PrefixTree createTree() {
//        return new DirectArrayYMap();
//        return new ArrayYMap();
        return new PrefixTreeImpl();
    }

    @Test
    public void testSize() {
        PrefixTree tree = createTree();
        Assertions.assertEquals(0, tree.size());
        tree.put("ss", "ss");
        Assertions.assertEquals(1, tree.size());
        int n = 200;
        for (int i = 0; i < n; i++) {
            tree.put("" + i, "" + i);
            tree.put("" + i, "" + i);
            Assertions.assertEquals(2 + i, tree.size());
        }
    }

    @Test
    public void test() {
        PrefixTree tree = createTree();
        Assertions.assertEquals(0, tree.size());
        Assertions.assertNull(tree.get("a"));
        tree.put("a", "a");
        Assertions.assertEquals(1, tree.size());
        Assertions.assertEquals("a", tree.get("a"));
        Assertions.assertNull(tree.get("b"));

        int n = 200;
        for (int k = 0; k < 10; k++) {
            tree = createTree();
            for (int i = 0; i < n; i++) {
                String key = "k" + i;
                String value = "v" + k + '.' + i;
                tree.put(key, value);
                Assertions.assertEquals(value, tree.get(key));
                Assertions.assertEquals(i, tree.size());
                tree.put(key, value);
                Assertions.assertEquals(value, tree.get(key));
                Assertions.assertEquals(i, tree.size());

                Assertions.assertNull(tree.get("v" + k + '.' + (i + 1)));
            }
            for (int i = 0; i < n; i++) {
                String key = "k" + i;
                String value = "v" + k + '.' + i;
                Assertions.assertEquals(value, tree.get(key));
            }
        }
    }
}
