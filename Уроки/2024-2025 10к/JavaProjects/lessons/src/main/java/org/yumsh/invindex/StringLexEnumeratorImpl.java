package org.yumsh.invindex;

import java.nio.charset.StandardCharsets;

public class StringLexEnumeratorImpl implements StringLexEnumerator {
    private final Node root = new Node();
    private int count = 0;
    private int maxBytesLen = 0;

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
        for (byte b : bytes) {
            int bi = b & 0xFF;
            if (node.isEoS) {
                idx += 1;
            }
            for (int i = 0; i < bi; i++) {
                idx += node.count[i];
            }
            Node child = node.children[bi];
            node = child == null ? (node.children[bi] = new Node()) : child;
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

    private static final class Node {
        private boolean isEoS = false;
        private final Node[] children = new Node[256];
        private final int[] count = new int[256];
    }
}
