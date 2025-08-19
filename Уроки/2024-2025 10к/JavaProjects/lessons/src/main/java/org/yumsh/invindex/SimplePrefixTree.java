package org.yumsh.invindex;

import java.nio.charset.StandardCharsets;

public class SimplePrefixTree implements PrefixTree {
    private int size = 0;
    private final Node root = new Node();

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(String key, Object value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException();
        }
        Node node = root;
        for (byte b : key.getBytes(StandardCharsets.UTF_8)) {
            int bi = b & 0xFF;
            Node child = node.children[bi];
            node = child == null ? (node.children[bi] = new Node()) : child;
        }
        if (node.value == null) {
            size += 1;
        }
        node.value = value;
    }

    @Override
    public Object get(String key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        Node node = root;
        for (byte b : key.getBytes(StandardCharsets.UTF_8)) {
            int bi = b & 0xFF;
            Node child = node.children[bi];
            if (child == null) {
                return null;
            }
            node = child;
        }
        return node.value;
    }

    private static final class Node {
        private final Node[] children = new Node[256];
        private Object value = null;
    }
}
