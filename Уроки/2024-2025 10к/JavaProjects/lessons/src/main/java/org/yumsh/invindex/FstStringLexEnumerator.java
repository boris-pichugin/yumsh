package org.yumsh.invindex;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FstStringLexEnumerator implements StringLexEnumerator {
    private static final byte[] EMPTY = new byte[0];

    private final Node root = new Node();
    private int count = 0;
    private int maxBytesLen = 0;
    private byte[] front = EMPTY;
    private final Map<Node, Node> suffixes = new HashMap<>();

    @Override
    public int size() {
        return count;
    }

    @Override
    public int put(String str) {
        if (str == null) {
            throw new IllegalArgumentException();
        }
        byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
        if (maxBytesLen < bytes.length) {
            maxBytesLen = bytes.length;
        }
        int idx = 0;
        Node node = root;
        for (int j = 0; j < bytes.length; j++) {
            byte b = bytes[j];
            int bi = b & 0xFF;
            if (j < front.length && front[j] != b) {
                int fi = front[j] & 0xFF;
                if (bi < fi) {
                    throw new IllegalStateException();
                }
                node.children[fi] = intern(node.children[fi], j + 1);
                front = EMPTY;
            }
            if (node.isEoS) {
                idx += 1;
            }
            for (int i = 0; i < bi; i++) {
                idx += node.count[i];
            }
            if (node.children[bi] == null) {
                node.children[bi] = new Node();
            }
            node = node.children[bi];
        }
        if (!node.isEoS) {
            node.isEoS = true;
            node = root;
            count += 1;
            for (byte b : bytes) {
                int bi = b & 0xFF;
                node.count[bi] += 1;
                node = node.children[bi];
            }
        }
        front = bytes;
        return idx;
    }

    @Override
    public int get(String str) {
        if (str == null) {
            throw new IllegalArgumentException();
        }
        byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
        int idx = 0;
        Node node = root;
        for (byte b : bytes) {
            int bi = b & 0xFF;
            if (node.isEoS) {
                idx += 1;
            }
            for (int i = 0; i < bi; i++) {
                idx += node.count[i];
            }
            node = node.children[bi];
            if (node == null) {
                return idx;
            }
        }
        return idx;
    }

    @Override
    public String get(int i) {
        if (size() <= i || i < 0) {
            throw new IndexOutOfBoundsException(i);
        }
        byte[] bytes = new byte[maxBytesLen];
        int pos = 0;

        Node node = root;
        while (true) {
            if (node.isEoS) {
                if (i == 0) {
                    return new String(bytes, 0, pos, StandardCharsets.UTF_8);
                }
                i -= 1;
            }
            for (int b = 0; b < 256; b++) {
                if (node.count[b] <= i) {
                    i -= node.count[b];
                } else {
                    node = node.children[b];
                    bytes[pos++] = (byte) b;
                    break;
                }
            }
        }
    }

    private Node intern(Node node, int nextByteId) {
        if (nextByteId < front.length) {
            int fi = front[nextByteId] & 0xFF;
            node.children[fi] = intern(node.children[fi], nextByteId + 1);
        }
        return suffixes.computeIfAbsent(node, n -> n);
    }

    private static final class Node {
        private int hash = 0;
        private boolean isEoS = false;
        private final Node[] children = new Node[256];
        private final int[] count = new int[256];

        @Override
        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Node node = (Node) o;
            return isEoS == node.isEoS
                && Arrays.equals(children, node.children)
                && Arrays.equals(count, node.count);
        }

        @Override
        public int hashCode() {
            if (hash == 0) {
                hash = Boolean.hashCode(isEoS);
                hash = 31 * hash + Arrays.hashCode(children);
                hash = 31 * hash + Arrays.hashCode(count);
            }
            return hash;
        }
    }
}
