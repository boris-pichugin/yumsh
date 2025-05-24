package org.yumsh.collections;

public class TreeYMap implements YMap {
    private int size = 0;
    private Node root = null;

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(Object key, Object value) {
        if (value == null) {
            remove(key);
            return;
        }
        if (root == null) {
            root = new Node(key, value);
            size += 1;
            return;
        }
        Node node = root;
        while (true) {
            int cmp = compareTo(key, node.key);
            if (cmp < 0) {
                if (node.left == null) {
                    node.left = new Node(key, value);
                    size += 1;
                    return;
                }
                node = node.left;
            } else if (cmp > 0) {
                if (node.right == null) {
                    node.right = new Node(key, value);
                    size += 1;
                    return;
                }
                node = node.right;
            } else {
                node.value = value;
                return;
            }
        }
    }

    @Override
    public Object get(Object key) {
        Node node = root;
        while (node != null) {
            int cmp = compareTo(key, node.key);
            if (cmp < 0) {
                node = node.left;
            } else if (cmp > 0) {
                node = node.right;
            } else {
                return node.value;
            }
        }
        return null;
    }

    @Override
    public void remove(Object key) {
        Node parent = null;
        int parentDirection = 0;
        Node node = root;
        while (node != null) {
            int cmp = compareTo(key, node.key);
            if (cmp < 0) {
                parent = node;
                parentDirection = -1;
                node = node.left;
            } else if (cmp > 0) {
                parent = node;
                parentDirection = 1;
                node = node.right;
            } else {
                size -= 1;
                if (node.left == null) {
                    if (parentDirection == -1) {
                        parent.left = node.right;
                    } else if (parentDirection == 1) {
                        parent.right = node.right;
                    } else {
                        root = node.right;
                    }
                } else if (node.right == null) {
                    if (parentDirection == -1) {
                        parent.left = node.left;
                    } else if (parentDirection == 1) {
                        parent.right = node.left;
                    } else {
                        root = node.left;
                    }
                } else {
                    if (parentDirection == -1) {
                        parent.left = node.left;
                    } else if (parentDirection == 1) {
                        parent.right = node.left;
                    } else {
                        root = node.left;
                    }
                    Node nodeRight = node.right;
                    Node deepNode = node.left.right;
                    while (deepNode.right != null) {
                        deepNode = deepNode.right;
                    }
                    deepNode.right = nodeRight;
                }
                return;
            }
        }
    }

    protected int compareTo(Object key1, Object key2) {
        return key1.toString().compareTo(key2.toString());
    }

    private static final class Node {
        public final Object key;
        public Object value;
        public Node left = null;
        public Node right = null;

        public Node(Object key, Object value) {
            this.key = key;
            this.value = value;
        }
    }
}
